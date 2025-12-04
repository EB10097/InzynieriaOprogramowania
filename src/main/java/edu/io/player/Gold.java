package edu.io.player;
// klasa zarządza ilościa posiadanego złota przez gracza
public class Gold {
    private double amount;

    public Gold() {
        this.amount = 0.0;
    }

    public Gold(double initialAmount) {
        if (initialAmount < 0) {
            throw new IllegalArgumentException("Zloto nie moze być ujemne");
        }
        this.amount = initialAmount;
    }

    public double amount() {
        return amount;
    }
    // dodanie złota do "sakiewki"
    public void gain(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Nie można dodać ujemnego złota");
        }
        this.amount += value;
    }
    // usunięcie złota z "sakiewki"
    public void lose(double value) {
        if (value < 0) throw new IllegalArgumentException("Nie można odjąć ujemnej wartości");
        if (this.amount - value < 0) throw new IllegalArgumentException("Zbyt mało złota!");
        this.amount -= value;
    }
}