package com.mysite;

import java.util.*;

public class SquareBoard<V> extends Board<Key,V> {
    private int size;

    SquareBoard(int size)
    {
        super(size,size);
        this.size=size;
    }

    @Override
    public void fillBoard(List<V> list)
    {
        if((list.size()>size*size) || list.size()!=size*size)
            throw new RuntimeException("Application initialization error!");

            board.clear();

            for (int i=0;i<size; i++)
            {
                for (int j=0; j<size; j++)
                {
                    addItem(new Key(i,j),list.get(i * size + j));
                }
            }

    }

    @Override
    public  List<Key> availableSpace()
    {
        List<Key> list = new ArrayList<>();

        for (Key currentKey : board.keySet())
        {
            if (board.get(currentKey)==null)
                list.add(currentKey);
        }

        return list;
    }

    @Override
    public  void addItem(Key key, V value)
    {
        board.put(key,value);
    }

    @Override
    public  Key getKey(int i, int j)
    {
        for (Key currentKey : board.keySet())
        {
            if(i==currentKey.getI() && j==currentKey.getJ())
                return currentKey;
        }

        return null;
    }

    @Override
    public  V getValue(Key key)
    {
        return board.get(key);
    }

    @Override
    public  List<Key> getColumn(int j)
    {
        List<Key> list = new ArrayList<>();

        for (Key currentKey : board.keySet())
        {
            if(currentKey.getJ()==j)
                list.add(currentKey);
        }

        SortKeyOnColumn skonc = new SortKeyOnColumn();
        Collections.sort(list,skonc);

        return list;
    }

    @Override
    public  List<Key> getRow(int i)
    {
        List<Key> list = new ArrayList<>();

        for (Key currentKey : board.keySet())
        {
            if(currentKey.getI()==i)
                list.add(currentKey);
        }

        SortKeyOnRow skonr = new SortKeyOnRow();
        Collections.sort(list,skonr);

        return list;
    }

    @Override
    public  boolean hasValue(V value)
    {
        return board.containsValue(value);
    }

    @Override
    public  List<V> getValues(List<Key> keys)
    {
        List<V> list = new ArrayList<>();
        for (Key currentKey : keys)
        {
            if(board.containsKey(currentKey))
            {
                list.add(board.get(currentKey));
            }
        }
        return list;
    }
}