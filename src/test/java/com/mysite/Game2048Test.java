package com.mysite;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.List;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
public class Game2048Test {

    @InjectMocks
    private Game2048 game = new Game2048();

    @Spy
    private GameHelper helper;

    @Spy
    private Random random;

    @Mock
    private SquareBoard<Integer> board;

    @Test
    public void testInit()
    {
        when(board.availableSpace()).thenReturn(asList(new Key(0,0),new Key(0,1)));
        game.init();
        verify(board,times(1)).fillBoard(asList(null, null, null, null,
                                                                      null, null, null, null,
                                                                      null, null, null, null,
                                                                      null, null, null, null));

    }

    @Test
    public void testCanMove()
    {
        when(board.availableSpace()).thenReturn(asList(new Key(0,0),new Key(0,1)));
        assertEquals(game.canMove(),true,"canMove not work =(");
    }

    @Test
    public void testMove() throws GameOverException {
        //для того что бы корректно отработал метод canMove
        when(board.availableSpace()).thenReturn(asList(new Key(0,0),new Key(0,1)));

        game.move(Direction.DOWN);

        verify(board,times(Game2048.GAME_SIZE)).getColumn(Mockito.anyInt());
        verify(board,times(Game2048.GAME_SIZE)).getValues(Mockito.anyList());
        verify(helper,times(Game2048.GAME_SIZE)).moveAndMergeEqual(anyList());

        verify(board,atLeast(1)).addItem(Mockito.any(Key.class),Mockito.any(Integer.class));

    }

}
