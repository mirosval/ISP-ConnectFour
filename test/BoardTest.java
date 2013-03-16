/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miroslav Zoricak
 */
public class BoardTest {
    
    public BoardTest() {
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
     * Test of initializeBoard method, of class Board.
     */
    @Test
    public void testInitBoard() {
        System.out.println("initBoard");
        Board instance = new Board();
        
        // 1x1
        instance.initBoard(1, 1);
        Board.Tile[][] board = instance.getBoard();
        assertEquals(1, board.length);
        assertEquals(1, board[0].length);
        
        // 2x1
        instance.initBoard(2, 1);
        board = instance.getBoard();
        assertEquals(1, board.length);
        assertEquals(2, board[0].length);
        
        // 1x2
        instance.initBoard(1, 2);
        board = instance.getBoard();
        assertEquals(2, board.length);
        assertEquals(1, board[0].length);
    }

    /**
     * Test of getWinner method, of class Board.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        Board instance = new Board();
        
        instance.initBoard(5, 5);
        assertEquals(false, instance.isTerminal());
        
        // Columns
        instance.insert(0, 1);
        assertEquals(false, instance.isTerminal());
        instance.insert(0, 1);
        assertEquals(false, instance.isTerminal());
        instance.insert(0, 1);
        assertEquals(false, instance.isTerminal());
        instance.insert(0, 1);
        assertEquals(Board.Winner.PLAYER1, instance.getWinner());
        
        instance.initBoard(5, 5);
        assertEquals(false, instance.isTerminal());
        
        // Rows
        instance.insert(0, 1);
        assertEquals(false, instance.isTerminal());
        instance.insert(1, 1);
        assertEquals(false, instance.isTerminal());
        instance.insert(2, 1);
        assertEquals(false, instance.isTerminal());
        instance.insert(3, 1);
        assertEquals(Board.Winner.PLAYER1, instance.getWinner());
        
        instance.initBoard(5, 5);
        assertEquals(false, instance.isTerminal());
        
        // Diagonal
        instance.insert(0, 1);
        assertEquals(false, instance.isTerminal());
        instance.insert(1, 2);
        instance.insert(1, 1);
        assertEquals(false, instance.isTerminal());
        instance.insert(2, 2);
        instance.insert(2, 2);
        instance.insert(2, 1);
        assertEquals(false, instance.isTerminal());
        instance.insert(3, 2);
        instance.insert(3, 2);
        instance.insert(3, 2);
        instance.insert(3, 1);
        assertEquals(Board.Winner.PLAYER1, instance.getWinner());
        
        instance.initBoard(5, 5);
        assertEquals(false, instance.isTerminal());
        
        // Other Diagonal
        instance.insert(3, 1);
        assertEquals(false, instance.isTerminal());
        instance.insert(2, 2);
        instance.insert(2, 1);
        assertEquals(false, instance.isTerminal());
        instance.insert(1, 2);
        instance.insert(1, 2);
        instance.insert(1, 1);
        assertEquals(false, instance.isTerminal());
        instance.insert(0, 2);
        instance.insert(0, 2);
        instance.insert(0, 2);
        instance.insert(0, 1);
        assertEquals(Board.Winner.PLAYER1, instance.getWinner());
        
        instance.initBoard(5, 5);
        assertEquals(false, instance.isTerminal());
        
        // Player 2
        instance.insert(0, 2);
        assertEquals(false, instance.isTerminal());
        instance.insert(0, 2);
        assertEquals(false, instance.isTerminal());
        instance.insert(0, 2);
        assertEquals(false, instance.isTerminal());
        instance.insert(0, 2);
        assertEquals(Board.Winner.PLAYER2, instance.getWinner());
        
        instance.initBoard(5, 5);
        assertEquals(false, instance.isTerminal());
        
        // Tie
        instance.insert(0, 2);
        instance.insert(1, 1);
        instance.insert(2, 1);
        instance.insert(3, 1);
        instance.insert(4, 2);
        assertEquals(false, instance.isTerminal());
        
        instance.insert(0, 2);
        instance.insert(1, 1);
        instance.insert(2, 1);
        instance.insert(3, 1);
        instance.insert(4, 2);
        assertEquals(false, instance.isTerminal());
        
        instance.insert(0, 1);
        instance.insert(1, 2);
        instance.insert(2, 2);
        instance.insert(3, 2);
        instance.insert(4, 1);
        assertEquals(false, instance.isTerminal());
        
        instance.insert(0, 2);
        instance.insert(1, 1);
        instance.insert(2, 1);
        instance.insert(3, 1);
        instance.insert(4, 2);
        assertEquals(false, instance.isTerminal());
        
        instance.insert(0, 2);
        instance.insert(1, 1);
        instance.insert(2, 1);
        instance.insert(3, 1);
        instance.insert(4, 2);
        assertEquals(Board.Winner.TIE, instance.getWinner());
    }

    /**
     * Test of insertCoin method, of class Board.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        
        Board instance = new Board();
        
        // * * * *
        // * * * *
        // * * * *
        instance.initBoard(4, 3);
        
        // * * * *
        // * * * *
        // 1 * * *
        instance.insert(0, 1);
        Board.Tile[][] board = instance.getBoard();
        assertEquals(Board.Tile.PLAYER1, board[2][0]);
        assertEquals(Board.Tile.EMPTY, board[1][0]);
        assertEquals(Board.Tile.EMPTY, board[2][1]);
        
        // * * * *
        // 1 * * *
        // 1 * * *
        instance.insert(0, 1);
        board = instance.getBoard();
        assertEquals(Board.Tile.PLAYER1, board[2][0]);
        assertEquals(Board.Tile.PLAYER1, board[1][0]);
        assertEquals(Board.Tile.EMPTY, board[0][0]);
        assertEquals(Board.Tile.EMPTY, board[2][1]);
        
        // * * * *
        // 1 * * *
        // 1 1 * *
        instance.insert(1, 1);
        board = instance.getBoard();
        assertEquals(Board.Tile.PLAYER1, board[2][0]);
        assertEquals(Board.Tile.PLAYER1, board[1][0]);
        assertEquals(Board.Tile.EMPTY, board[0][0]);
        assertEquals(Board.Tile.PLAYER1, board[2][1]);
        assertEquals(Board.Tile.PLAYER1, board[2][1]);
    }
    
    @Test
    public void testGetUtility() {
        System.out.println("getUtility");
        
        Board instance = new Board();
        
        // * * * *
        // * * * *
        // * * * *
        instance.initBoard(4, 3);
        assertEquals(0, instance.getPartialUtilityForPlayer(1));
        
        // * * * * *
        // * | * * /
        // * | * / *
        // * | / * *
        // * 1 2 * *
        instance.initBoard(5, 5);
        instance.insert(1, 1);
        instance.insert(2, 2);
        assertEquals(20, instance.getPartialUtilityForPlayer(1));
        
        // * * * * *
        // \ | * | /
        // * + * + *
        // * | + | *
        // * 1 2 1 2
        instance.initBoard(5, 5);
        instance.insert(1, 1);
        instance.insert(2, 2);
        instance.insert(3, 1);
        instance.insert(4, 2);
        assertEquals(40, instance.getPartialUtilityForPlayer(1));
        
        // * | * * /
        // * | * / /
        // * + / / *
        // - 1 + - -
        // * 1 2 2 *
        instance.initBoard(5, 5);
        instance.insert(1, 1);
        instance.insert(2, 2);
        instance.insert(1, 1);
        instance.insert(3, 2);
        assertEquals(150, instance.getPartialUtilityForPlayer(1));
        
        // * * | * *
        // \ | | * /
        // * \ | / *
        // - + 1 + -
        // * 1 2 \ *
        instance.initBoard(5, 5);
        instance.insert(1, 1);
        instance.insert(2, 2);
        instance.insert(2, 1);
//        instance.print();
        assertEquals(150, instance.getPartialUtilityForPlayer(1));
        
        // * * | | *
        // \ | | | /
        // - + + 1 -
        // - + 1 1 -
        // * 1 2 2 2
        instance.initBoard(5, 5);
        instance.insert(1, 1);
        instance.insert(2, 2);
        instance.insert(2, 1);
        instance.insert(3, 2);
        instance.insert(3, 1);
        instance.insert(4, 2);
        instance.insert(3, 1);
//        instance.print();
        assertEquals(1350, instance.getPartialUtilityForPlayer(1));
    }
}