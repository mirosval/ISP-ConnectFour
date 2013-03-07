
import java.util.TreeMap;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miroslav Zoricak
 */
public class Minimax {
    public static int decision(Board board, int maxPlayerID) {
        int width = board.getWidth();
        
        TreeMap<Integer, Integer>values = new TreeMap();
        
        for(int i = 0; i < width; i++) {
            if(!board.isValidMove(i)) continue;
            Board result = new Board(board);
            result.insert(i, maxPlayerID);
            values.put(minValue(result, 3 - maxPlayerID), i);
        }
        
        return values.get(values.lastKey());
    }
    
    public static int maxValue(Board board, int playerID) {
        if(board.getWinner() != Board.Winner.NOT_FINISHED) {
            return board.getUtility(1);
        }
        
        float value = Float.NEGATIVE_INFINITY;
        
        int width = board.getWidth();
        for(int i = 0; i < width; i++) {
            if(!board.isValidMove(i)) continue;
            Board result = new Board(board);
            result.insert(i, playerID);
            value = Math.max(value, minValue(result, 3 - playerID));
        }
        
        return (int)value;
    }
    
    public static int minValue(Board board, int playerID) {
        if(board.getWinner() != Board.Winner.NOT_FINISHED) {
            return board.getUtility(1);
        }
        
        float value = Float.NEGATIVE_INFINITY;
        
        int width = board.getWidth();
        for(int i = 0; i < width; i++) {
            if(!board.isValidMove(i)) continue;
            Board result = new Board(board);
            result.insert(i, playerID);
            value = Math.min(value, maxValue(result, 3 - playerID));
        }
        
        return (int)value;
    }
}
