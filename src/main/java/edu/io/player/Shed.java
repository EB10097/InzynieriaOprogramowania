package edu.io.player;

import edu.io.token.Tool;
import java.util.Stack;

public class Shed {

    // Narzędzia przechowujemy na stosie LIFO
    private final Stack<Tool> tools = new Stack<>();

    // Umieść narzędzie w szopie
    public void add(Tool tool) {
        if (tool == null) {
            throw new IllegalArgumentException("Nie można dodać null do szopy");
        }
        tools.push(tool);
    }

    // Pobierz ostatnie narzędzie
    public Tool getTool() {
        if (tools.isEmpty()) {
            // Jeśli szopa jest pusta zwracamy notool dla gracza
            return new NoTool();
        }
        return tools.peek();
    }

    // Wyrzuć (zużyte) narzędzie z szopy (usuń)
    public void dropTool() {
        if (!tools.isEmpty()) {
            tools.pop();
        }
    }

    // Metoda pomocnicza dla szopy
    public boolean isEmpty() {
        return tools.isEmpty();
    }
}