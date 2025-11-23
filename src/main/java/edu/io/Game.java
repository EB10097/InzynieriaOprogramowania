package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.PyriteToken;
import java.util.Scanner;

// Klasa do zarządanią grą
public class Game {

    private final Board board;
    private final Player player;

    // inicjalizacja planszy, gracza i rozmieszczenie złota/pyritu
    public Game() {
        // 1. Tworzymy planszę i gracza
        this.board = new Board(8);
        this.player = new Player();

        // rozmieszczenie tokenów na planszy

        // Złoto (domyślna wartość 1.0)
        board.placeToken(1, 1, new GoldToken());
        board.placeToken(6, 6, new GoldToken(5.0));
        // Piryt (wartość 0.0) na polu (4,4)
        board.placeToken(4, 4, new PyriteToken());
        board.placeToken(2, 5, new GoldToken(2.5));
        board.placeToken(7, 0, new GoldToken(10.0));
    }


    //dołaczenie gracza do gry na wolne miejsce na planszy
    public void join(Player player) {
        var coords = board.getAvailableSquare();

        if (coords == null) {
            throw new IllegalStateException("Brak wolnych miejsc na planszy!");
        }

        PlayerToken token = new PlayerToken(board, coords.col(), coords.row());
        player.assignToken(token);
    }

    // uruchomienie gry (przeniesione z maina)
    public void start() {
        join(this.player);
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Witaj Zbieraj zloto i unikaj pirytu.");

        while (isRunning) {
            board.display();
            System.out.println("Złoto: " + player.gold());
            System.out.print("Ruch (w/s/a/d, q=wyjście): ");

            String input = scanner.nextLine().toLowerCase();
            PlayerToken.Move moveDir = PlayerToken.Move.NONE;

            switch (input) {
                case "w" -> moveDir = PlayerToken.Move.UP;
                case "s" -> moveDir = PlayerToken.Move.DOWN;
                case "a" -> moveDir = PlayerToken.Move.LEFT;
                case "d" -> moveDir = PlayerToken.Move.RIGHT;
                case "q" -> {
                    isRunning = false;
                    System.out.println("Koniec gry!");
                    System.out.println("tyle zebrałeś złota " + this.player.gold());
                }
            }

            if (moveDir != PlayerToken.Move.NONE) {
                try {
                    player.token().move(moveDir);
                } catch (IllegalArgumentException e) {
                    System.out.println("Błąd ruchu: " + e.getMessage());
                }
            }
        }
    }
}