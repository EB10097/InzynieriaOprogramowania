package edu.io.token;

// od teraz traktujemy token jako ojca dla innych znaków

public abstract class Token {

    private final String label; //

    // konstruktor używany podczas tworzenia nowego obiektu klasy Token
    //  wywoływana za pomocą słowa kluczowego super
    public Token(String label) {
        this.label = label;
    }

    //label jest teraz prywatne więc to jedyny sposób aby inne klasy
    // mogły zapytać o to jakim jest symbolem
    public String label() {
        return label;
    }
}