public enum Znak {
    PLUS("+"),MINUS("-"),UMNOJ("*"),DELEN("/");

    private final String oper;

    Znak(String oper){
        this.oper = oper;
    }

    // получение значения перечисления
    public String getZnak(){
        return oper;
    }

    public String toString(){
        return oper;
    }
}
