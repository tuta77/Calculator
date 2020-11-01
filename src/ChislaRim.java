/**
 * Created by Igor Kvon
 * */

public enum ChislaRim {
    I("1"),II("2"),III("3"),IV("4"),V("5"),
    VI("6"),VII("7"),VIII("8"),IX("9"),X("10");

    private final String chislo;

    ChislaRim(String chislo){
        this.chislo = chislo;
    }

    // получение значения перечисления
    public String getChislo(){
        return chislo;
    }

    public String toString(){
        return chislo;
    }
}
