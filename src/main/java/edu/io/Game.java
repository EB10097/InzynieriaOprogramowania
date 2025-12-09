package edu.io;

import edu.io.player.VitalsValues;
import edu.io.player.Player;
import edu.io.token.*;

import java.util.Scanner;

// Klasa do zarządanią grą
public class Game {

    private final Board board;
    private final Player player;

    private boolean isRunning = true;

    // inicjalizacja planszy, gracza i rozmieszczenie złota/pyritu
    public Game() {
        // 1. Tworzymy planszę i gracza
        this.board = new Board(8);
        this.player = new Player();

        player.vitals.setOnDeathHandler(()->{
            System.out.println("umrałeś z pragnienia!");
            isRunning = false;
        });


        // Złoto (domyślna wartość 1.0)
        board.placeToken(1, 1, new GoldToken());
        board.placeToken(2, 2, new GoldToken(5.0));
        // Piryt (wartość 0.0) na polu (4,4)
        board.placeToken(3, 3, new PyriteToken());
        board.placeToken(4, 4, new GoldToken(2.5));
        board.placeToken(5, 5, new GoldToken(10.0));
        board.placeToken(3, 1, new AnvilToken());
        board.placeToken(4, 1, new PickaxeToken(2.0, 3));
        board.placeToken(3, 2, new WaterToken(10));
    }


    //dołaczenie gracza do gry na wolne miejsce na planszy
    public void join(Player player) {
        var coords = board.getAvailableSquare();

        if (coords == null) {
            throw new IllegalStateException("Brak wolnych miejsc na planszy!");
        }

        PlayerToken token = new PlayerToken(player, board);
        player.assignToken(token);
    }

    // uruchomienie gry (przeniesione z maina)
    public void start() {
        join(this.player);
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Witaj Zbieraj zloto, unikaj pirytu i nie umrzyj z wycieńczenia.");

        while (isRunning) {
            board.display();
            System.out.println("Złoto: " + player.gold() + " Woda: " + player.vitals.hydration() + "%");
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

            if (isRunning && moveDir != PlayerToken.Move.NONE) {
                try {

                    // co jeśli nadal żyjemy?
                    if (player.vitals.isAlive()) {
                        player.token().move(moveDir);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Błąd ruchu: " + e.getMessage());
                }
            }
        }
    }
}