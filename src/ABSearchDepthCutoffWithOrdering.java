/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Comparator;
import java.util.TreeSet;

/**
 *
 * @author Miroslav Zoricak
 */
public class ABSearchDepthCutoffWithOrdering {
    


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
        BoardComparator boardComparator = new BoardComparator();
        
        boardComparator.setPlayerID(playerID);
        TreeSet<Board> boards = new TreeSet<>(boardComparator);

        for(int i = 0; i < width; i++) {
            if(!board.isValidMove(i)) continue;

            Board step = new Board(board);
            step.insert(i, playerID);

            boards.add(step);
        }
        
        if(playerID == 1) {
            for(Board step : boards) {
                int lastMove = step.getLastMove();
                int current = Search(step, depth - 1, alpha, beta, 3 - playerID)[0];
                
                if(current > alpha) {
                    alpha = current;
                    move = lastMove;
                }
                
                if(beta <= alpha) {
                    break;
                }
            }
            return new int[] {(int)alpha, move};
        } else {
            for(Board step : boards) {
                int lastMove = step.getLastMove();
                int current = Search(step, depth - 1, alpha, beta, 3 - playerID)[0];
                
                if(current < beta) {
                    beta = current;
                    move = lastMove;
                }
                
                if(beta <= alpha) {
                    break;
                }
            }
            return new int[] {(int)beta, move};
        }
    }
    
    static class BoardComparator implements Comparator {
        private int playerID;

        public void setPlayerID(int playerID) {
            this.playerID = playerID;
        }
        
        @Override
        public int compare(Object o1, Object o2) {
            Board b1 = (Board)o1;
            Board b2 = (Board)o2;
            
            Integer utility1 = b1.getTotalUtilityForPlayer(playerID);
            Integer utility2 = b2.getTotalUtilityForPlayer(playerID);

            return utility1.compareTo(utility2);
        }
        
    }
}
