
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miroslav Zoricak
 */
public class Board {
    public enum Winner {PLAYER1, PLAYER2, TIE, NOT_FINISHED}
    public enum Tile {EMPTY, PLAYER1, PLAYER2}
    
    private int width = 0;
    private int height = 0;
    
    private Tile[][] board;
    
    public Board() {
        
    }
    
    public Board(Board b) {
        this.width = b.width;
        this.height = b.height;
        
        this.board = new Tile[height][width];
        
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                board[i][j] = b.board[i][j];
            }
        }
    }
    
    public void initBoard(int width, int height) {
        this.width = width;
        this.height = height;
                
        board = new Tile[height][width];
        
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                board[i][j] = Tile.EMPTY;
            }
        }
    }
    
    public boolean isValidMove(int column) {
        if(board[0][column] == Tile.EMPTY) {
            return true;
        } else {
            return false;
        }
    }
    
    public void insert(int column, int playerID) {
        if(!isValidMove(column)) return;
        
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
    
    public boolean isTerminal() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(board[i][j] == Tile.EMPTY) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public Winner getWinner() {
        for(int i = height - 1; i >= 0; i--) {
            for(int j = 0; j < width; j++) {
                Tile tile = board[i][j];
                if(tile == Tile.EMPTY) continue;
                
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
        
        if(isTerminal()) {
            return Winner.TIE;
        } else {
            return Winner.NOT_FINISHED;
        }
    }
    
    public int getUtility(int playerID) {
        Tile player = playerID == 1 ? Tile.PLAYER1 : Tile.PLAYER2;
        Tile opponent = playerID == 1 ? Tile.PLAYER2 : Tile.PLAYER2;
        
        int utility = 0;
        
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                Tile tile = board[i][j];

                // Check row
                if(j + 3 < width) {
                    int ours = 0;
                    for(int k = 1; k <= 3; k++) {
                        if(board[i][j + k] == opponent) {
                            ours = -1;
                            break;
                        }
                        
                        if(board[i][j + k] == player) ours++;
                    }
                    
                    if(ours > 0) {
                        utility += Math.pow(10, ours);
                    }
//                    System.out.println("[" + i + "][" + j + "]Row: " + ours);
                }
//                System.out.println(utility);
                // Check col
                if(i + 3 < height) {
                    int ours = 0;
                    for(int k = 1; k <= 3; k++) {
                        if(board[i + k][j] == opponent) {
                            ours = -1;
                            break;
                        }
                        
                        if(board[i + k][j] == player) ours++;
                    }
                    
                    if(ours > 0) {
                        utility += Math.pow(10, ours);
                    }
//                    System.out.println("[" + i + "][" + j + "]Col: " + ours);
                }
//                System.out.println(utility);
                // Check diag 1
                if(i + 3 < height && j + 4 < width) {
                    int ours = 0;
                    for(int k = 1; k < 4; k++) {
                        if(board[i + k][j + k] == opponent) {
                            ours = -1;
                            break;
                        }
                        
                        if(board[i + k][j + k] == player) ours++;
                    }
                    
                    if(ours > 0) {
                        utility += Math.pow(10, ours);
                    }
//                    System.out.println("[" + i + "][" + j + "]Di1: " + ours);
                }
//                System.out.println(utility);
                // Check diag 2
                if(i + 3 < height && j - 3 >= 0) {
                    int ours = 0;
                    for(int k = 1; k < 4; k++) {
                        if(board[i + k][j - k] == opponent) {
                            ours = -1;
                            break;
                        }
                        
                        if(board[i + k][j - k] == player) ours++;
                    }
                    
                    if(ours > 0) {
                        utility += Math.pow(10, ours);
                    }
//                    System.out.println("[" + i + "][" + j + "]Di2: " + ours);
                }
//                System.out.println(utility);
//                System.out.println("-");
            }
//            System.out.println("--");
        }
        
//        System.out.println("-------------------------------------------------");
        
        return utility;
    }
    
    public Tile[][] getBoard() {
        return board;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void print() {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(board[i][j] == Tile.EMPTY) {
                    System.out.print(" * ");
                } else if(board[i][j] == Tile.PLAYER1) {
                    System.out.print(" 1 ");
                } else if(board[i][j] == Tile.PLAYER2) {
                    System.out.print(" 2 ");
                }
            }
            System.out.println();
        }
    }
}
