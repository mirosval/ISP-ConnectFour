
import java.util.ArrayList;


public class GameLogic implements IGameLogic {
    public enum Tile {EMPTY, PLAYER1, PLAYER2}
    
    private int width = 0;
    private int height = 0;
    private int playerID;
    
    private Tile[][] board;
    
    public GameLogic() {
        //TODO Write your implementation for this method
    }
	
    public void initializeGame(int width, int height, int playerID) {
        this.width = width;
        this.height = height;
        this.playerID = playerID;
        
        board = new Tile[height][width];
        
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                board[i][j] = Tile.EMPTY;
            }
        }
    }
	
    public Winner gameFinished() {
        int empty = 0;
        for(int i = height - 1; i >= 0; i--) {
            for(int j = 0; j < width; j++) {
                Tile tile = board[i][j];
                if(tile == Tile.EMPTY) {
                    empty++;
                    continue;
                }
                
                // Check row
                if(j + 3 < width) {
                    if(tile == board[i][j + 1] &&
                       tile == board[i][j + 2] &&
                       tile == board[i][j + 3]) {
                        return tile == Tile.PLAYER1 ? Winner.PLAYER1 : Winner.PLAYER2;
                    }
                }
                
                // Check col
                if(i - 3 >= 0) {
                    if(tile == board[i - 1][j] &&
                       tile == board[i - 2][j] &&
                       tile == board[i - 3][j]) {
                        return tile == Tile.PLAYER1 ? Winner.PLAYER1 : Winner.PLAYER2;
                    }
                }
                
                // Check diag 1
                if(i - 3 >= 0 && j + 3 < width) {
                    if(tile == board[i - 1][j + 1] &&
                       tile == board[i - 2][j + 2] &&
                       tile == board[i - 3][j + 3]) {
                        return tile == Tile.PLAYER1 ? Winner.PLAYER1 : Winner.PLAYER2;
                    }
                }
                
                // Check diag 2
                if(i - 3 >= 0 && j - 3 >= 0) {
                    if(tile == board[i - 1][j - 1] &&
                       tile == board[i - 2][j - 2] &&
                       tile == board[i - 3][j - 3]) {
                        return tile == Tile.PLAYER1 ? Winner.PLAYER1 : Winner.PLAYER2;
                    }
                }
            }
        }
        
        if(empty == 0) {
            return Winner.TIE;
        } else {
            return Winner.NOT_FINISHED;
        }
    }


    public void insertCoin(int column, int playerID) {
        int i = height;
        
        do {
            i--;
        } while(board[i][column] != Tile.EMPTY);
        
        if(i < 0) {
            return;
        }
        
        if(playerID == 1) {
            board[i][column] = Tile.PLAYER1;
        } else {
            board[i][column] = Tile.PLAYER2;
        }
    }

    public int decideNextMove() {
        //TODO Write your implementation for this method
        return 0;
    }
    
    public Tile[][] getBoard() {
        return board;
    }
    
    public void printBoard() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(board[i][j] == Tile.EMPTY) {
                    System.out.print("* ");
                } else if(board[i][j] == Tile.PLAYER1) {
                    System.out.print("1 ");
                } else if(board[i][j] == Tile.PLAYER2) {
                    System.out.print("2 ");
                }
            }
            System.out.println();
        }
    }

}
