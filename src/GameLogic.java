
import java.util.ArrayList;


public class GameLogic implements IGameLogic {
    public enum Tile {EMPTY, PLAYER1, PLAYER2}
    
    private int x = 0;
    private int y = 0;
    private int playerID;
    
    private Tile[][] board;
    
    public GameLogic() {
        //TODO Write your implementation for this method
    }
	
    public void initializeGame(int x, int y, int playerID) {
        this.x = x;
        this.y = y;
        this.playerID = playerID;
        
        board = new Tile[y][x];
        
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                board[i][j] = Tile.EMPTY;
            }
        }
    }
	
    public Winner gameFinished() {
        //TODO Write your implementation for this method
        return Winner.NOT_FINISHED;
    }


    public void insertCoin(int column, int playerID) {
        int i = y;
        
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
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                if(board[i][j] == Tile.EMPTY) {
                    System.out.print("*");
                } else if(board[i][j] == Tile.PLAYER1) {
                    System.out.print("1");
                } else if(board[i][j] == Tile.PLAYER2) {
                    System.out.print("2");
                }
            }
            System.out.println();
        }
    }

}
