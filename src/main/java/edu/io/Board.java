package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board {

    // stała reprezentująca pusty żeton tworzony jest tylko jeden obiekt na całą plansze
    private static final Token EMPTY_TOKEN = new EmptyToken();
    private final int size; // rozmiar planszy
    private final Token[][] grid; // tablica dwuwymiarowa przechowująca wszystkie żetony na planszy

    public record Coords(int col, int row) {} // do przechowywania współrzędnych

    //głowny konstruktor planszy, w skrócie bierzę rozmiar planszy i go buduje dodatkowo wypełnia ją pustymi żetonami
    // za pomocą metody clean()
    public Board(int size) {
        this.size = size;
        this.grid = new Token[size][size];
        clean();
    }

    // dodany dla testów
    public Board() {
        this(8);
    }
    // dodany dla testów zwraca rozmiar planszy
    public int size() {
        return this.size;
    }
    // wypełnienie plaszny pustymi żetonami
    public void clean() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = EMPTY_TOKEN;
            }
        }
    }
    // umieszczenie dany żetonu na podanym miejscu
    public void placeToken(int col, int row, Token token) {
        grid[row][col] = token;
    }

    // zwraca pozycję danego żetonu na planszy
    public Token peekToken(int col, int row) {
        return grid[row][col];
    }
    // wyświetlenie planszy na konsoli
    public void display() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Token token = grid[r][c];
                System.out.print(" " + token.label() + " ");
            }
            System.out.println();
        }
    }
}