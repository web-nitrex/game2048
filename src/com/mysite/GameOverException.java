package com.mysite;

public class GameOverException extends Exception{
    private String msg;
    GameOverException()
    {
        msg = "GameOverException!";
    }
    GameOverException(String msg)
    {
        this.msg="GameOverException: "+msg;
    }

    @Override
    public String toString()
    {
        return msg;
    }

}
