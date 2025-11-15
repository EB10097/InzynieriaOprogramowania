package edu.io;

public class Board {

    //puste pole ・
    public static final String EMPTY_SYMBOL = "\u30FB"; // ・/u30FB

    // rozmiar planszy
    public final int size;

    // tablica pol dla planszy
    private final Token[][] grid;

    // konstruktor
    public Board(int size) {
        this.size = size;
        this.grid = new Token[size][size];
        clean();
    }

    // konstruktor dla testów
    public Board() {
        this(8); // Domyślny rozmiar 8
    }

    // czyści plansze ustawia wszystkie pola na pusty
    public void clean() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                // Zamiast null, wstawiamy prawdziwy obiekt Token
                grid[r][c] = new Token(EMPTY_SYMBOL);
            }
        }
    }

    // umieszcza żeton w polu
    public void placeToken(int col, int row, Token token) {
        grid[row][col] = token;
    }

   // zwraca żeton z pola
    public Token square(int col, int row) {
        return grid[row][col];
    }

   // wyświetla plansze
    public void display() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                // Pobieramy żeton
                Token token = grid[r][c];

                // Zawsze drukujemy etykietę
                System.out.print(" " + token.label + " ");
            }
            System.out.println(); // Nowa linia po każdym wierszu
        }
    }
}