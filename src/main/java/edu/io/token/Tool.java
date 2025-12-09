package edu.io.token;

// interface dla narzędzi w grze z ich zachowaniem
public interface Tool {
    Tool useWith(Token token);
    Tool ifWorking(Runnable action);
    Tool ifBroken(Runnable action);
    Tool ifIdle(Runnable action);

    boolean isBroken();
}