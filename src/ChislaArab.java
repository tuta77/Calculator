public enum ChislaArab {
    ONE("1"),TWO("2"),THREE("3"),FOUR("4"),FIVE("5"),
    SIX("6"),SEVEN("7"),EIGHT("8"),NINE("9"),TEN("10");

    private final String chislo;

    ChislaArab(String chislo){
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

