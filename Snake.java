package com.javarush.task.jdk13.task53.task5303;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "⚫";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;



    public Snake(int x, int y) {
        GameObject gameObject1 = new GameObject(x, y);
        GameObject gameObject2 = new GameObject(x + 1, y);
        GameObject gameObject3 = new GameObject(x + 2, y);

        snakeParts.add(gameObject1);
        snakeParts.add(gameObject2);
        snakeParts.add(gameObject3);

    }
    public void draw(Game game) {
        for (int i = 0; i < snakeParts.size(); i++) {
            if (isAlive) {
                if (i == 0) {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, Color.BLACK, 75);
                } else {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.BLACK, 75);
                }
            } else {
                if (i == 0) {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, Color.RED, 75);
                } else {
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.RED, 75);
                }
            }
        }
    }

    public int getLength(){
        return snakeParts.size();
    }

    public GameObject createNewHead() {
        if (direction == Direction.LEFT) {
            return new GameObject(snakeParts.get(0).x - 1, snakeParts.get(0).y);
        } else if (direction == Direction.RIGHT) {
            return new GameObject(snakeParts.get(0).x + 1, snakeParts.get(0).y);
        }else if (direction == Direction.UP) {
            return new GameObject(snakeParts.get(0).x, snakeParts.get(0).y - 1);
        } else {
            return new GameObject(snakeParts.get(0).x, snakeParts.get(0).y + 1);
        }

    }
    public void removeTail() {
        int tailIndex = snakeParts.size() - 1;
        snakeParts.remove(tailIndex);
    }

    public void move(Apple apple) {


        GameObject newHead = createNewHead();

        if (newHead.x >= 15 || newHead.y >= 15 || newHead.x < 0 || newHead.y < 0) {
            isAlive = false;
            return;
        }
        if (checkCollision(newHead)) {
            isAlive = false;
            return;
        }

        if (newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false;


        } else {
            removeTail();
        }

        snakeParts.add(0, newHead); //добавление головы
    }

    public boolean checkCollision(GameObject go) {
        boolean col = false;
        for (int i = 0; i < snakeParts.size(); i++) {
            if (go.x == snakeParts.get(i).x && go.y == snakeParts.get(i).y) {
                col = true;
            }
        }
        return col;
    }

    public void setDirection(Direction direction) {
        if (this.direction == Direction.LEFT && direction == Direction.RIGHT ||
                this.direction == Direction.LEFT && snakeParts.get(0).x == snakeParts.get(1).x){
            return;
        } else if (this.direction == Direction.RIGHT && direction == Direction.RIGHT ||
                this.direction == Direction.RIGHT && snakeParts.get(0).x == snakeParts.get(1).x){
            return;
        } else if (this.direction == Direction.UP && direction == Direction.DOWN ||
                this.direction == Direction.UP && snakeParts.get(0).y == snakeParts.get(1).y){
            return;
        } else if (this.direction == Direction.DOWN && direction == Direction.UP ||
                this.direction == Direction.DOWN && snakeParts.get(0).y == snakeParts.get(1).y){
            return;
        }
        this.direction = direction;
    }
}
