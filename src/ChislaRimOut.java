import java.util.HashMap;
import java.util.Map;

/**
 * Created by Igor Kvon
 * */

public enum ChislaRimOut {
    M(1000),CM(900),D(500), CD(400),C(100),XC(90),
    L(50), XL(40),X(10),IX(9),V(5),IV(4),I(1);

    private final int chislo;

    ChislaRimOut(int chislo){
        this.chislo = chislo;
    }

    // получение значения перечисления
    public int getChislo(){
        return chislo;
    }

    public String toString(){
        return String.valueOf(chislo);
    }
}
