package com.mysite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Game2048 implements Game {
    private GameHelper helper;
    private Random random;

    public static final int GAME_SIZE = 4;
    private static final int ITEM_VALUE_FOUR=4;
    private static final int ITEM_VALUE_TWO=2;
    private static final int MAX_ITEM_VALUE=2048;

    private final Board<Key, Integer> board;

    Game2048()
    {
        helper = new GameHelper();
        random = new Random();
        board = new SquareBoard<>(GAME_SIZE);
    }

    @Override
    public void init()
    {
        //инициализируем пустое игровое поле
        List<Integer> nullBoard = new ArrayList<>();

        for (int i=0;i<GAME_SIZE*GAME_SIZE;i++)
        {
            nullBoard.add(null);
        }

        //заполняем игровое поле
        board.fillBoard(nullBoard);

        //по правилам игры добавляем две цифры на игровое поле
        addTwoItem();
    }
    @Override
    public boolean canMove()
    {
        return board.availableSpace().size() > 0;
    }

    @Override
    public void move(Direction direction) throws GameOverException
    {
        if(!canMove()) throw new GameOverException();

        switch(direction){
            case RIGHT:
                moveToRight();
                addItem();
                break;
            case LEFT:
                moveToLeft();
                addItem();
                break;
            case UP:
                moveToUp();
                addItem();
                break;
            case DOWN:
                moveToDown();
                addItem();
                break;
            default:
                break;
        }
    }

    private void moveToDown()
    {
        for (int i=0; i<GAME_SIZE; i++)
        {
            List<Key> currentCol = board.getColumn(i);
            List<Integer> values = board.getValues(currentCol);
            Collections.reverse(values);
            List<Integer> newColValues = helper.moveAndMergeEqual(values);
            Collections.reverse(newColValues);

            for(int j=0; j<newColValues.size();j++)
            {
                board.addItem(currentCol.get(j),newColValues.get(j));
            }
        }
    }

    private void moveToUp()
    {
        for (int i=0; i<GAME_SIZE; i++)
        {
            List<Key> currentCol = board.getColumn(i);
            List<Integer> values = board.getValues(currentCol);
            List<Integer> newColValues = helper.moveAndMergeEqual(values);

            for(int j=0; j<newColValues.size();j++)
            {
                board.addItem(currentCol.get(j),newColValues.get(j));
            }
        }
    }

    private void moveToRight()
    {
        for (int i=0; i<GAME_SIZE; i++)
        {
            List<Key> currentRow = board.getRow(i);
            List<Integer> values = board.getValues(currentRow);
            Collections.reverse(values);
            List<Integer> newRowValues = helper.moveAndMergeEqual(values);
            Collections.reverse(newRowValues);

            for(int j=0; j<newRowValues.size();j++)
            {
                board.addItem(currentRow.get(j),newRowValues.get(j));
            }
        }
    }

    private void moveToLeft()
    {
        for (int i=0; i<GAME_SIZE; i++)
        {
            List<Key> currentRow = board.getRow(i);
            List<Integer> values = board.getValues(currentRow);
            List<Integer> newRowValues = helper.moveAndMergeEqual(values);

            for(int j=0; j<newRowValues.size();j++)
            {
                board.addItem(currentRow.get(j),newRowValues.get(j));
            }
        }
    }

    @Override
    public void addItem() throws GameOverException
    {
        if(!canMove()) throw new GameOverException();
        List<Key> freeCell = board.availableSpace();
        int itemValue = generateItemValue();
        int indexItem = random.nextInt(freeCell.size());
        board.addItem(freeCell.get(indexItem),itemValue);

    }

    public void addTwoItem()
    {
        List<Key> freeCell = board.availableSpace();

        int itemValue = generateItemValue();
        int indexItem = random.nextInt(freeCell.size());
        board.addItem(freeCell.get(indexItem),itemValue);

        itemValue = generateItemValue();
        indexItem = random.nextInt(freeCell.size());
        board.addItem(freeCell.get(indexItem),itemValue);
    }

    private int generateItemValue()
    {
        int value = random.nextInt(10);
        return (value<1)?ITEM_VALUE_FOUR:ITEM_VALUE_TWO;
    }

    @Override
    public Board getGameBoard()
    {
        return board;
    }

    @Override
    public boolean hasWin()
    {
        Integer maxValItem = ITEM_VALUE_TWO;

        //ищем максимальное значение клетки в игровом поле
        for (int i = 0; i < GAME_SIZE; i++)
        {
            for (int j = 0; j < GAME_SIZE; j++)
            {
                Key key = board.getKey(i, j);
                Integer value = board.getValue(key);

                if (value != null && value > maxValItem)
                {
                    maxValItem = value;
                }
            }
        }

        return maxValItem >= MAX_ITEM_VALUE;
    }
}


