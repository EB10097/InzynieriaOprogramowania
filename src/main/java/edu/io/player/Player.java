package edu.io.player;

import edu.io.token.*;

public class Player {

    // reprezentacja sakwy gracza
    public final Gold gold = new Gold();
    // reprezentacja szopki gracza
    private final Shed shed = new Shed();
    // parametry życiowe
    public final Vitals vitals = new Vitals();

    private PlayerToken token;

    // konstruktor gracza
    public Player() {
    }

    //zwraca aktualna ilosc zlota gracza
    public double gold() {
        return gold.amount();
    }

    //dodanie złota do sakwy gracza
    public void gainGold(double amount) {
        gold.gain(amount);
        System.out.println("Złoto: " + gold.amount());
    }

    // przypisanie pionka gracza
    public void assignToken(PlayerToken token) {
        if (token == null) {
            throw new NullPointerException("Token cannot be null");
        }
        this.token = token;
        this.token.setPlayer(this);
    }

    //zwraca pionek gracza
    public PlayerToken token() {
        return token;
    }

    // metoda interakcji gracza z tokenami na planszy
    public void interactWithToken(Token token) {
        if (token == null) {
            throw new NullPointerException("Token cannot be null");
        }
        // martwy gracz
        if (!vitals.isAlive()) {
            throw new IllegalStateException("Player is dead");
        }
        if (token instanceof EmptyToken) {
            // To naprawia test move_dehydrates
            vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
        }

        if (token instanceof PyriteToken) {
            System.out.println("To zloto glupcow, Glupcze + 0.0 zlota !!!");
            vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD); // zmęczenie dla pyritu
        }
        else if (token instanceof GoldToken goldToken) {
            vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD); // zmęczenie dla złota

            // pobranie narzędzia z szopy (góra stosu)
            Tool currentTool = shed.getTool();
            // sprawdzenie czy gracz ma kilof
            if (currentTool instanceof PickaxeToken pickaxe) {
                // użycie fluent interface
                pickaxe.useWith(goldToken)
                        // jeśli nie jest zepsuty
                        .ifWorking(() -> {
                            // sprawdzenie wartosci bonusu
                            double bonus = goldToken.amount() * (pickaxe.gainFactor() - 1.0);
                            gainGold(bonus);
                            System.out.println("BONUS za kilof: " + bonus);
                        })
                        .ifBroken(() -> { // akcja w przypadku uszkodzenia kilofa
                            System.out.println("Kilof jest ZEPSUTY! Idź do kowadla.");
                        });
            }

            // dodanie złota
            gainGold(goldToken.amount());
        } // obsługa wody
        else if (token instanceof WaterToken water){
            vitals.hydrate(water.amount());
            System.out.println("Napiłeś się takiej ilości wody:" + water.amount());
        }
        // obsługa podniesienia kilofa
        else if (token instanceof PickaxeToken foundPickaxe) {
            shed.add(foundPickaxe);
            System.out.println("Podniesiono kilof!");
        }
        // obsługa naprawy w kowadle
        else if (token instanceof AnvilToken) {
            vitals.dehydrate(VitalsValues.DEHYDRATION_ANVIL); // zmęczenie dla kowadła
            Tool tool = shed.getTool();

            // sprawdzenie czy narzędzie jest naprawialne
            if (tool instanceof Repairable repairableTool) {
                repairableTool.repair();
                System.out.println("Narzędzie naprawione w kowadle!");
            } else {
                System.out.println("Nie masz nic do naprawienia.");
            }
        }
    }
}