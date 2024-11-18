package com.javarush.task.jdk13.task53.task5303;

import com.javarush.engine.cell.*;

public class Apple extends GameObject {
    private static final String APPLE_SIGN = "\uD83C\uDF4E";
    public Game game;
    public boolean isAlive = true;

    public Apple(int x, int y) {
        super(x, y);

    }


    public void draw(Game game) {
        game.setCellValueEx(this.x, this.y, Color.NONE, APPLE_SIGN, Color.GREENYELLOW, 75);
    }

}
