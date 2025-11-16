package edu.io;

// Importujemy klasy, których potrzebujemy do działania gry
import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //  Inicjalizacja gry poprzez zrobienie planszy w tym przypadku 8x8
        Board board = new Board(8);

        // Stwórzmy gracza i umieśćmy go na (0, 0)
        PlayerToken player = new PlayerToken(board, 0, 0);

        // Dodajmy kilka żetonów złota dla testu
        board.placeToken(3, 3, new GoldToken());
        board.placeToken(5, 7, new GoldToken());
        board.placeToken(1, 6, new GoldToken());

        // Użyjemy Scannera do zaczytywania poleceń
        Scanner scanner = new Scanner(System.in);
        String command;

        // 2. Nieskończona pętla gry (system turowy)
        while (true) {
            board.display();
            System.out.println("Twoj ruch (w=gora, s=dol, a=lewo, d=prawo):");

            // zaczytanie
            command = scanner.nextLine();

            // poruszanie się
            try {
                // Wykonujemy ruch na podstawie komendy
                switch (command.toLowerCase()) {
                    case "w": // Góra
                        player.move(PlayerToken.Move.UP);
                        break;
                    case "s": // Dół
                        player.move(PlayerToken.Move.DOWN);
                        break;
                    case "a": // Lewo
                        player.move(PlayerToken.Move.LEFT);
                        break;
                    case "d": // Prawo
                        player.move(PlayerToken.Move.RIGHT);
                        break;
                    default:
                        System.out.println("Nieznana komenda: " + command); // w przypadku wpisania innego polecenia
                        break;
                }
            } catch (IllegalArgumentException e) {
                // na wypadek jakby gracz chciał wyjść poza naszą planszę
                System.out.println("Nie mozesz tam isc! " + e.getMessage());
            }

            System.out.println("-------------------------------------");
        }
    }
}