/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author meosoft
 */
public class GameLogicTest {
    
    public GameLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initializeGame method, of class GameLogic.
     */
    @Test
    public void testInitializeGame() {
        System.out.println("initializeGame");
        
        GameLogic instance = new GameLogic();
        
        // 1x1
        instance.initializeGame(1, 1, 1);
        GameLogic.Tile[][] board = instance.getBoard();
        assertEquals(1, board.length);
        assertEquals(1, board[0].length);
        
        // 2x1
        instance.initializeGame(2, 1, 1);
        board = instance.getBoard();
        assertEquals(1, board.length);
        assertEquals(2, board[0].length);
        
        // 1x2
        instance.initializeGame(1, 2, 1);
        board = instance.getBoard();
        assertEquals(2, board.length);
        assertEquals(1, board[0].length);
    }

    /**
     * Test of gameFinished method, of class GameLogic.
     */
    @Test
    public void testGameFinished() {
        System.out.println("gameFinished");
        GameLogic instance = new GameLogic();
        
        instance.initializeGame(5, 5, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        
        // Columns
        instance.insertCoin(0, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        instance.insertCoin(0, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        instance.insertCoin(0, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        instance.insertCoin(0, 1);
        assertEquals(IGameLogic.Winner.PLAYER1, instance.gameFinished());
        
        instance.initializeGame(5, 5, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        
        // Rows
        instance.insertCoin(0, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        instance.insertCoin(1, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        instance.insertCoin(2, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        instance.insertCoin(3, 1);
        assertEquals(IGameLogic.Winner.PLAYER1, instance.gameFinished());
        
        instance.initializeGame(5, 5, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        
        // Diagonal
        instance.insertCoin(0, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        instance.insertCoin(1, 2);
        instance.insertCoin(1, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        instance.insertCoin(1, 2);
        instance.insertCoin(1, 2);
        instance.insertCoin(2, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        instance.insertCoin(1, 2);
        instance.insertCoin(1, 2);
        instance.insertCoin(1, 2);
        instance.insertCoin(3, 1);
        assertEquals(IGameLogic.Winner.PLAYER1, instance.gameFinished());
        
        instance.initializeGame(5, 5, 1);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        
        // Player 2
        instance.insertCoin(0, 2);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        instance.insertCoin(0, 2);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        instance.insertCoin(0, 2);
        assertEquals(IGameLogic.Winner.NOT_FINISHED, instance.gameFinished());
        instance.insertCoin(0, 2);
        assertEquals(IGameLogic.Winner.PLAYER2, instance.gameFinished());
        
        //TODO: Add Tie Detection
    }

    /**
     * Test of insertCoin method, of class GameLogic.
     */
    @Test
    public void testInsertCoin() {
        System.out.println("insertCoin");
        
        GameLogic instance = new GameLogic();
        
        // * * * *
        // * * * *
        // * * * *
        instance.initializeGame(4, 3, 1);
        
        // * * * *
        // * * * *
        // 1 * * *
        instance.insertCoin(0, 1);
        GameLogic.Tile[][] board = instance.getBoard();
        assertEquals(GameLogic.Tile.PLAYER1, board[2][0]);
        assertEquals(GameLogic.Tile.EMPTY, board[1][0]);
        assertEquals(GameLogic.Tile.EMPTY, board[2][1]);
        
        // * * * *
        // 1 * * *
        // 1 * * *
        instance.insertCoin(0, 1);
        board = instance.getBoard();
        assertEquals(GameLogic.Tile.PLAYER1, board[2][0]);
        assertEquals(GameLogic.Tile.PLAYER1, board[1][0]);
        assertEquals(GameLogic.Tile.EMPTY, board[0][0]);
        assertEquals(GameLogic.Tile.EMPTY, board[2][1]);
        
        // * * * *
        // 1 * * *
        // 1 1 * *
        instance.insertCoin(1, 1);
        board = instance.getBoard();
        assertEquals(GameLogic.Tile.PLAYER1, board[2][0]);
        assertEquals(GameLogic.Tile.PLAYER1, board[1][0]);
        assertEquals(GameLogic.Tile.EMPTY, board[0][0]);
        assertEquals(GameLogic.Tile.PLAYER1, board[2][1]);
        assertEquals(GameLogic.Tile.PLAYER1, board[2][1]);
    }

    /**
     * Test of decideNextMove method, of class GameLogic.
     */
    @Test
    public void testDecideNextMove() {
        System.out.println("decideNextMove");
        GameLogic instance = new GameLogic();
        
    }
}