package edu.io.token; 

// importy dla klas
import edu.io.Board;
import edu.io.player.Player;
import java.util.Objects;


//Pionek gracza
public class PlayerToken extends Token {

    //zdefiniowanie możliwych kierunków ruchu
    public enum Move {
        NONE,
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private final Board board; // po stworzeniu planszy pionek nie może zmienić planszy
    private int col; // współrzędna x
    private int row; // współrzędna y
    private Player player; // referencja do obiektu gracza

    // główny konstruktor tworzy pionka gracza na danej pozycji
    public PlayerToken(Board board, int col, int row) {
        super(Label.PLAYER_TOKEN_LABEL); // przekazuje symbol gracza
        this.board = Objects.requireNonNull(board, "Board cannot be null");
        this.col = col;
        this.row = row;
        board.placeToken(this.col, this.row, this); // po stworzeniu pionka odrazu zostaje umieszczony na planszy
    }


    public PlayerToken(Player player, edu.io.Board board) {
        // Wywołujemy nasz główny konstruktor, ustawiając pionek na (0, 0)
        this(board, 0, 0);
        this.player = Objects.requireNonNull(player, "Player cannot be null");
    }

    public void setPlayer(Player player) {
        this.player = Objects.requireNonNull(player, "Player cannot be null");
    }
    // zwraca aktualna pozycję pionka/gracza
    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }


    // służy do przesuwania pionka o 1 jednostkę w danym kierunku, dodatkowo dba aby gracz nie mógł wyjśc poza planszę
    public void move(Move dir) {
        int newCol = col;
        int newRow = row;

        switch (dir) {
            case NONE: break;
            case UP: newRow--; break;
            case DOWN: newRow++; break;
            case LEFT: newCol--; break;
            case RIGHT: newCol++; break;
        }
        // jeśli ten ruch jest "legalny to wykorzystujemy go"
        if (newCol < 0 || newCol >= board.size() || newRow < 0 || newRow >= board.size()) {
            throw new IllegalArgumentException("nie mozna wyjsc poza plansze");
        }

        // czyścimy starą pozycję pionka poprzez wstawienie tam pustego pola
        board.placeToken(col, row, new EmptyToken());

        Token tokenAtNewPos = board.peekToken(newCol, newRow);

        if (player != null) {
            player.interactWithToken(tokenAtNewPos);
        }

        // aktualizacja pozycji pionka
        this.col = newCol;
        this.row = newRow;
        // umieszczamy pionka na nowej pozycji
        board.placeToken(this.col, this.row, this);
    }
}