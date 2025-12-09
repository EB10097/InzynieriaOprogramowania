package edu.io.player;

import edu.io.token.Token;
import edu.io.token.Tool;

public class NoTool implements Tool {
    // użycie z czym
    @Override
    public Tool useWith(Token token) {
        return this;
    }
    // jeśli działa
    @Override
    public Tool ifWorking(Runnable action) {
        return this;
    }
    // jeśli zepsuty
    @Override
    public Tool ifBroken(Runnable action) {
        return this;
    }
    // naprawa
    @Override
    public Tool ifIdle(Runnable action) {
        return this;
    }
    // puste narzędzie nie jest zepsute
    @Override
    public boolean isBroken() {
        return false;
    }
}