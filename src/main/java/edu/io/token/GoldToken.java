package edu.io.token;

// ZŁOTO
public class GoldToken extends Token {

    // zmienna prywatna przechowuje złoto w uncji
    private final double amount;

    //tworzy bazową ilość złota
    public GoldToken() {

        this(1.0);
    }


    // do przechowywania ilosci zlota w uncjach
    public GoldToken(double amount) {
        super(Label.GOLD_TOKEN_LABEL);
        if (amount < 0.0) {
            throw new IllegalArgumentException("ilosc zlota nie moze byc ujemna!!!");
        }
        this.amount = amount;
    }


    // funkcja zwraca ilość złota w danym żetonie
    public double amount() {
        return amount;
    }
}