/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miroslav Zoricak
 */
public class ABSearchDepthCutoff {
    public static int Search(Board board, int depth, int playerID) {
        int[] result = Search(board, depth, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, playerID);
        return result[1];
    }
    
    private static int[] Search(Board board, int depth, float alpha, float beta, int playerID) {
//        System.out.println(playerID);
        Board.Winner winner = board.getWinner();
        if(depth == 0 || winner == Board.Winner.TIE) {
            int utility = board.getTotalUtilityForPlayer(playerID);
            if(utility != 0){
//                System.out.println("Utility: " + utility + " Depth: " + depth);
            }
            return new int[] {utility, -1};
        }
        
        int width = board.getWidth();
        
        int move = -1;
        
        if(playerID == 1) {
            for(int i = 0; i < width; i++) {
                if(!board.isValidMove(i)) continue;
                
                Board step = new Board(board);
                step.insert(i, playerID);
                int current = Search(step, depth - 1, alpha, beta, 3 - playerID)[0];
                
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
                int current = Search(step, depth - 1, alpha, beta, 3 - playerID)[0];
                
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
