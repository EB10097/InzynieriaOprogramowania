package edu.io;

//cała "gra" została przeniesiona do klasy Game
public class Main {

        public static void main(String[] args) {
            // Tworzymy nową instancję gry (to tutaj tworzy się plansza i gracz)
            Game game = new Game();

            // Uruchamiamy pętlę gry
            game.start();
        }
    }