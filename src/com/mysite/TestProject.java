package com.mysite;

public class TestProject {
    public static void main(String[] args)
    {
        Board board = new SquareBoard(4);
        Game game2048 = new Game2048();
        System. out .println(game2048.canMove());
    }
}
