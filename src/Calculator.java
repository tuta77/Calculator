/**
 * Created by Igor Kvon
 * Калькулятор целых арабских и римских чисел.
 * Входные данные от 1 до 10 [I-X] включительно
 * На выходе целочисленное деление
 * */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Calculator {
    static Boolean ArabOrRim; // ArabOrRim=true - арабские числа, FromChis=false - римские числа
    BufferedReader br;

    public Calculator(InputStreamReader isr) {
        br = new BufferedReader(isr);
    }

    public static void main(String[] args) {
        new Calculator(new InputStreamReader(System.in)).run();
    }

    public void run() {
        String a,b,c; // a и b арабские/римские числа, с=знак операции
        String txt;
        int state = 0;
        p("Калькулятор арабских[1-10] и римских чисел[I-X]");
        p("Для выхода просто нажмите Enter в пустой строке");
        try {
            while((txt = br.readLine()) != null && !txt.equals("")) {
                switch (state) { // в зависимости от состояния
                    case 0: // если состояние "читаем первое число"
                        txt = txt.trim();

                        ArabOrRim = true;

                        // проверка чисел на допустимость чисел
                        checkInputString(txt);

                        a = getComponents(txt, true)[0];
                        b = getComponents(txt, true)[2];
                        c = getComponents(txt, true)[1];

                        // переключение режимов
                        if (!ArabOrRim) {
                            // получим арабское число из римского
                            a = ChislaRim.valueOf(a).toString();
                            b = ChislaRim.valueOf(b).toString();
                        }

                        getOperacii(a, b, c, ArabOrRim);

                        state = 0; // переключаем в состояние "читаем операцию"
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // Вычислить.
    static void getOperacii(String a, String b, String c, boolean ArabOrRim){
        int x, y;
        x = Integer.parseInt(a);
        y = Integer.parseInt(b);

        switch (c) {
            case "+":
                p("" + (x + y), ArabOrRim);
                break;
            case "-":
                p("" + (x - y), ArabOrRim);
                break;
            case "*":
                p("" + (x * y), ArabOrRim);
                break;
            case "/":
                p("" + (x / y), ArabOrRim);
                break;
            default:
                p("Недопустимая операция!");
        }
    }

    // Проверка на правильность ввода.
    static void checkInputString(String txt) throws Exception {
        final String a,b,c;

        a=getComponents(txt,true)[0];
        b=getComponents(txt,true)[2];
        c=getComponents(txt,true)[1];
        try {
            if(!checkZnak(c)) {
                throw new Exception("Недопустимая операция! Допускается [+-*/]");
            }
            if (checkArab(a) && checkArab(b)) {
                ArabOrRim=true;
            }else if(checkRim(a) && checkRim(b)){
                ArabOrRim=false;
            }else{
                throw new Exception("Недопустимый ввод формата чисел!");
            }
        }catch(Exception e){
            throw new Exception("Недопустимый ввод формата чисел!");
        }
    }

    // проверка ввода на арабские цифры от 1 до 10
    static boolean checkArab(String txt) throws Exception {
        try {
            for (ChislaArab arab : ChislaArab.values()) {
                if (txt.equals(arab.getChislo())) {
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            throw new Exception("Допустимые числа в диапазоне [1-10]");
        }
    }

    // проверка ввода на римские цифры от I до X
    static boolean checkRim(String txt) throws Exception {
        try {
            for (ChislaRim rim : ChislaRim.values()) {
                if (txt.equals(rim.name())) {
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            throw new Exception("Допустимые заглавные числа в диапазоне [I-X]");
        }
    }

    // проверка ввода арифметические операции (+-*/)
    static boolean checkZnak(String txt) throws Exception {
        try {
            for (Znak z : Znak.values()) {
                if (txt.equals(z.getZnak())) {
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            throw new Exception("Допустимые операции в диапазоне [+-*/]");
        }
    }

    // вывод на экран
    public static void p(String txt, boolean fc){
        try {
            if (fc) {
                System.out.println(txt);
            } else {
                System.out.println(getTransform(txt, false));
            }
        } catch (Exception e) {
            p(e.getMessage());
        }
    }

    // вывод на экран
    public static void p(String txt){
        System.out.println(txt);
    }

    // преобразование ответа с арабских чисел в римские
    static String getTransform(String txt, boolean fc) throws Exception{
        try {
            return convertToRim(getComponents(txt, fc)[0]);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // Получить имя перечисления по значению
    public static String getOutNameByVal(int v){
        String txt=String.valueOf(v);
        if (txt.length()>4){
            v=Integer.parseInt(txt.substring(0,3));
        }

        for(ChislaRimOut e : ChislaRimOut.values()){
            if(v==e.getChislo()) {
                return e.name();
            }
        }
        return null;
    }

    // получим составляющие нашего уравнения или
    // получим составляющие ответа
    static String[] getComponents(String txt, boolean InOutMaska) throws Exception {
        try {
            StringTokenizer st;
            st = new StringTokenizer(txt, "+-*/", true); //input txt

            String[] arr = new String[st.countTokens()];

            if (InOutMaska){
                if (arr.length != 3){
                    throw new Exception("Недопустимая операция!");
                }
            }else{
                if (arr.length != 1){
                    throw new Exception("UPS!");
                }
            }
            int i=0;
            while (st.hasMoreTokens()) {
                arr[i] = st.nextToken();
                i++;
            }
            return arr;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // Преобразование арабских цифр в римские.
    public static String convertToRim(String txt) {
        int num = Integer.parseInt(txt);
        if (num >= 4000 || num < 1) {
            return "0";}
        StringBuilder result = new StringBuilder();
        for(ChislaRimOut rim : ChislaRimOut.values()) {
            while (num >= rim.getChislo()) {
                num -= rim.getChislo();
                result.append(getOutNameByVal(rim.getChislo()));
            }
        }
        return result.toString();
    }
}
