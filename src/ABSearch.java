/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miroslav Zoricak
 */
public class ABSearch {
    public static int[] ABSearch(Board board, int depth, float alpha, float beta, int playerID) {
        if(depth == 0 || board.isTerminal()) {
            return new int[] {board.getUtility(playerID), -1};
        }
        
        int width = board.getWidth();
        
        int move = -1;
        
        if(playerID == 1) {
            for(int i = 0; i < width; i++) {
                if(!board.isValidMove(i)) continue;
                
                Board step = new Board(board);
                step.insert(i, playerID);
                int current = ABSearch(step, depth - 1, alpha, beta, 3 - playerID)[0];
                
                if(current > alpha) {
                    alpha = current;
                    move = i;
                }
                
                if(beta <= alpha) {
                    break;
                }
            }
            return new int[] {(int)alpha, move};
        } else {
            for(int i = 0; i < width; i++) {
                if(!board.isValidMove(i)) continue;
                
                Board step = new Board(board);
                step.insert(i, playerID);
                int current = ABSearch(step, depth - 1, alpha, beta, 3 - playerID)[0];
                
                if(current < beta) {
                    beta = current;
                    move = i;
                }
                
                if(beta <= alpha) {
                    break;
                }
            }
            return new int[] {(int)beta, move};
        }
    }
}
