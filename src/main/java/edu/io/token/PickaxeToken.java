package edu.io.token;

public class PickaxeToken extends Token implements Tool, Repairable {

    private final double gainFactor;
    private int durability;
    private final int maxDurability;
    private Token tokenToInteract; //użycie na danym żetonie


    public PickaxeToken() {
        this(1.5, 3);
    }

    public PickaxeToken(double gainFactor) {
        this(gainFactor, 3);
    }

    public PickaxeToken(int durability) {
        this(1.5, durability);
    }

    public PickaxeToken(double gainFactor, int durability) {
        super(Label.PICKAXE_TOKEN_LABEL);
        // walidacja dla testów
        if (gainFactor <= 0) {
            throw new IllegalArgumentException("Mnoznik dla zlota musi byc dodatni!");
        }
        if (durability <= 0) {
            throw new IllegalArgumentException("Wytrzymalosc musi byc dodatnia");
        }

        this.gainFactor = gainFactor;
        this.durability = durability;
        this.maxDurability = durability;
    }

    // mnożnik złota
    public double gainFactor() {
        return gainFactor;
    }

    public int durability() {
        return durability;
    }
    // zużywanie kilofa
    public void use() {
        if (durability > 0) {
            durability--;
        }
    }
    //sprawdzenie czy kilof jest zepsuty
    @Override
    public boolean isBroken() {
        return durability <= 0;
    }

    //naprawa kilofa
    @Override
    public void repair() {
        this.durability = this.maxDurability;
    }

    //wprowadzenie fluent interface

    @Override
    public Tool useWith(Token token) {
        this.tokenToInteract = token;
        return this;
    }
    // wykonanie akcji dla sprawnego kilofa
    @Override
    public Tool ifWorking(Runnable action) {
        if (!isBroken() && tokenToInteract instanceof GoldToken) {
             System.out.println(" Wytrzymałość kilofu : " + durability);
            action.run();
            this.use();
        } else {
            System.out.println("Kilof nie działa");
        }
        return this;
    }
    // akcja dla zużytego kilofa
    @Override
    public Tool ifBroken(Runnable action) {
        if (isBroken()) {
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifIdle(Runnable action) {
        if (!(tokenToInteract instanceof GoldToken)) {
            action.run();
        }
        return this;
    }
}