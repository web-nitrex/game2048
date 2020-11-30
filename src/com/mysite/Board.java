package com.mysite;

import java.util.*;

public abstract class Board<K,V> {
    private int weigh;
    private int height;
    protected Map<K, V> board;

    Board(int weigh, int height)
    {
        this.weigh=weigh;
        this.height=height;
        board = new HashMap<>();
    }

    public abstract void fillBoard(List<V> list);
    public abstract List<K> availableSpace();
    public abstract void addItem(K key, V value);
    public abstract K getKey(int i, int j);
    public abstract V getValue(K key);
    public abstract List<K> getColumn(int j);
    public abstract List<K> getRow(int i);
    public abstract boolean hasValue(V value);
    public abstract List<V> getValues(List<K> keys);
}

class Key{
    private int i;
    private int j;

    Key(int i, int j)
    {
        this.setI(i);
        this.setJ(j);
    }

    public int getI() {
        return i;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return i == key.i &&
                j == key.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}

class SortKeyOnColumn implements Comparator<Key>
{
    @Override
    public int compare(Key o1, Key o2) {
        return o1.getI() < o2.getI() ? -1 : 1;
    }
}

class SortKeyOnRow implements Comparator<Key>
{
    @Override
    public int compare(Key o1, Key o2) {
        return o1.getJ() < o2.getJ() ? -1 : 1;
    }
}





