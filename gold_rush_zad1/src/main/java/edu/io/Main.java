package edu.io;


public class Main {

    public static void main(String[] args) {
        System.out.println("> Task :edu.io.Main.main()");
        System.out.println();

        /* konsola nie chce mi wyświetlić podanych przez Pana znaków więc
        tymczasowo dopóki nie rozwiążę z nimi problemami (zakładam że to wina windowsa, znajomy na macku nie ma tego problemu)
         zostawie je w taki sposób Player = P, Gold = G jak, w przypadku pustego miejsca użyłem symbolu podanego przez Pana  */
        String playerSymbol = "P"; // \uC6C3
        String goldSymbol = "G"; //  \uD83D\uDCB0

        // Tworzymy żetony
        Token playerToken = new Token(playerSymbol);
        Token goldToken = new Token(goldSymbol);

        // pokazuje planszę 6x6
        int boardSize = 6;
        Board board = new Board(boardSize);

        // Umieszczamy żetony testowo
        board.placeToken(1, 3, playerToken);
        board.placeToken(4, 4, goldToken);

        // Wyświetlamy planszę
        board.display();
    }
}