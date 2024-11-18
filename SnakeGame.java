package com.javarush.task.jdk13.task53.task5303;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private static final int GOAL = 28;
    private int score = 0;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();

    }

    private void drawScene() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.GOLDENROD, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    private void createGame() {
        isGameStopped = false;
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        //apple = new Apple(5, 5);
        createNewApple();
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        score = 0;
        setScore(score);
    }
    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.AZURE, "You won!", Color.BLACK, 75);
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if (!apple.isAlive) {
            score = score + 5;
            setScore(5);
            turnDelay = turnDelay - 10;
            setTurnTimer(turnDelay);
            createNewApple();
        }
        if (!snake.isAlive) {
            gameOver();
        }
        if (snake.getLength() > GOAL) {
            win();
        }
        drawScene();
    }

    private void gameOver() {
        isGameStopped = true;
        stopTurnTimer();
        showMessageDialog(Color.ALICEBLUE, "GAME OVER", Color.BLACK, 75);
    }


    @Override
    public void onKeyPress(Key key) {
        if (key == Key.LEFT) {
            snake.setDirection(Direction.LEFT);
        } else if (key == Key.RIGHT) {
            snake.setDirection(Direction.RIGHT);
        } else if (key == Key.UP) {
            snake.setDirection(Direction.UP);
        } else if (key == Key.DOWN) {
            snake.setDirection(Direction.DOWN);
        } else if (key == Key.SPACE) {
            if (isGameStopped) {
                createGame();
            } else {
                return;
            }
        } else {
            return;
        }
    }

    private void createNewApple() {
        if (apple == null || !apple.isAlive) {
            do {
                apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
            } while (snake.checkCollision(apple));
        } else {
            return;
        }
    }
}