package edu.io;

import edu.io.token.PlayerToken;
import edu.io.token.PyriteToken;
import edu.io.token.Token;
import edu.io.token.GoldToken;

public class Player {

    //do przechowywania pionka gracza
    private PlayerToken token;

    //do przechowywania złota
    private double gold;

    // konstruktor gracza, na start przypisuje mu 0 złota
    public Player() {
        this.gold = 0.0;
    }

    //przypisanie pionka do gracza
    public void assignToken(PlayerToken token) {
        this.token = token;
        this.token.setPlayer(this);
    }

    //zwraca pionek gracza
    public PlayerToken token() {
        return token;
    }

    //zwraca aktualna ilosc zlota gracza
    public double gold() {
        return gold;
    }

    //dodanie złota do sakwy gracza
    public void gainGold(double amount) {
        //zmiana walidacji
        if (amount < 0.0) {
            throw new IllegalArgumentException("nie mozna dodac ujemnej ilosci zlota!!!");
        }

        this.gold += amount;
        System.out.println("Gracz zebral " + amount + " uncji zlota! (Razem: " + this.gold + ")");
    }

    //funkcja do odejmowania złota gracza
    public void loseGold(double amount) {
        if (amount < 0.0) {
            throw new IllegalArgumentException("nie mozna odjac ujemnej ilosci zlota!!!");
        }

        if (this.gold - amount < 0) {
            throw new IllegalArgumentException("anty zadluzenie!!!");
        }
        this.gold -= amount;
    }

    // interakcja gracza ze złotem
    public void interactWithToken(Token token) {
        // Używamy "Pattern Matching for instanceof"
        // Sprawdza, czy token to GoldToken (lub PyriteToken) i od razu przypisuje do zmiennej 'goldToken'
        // jesli to zlotoglupcow to daje komunikat "To zloto glupcow, Glupcze + 0.0!!!"
        if (token instanceof PyriteToken) {
            System.out.println("To zloto glupcow, Glupcze + 0.0!!!");
        } else if (token instanceof GoldToken goldToken) {
            gainGold(goldToken.amount());
        }
    }
}