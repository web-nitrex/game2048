package com.mysite;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static java.util.Arrays.asList;
import org.junit.jupiter.api.Nested;

public class SquareBoardTest {
    private Board<Key, Integer> board;

    @BeforeEach
    public void initBoard()
    {
        board = new SquareBoard<>(2);
        board.fillBoard(asList(1,2,3,null));
    }

    @Nested
    class TestFillBoard{

        @BeforeEach
        public void init()
        {
            board = new SquareBoard<>(2);
        }

        @Test
        public void testTrowsExceptionFillBoard()
        {
            assertThrows(RuntimeException.class,()->board.fillBoard(asList(1,2,3)),"fillBoard not trows exception");
        }

        @Test
        public void testFillBoard()
        {
            board.fillBoard(asList(5,6,7,8));
            assertEquals(board.availableSpace().isEmpty(),true,"fillBoard not correct work =(");
        }
    }

   @Test
   public void testAvailableSpace()
   {
       assertEquals(board.availableSpace().get(0),board.getKey(1,1),"availableSpace not work =(");
   }

    @Test
    public void testGetKey()
    {
        assertEquals(board.getKey(0,0),board.getKey(0, 0),"Keys must be same");
        assertEquals(board.getKey(2,2),null,"Not found key must be null");
    }

    @Test
    public void testGetValue()
    {
        assertEquals(board.getValue(board.getKey(1, 1)),null,"getValue not work =(");
    }

    @Test
    public void testHasValue()
    {
        assertEquals(board.hasValue(null),true,"hasValue not work =(");
        assertEquals(board.hasValue(3),true,"hasValue not work =(");
    }

    @Test
    public void testGetColumn()
    {
        assertEquals(board.getColumn(0), asList(board.getKey(0,0), board.getKey(1,0)),"getColumn not work =(");
    }

    @Test
    public void testGetRow()
    {
        assertEquals(board.getRow(0), asList(board.getKey(0,0), board.getKey(0,1)),"getRow not work =(");
    }

    @Test
    public void testGetValues()
    {
        assertEquals(board.getValues(board.getColumn(0)), asList(1, 3), "getValues not work =(");
    }
}
