package com.mysite;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static java.util.Arrays.asList;

public class SquareBoardTest {
    Board<Key, Integer> board;

    @BeforeEach
    public void initBoard()
    {
        board = new SquareBoard<>(2);
        board.fillBoard(asList(1,2,3,null));
    }

    @Test
    public void testFillBoard()
    {
        assertThrows(RuntimeException.class,()->board.fillBoard(asList(1,2,3)),"fillBoard not trows exception");
        assertEquals(board.availableSpace().get(0),board.getKey(1,1),"availableSpace not work =(");

        board.fillBoard(asList(5,6,7,8));
        assertEquals(board.availableSpace().isEmpty(),true,"fillBoard not correct work =(");
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


}
