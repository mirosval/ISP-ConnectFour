/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miroslav Zoricak
 */
public class ABSearch {
    public static int ABSearch(Board board, int depth, float alpha, float beta, int playerID) {
        if(depth == 0 || board.isTerminal()) {
            return board.getUtility(playerID);
        }
        
        int width = board.getWidth();
        
        if(playerID == 1) {
            for(int i = 0; i < width; i++) {
                Board step = new Board(board);
                step.insert(i, playerID);
                alpha = Math.max(alpha, ABSearch(step, depth - 1, alpha, beta, 3 - playerID));
                if(beta <= alpha) {
                    break;
                }
            }
            return (int)alpha;
        } else {
            for(int i = 0; i < width; i++) {
                Board step = new Board(board);
                step.insert(i, playerID);
                beta = Math.min(beta, ABSearch(step, depth - 1, alpha, beta, 3 - playerID));
                if(beta <= alpha) {
                    break;
                }
            }
            return (int)beta;
        }
    }
}
