package Tetris;

import javax.swing.*;
import java.util.Arrays;

public class GameEngine {
    static Board board = new Board();
    boolean adding = false;

    GameEngine() {
    }


    //checks whether the row is full
    public boolean isFull(int[] arr) {
        for (int j : arr) {
            if (j == 0) {
                return false;
            }
        }
        return true;
    }

    //clears the row which is filled
    public void clearOneRow(int filledRow) {
        for (int newRow = filledRow; newRow > 0; --newRow) {
            System.arraycopy(board.grid[newRow - 1], 0, board.grid[newRow], 0, board.width);
        }
        Arrays.fill(board.grid[0], 0);
    }

    //clears all full rows
    public void clearAllFullRows() {
        int count = 0;
        int lines = 0;
        for (int row = 0; row < board.grid.length; row++) {
            while (isFull(board.grid[row])) {
                clearOneRow(row);
                count += 100;
                lines++;
            }
        }
        GameFrame.eastPanel.updateScore(count);
        GameFrame.eastPanel.updateLines(lines);
    }

    //checks for the collisions on the sides
    public boolean NoSideCollision(char side) {
        boolean noCollision = true;
        switch (side) {
            //right
            case 'r':
                try {
                    for (int i = 0; i < board.currentTetromino.getStateOfTetromino().length; i++) {
                        for (int j = 0; j < board.currentTetromino.getStateOfTetromino()[i].length; j++) {
                            if (board.currentTetromino.getStateOfTetromino()[i][j] != 0) {
                                if (board.grid[board.currentTetromino.getCurrShapeY() + i - 1][board.currentTetromino.getCurrShapeX() + j] != 0) {
                                    noCollision = false;
                                    break;
                                }
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error while moving right");
                }
                break;
            //left
            case 'l':
                try {
                    for (int i = 0; i < board.currentTetromino.getStateOfTetromino().length; i++) {
                        for (int j = 0; j < board.currentTetromino.getStateOfTetromino()[i].length; j++) {
                            if (board.currentTetromino.getStateOfTetromino()[i][j] != 0) {
                                if (board.grid[board.currentTetromino.getCurrShapeY() + i - 1][board.currentTetromino.getCurrShapeX() + j - 2] != 0) {
                                    noCollision = false;
                                    break;
                                }
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error while moving left");
                }
                break;
        }
        return noCollision;
    }

    //checks the collision from the bottom with the board and with other shapes
    public boolean NoBottomCollision() {
        boolean noCollision = true;

        if (board.currentTetromino.getHeight() / 2 + (board.currentTetromino.getCurrShapeY() == board.height - 1 ? 1 : 0) >= board.height - board.currentTetromino.getCurrShapeY()
                || (board.currentTetromino.getCurrShapeY() == board.height - board.currentTetromino.getHeight() / 2 - 1 && !board.currentTetromino.areSidesZeros('d'))) { //changed -1
            noCollision = false;
            this.adding = true;
        } else {
            for (int i = 0; i < board.currentTetromino.getStateOfTetromino().length; i++) {
                for (int j = 0; j < board.currentTetromino.getStateOfTetromino()[i].length; j++) {
                    if (board.currentTetromino.getStateOfTetromino()[i][j] != 0) {
                        if (board.grid[board.currentTetromino.getCurrShapeY() + i][board.currentTetromino.getCurrShapeX() + j - 1] != 0) {
                            noCollision = false;
                            this.adding = true;
                        }
                    }
                }
            }
        }

        return noCollision;
    }

    //moves the tetromino to the left
    public void moveLeft() {
        if (NoSideCollision('l')) {
            if (board.currentTetromino.getCurrShapeX() > 1 || (board.currentTetromino.getCurrShapeX() == 1 && board.currentTetromino.areSidesZeros('l'))) {
                board.currentTetromino.setCurrShapeX(board.currentTetromino.getCurrShapeX() - 1);
            }
        }
    }

    //moves the tetromino to the right
    public void moveRight() {
        if (NoSideCollision('r')) {
            if ((board.currentTetromino.getWidth() / 2 < board.width - board.currentTetromino.getCurrShapeX() - 1
                    || (board.currentTetromino.getCurrShapeX() == 8 && board.currentTetromino.areSidesZeros('r')))) { //changed currentTetromino.width/2
                board.currentTetromino.setCurrShapeX(board.currentTetromino.getCurrShapeX() + 1);
            }
        }
    }

    //rotates the tetromino clockwise
    public void rotateClockwise() {
        if ((NoSideCollision('r') && board.currentTetromino.getCurrShapeX() >= 5) || (NoSideCollision('l') && (board.currentTetromino.getHeight() / 2 <= board.currentTetromino.getCurrShapeX()))) {
            if (board.currentTetromino.getHeight() / 2 <= board.width - board.currentTetromino.getCurrShapeX() - 1) {
                if (board.currentTetromino.getPositions().length > 1) {
                    if (board.currentTetromino.getIndex() == board.currentTetromino.getPositions().length - 1) {
                        board.currentTetromino.setIndex(0);
                    } else {
                        board.currentTetromino.setIndex(board.currentTetromino.getIndex() + 1);
                    }
                    board.currentTetromino.setStateOfTetromino(board.currentTetromino.getPositions()[board.currentTetromino.getIndex()]);
                    board.currentTetromino.setWidthAndHeight();
                }
            }
        }
    }

    //hard drops the tetromino
    public void hardDrop() {
        if (board.currentTetromino.getCurrShapeY() > 1) {
            while (NoBottomCollision()) {
                board.currentTetromino.setCurrShapeY(board.currentTetromino.getCurrShapeY() + 1);
                adding = true;
            }
        }
    }

    //holds the tetromino
    public void holdShape() {
        if (board.heldShape != null && !board.heldThisRound) {
            board.heldShape.setCurrShapeY(0);
            board.heldShape.setCurrShapeX(4);
            Tetromino temporaryTetromino = board.heldShape;
            board.heldShape = board.currentTetromino;
            board.currentTetromino = temporaryTetromino;
        } else {
            board.heldShape = board.currentTetromino;
            board.currentTetromino = UpcomingTetromino.pop();
        }
    }

    //fills the grid with the numbers corresponding the color of particular shape
    public void addToGrid() {
        for (int i = 0; i < board.currentTetromino.getStateOfTetromino().length; i++) {
            for (int j = 0; j < board.currentTetromino.getStateOfTetromino()[i].length; j++) {
                if (board.currentTetromino.getStateOfTetromino()[i][j] != 0) {
                    try {
                        board.grid[board.currentTetromino.getCurrShapeY() + i - 1][board.currentTetromino.getCurrShapeX() + j - 1] = board.currentTetromino.getTetrominoNumber();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        String[] options = {"Yes", "Exit"};
                        int input = JOptionPane.showOptionDialog(board, "You lost...\nYour score is: " + GameFrame.eastPanel.getScore() + "\nNumbers of Lines cleared: " + GameFrame.eastPanel.getLines() + "\nRestart?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (input == 0) {
                            board.restartGame();

                            return;
                        } else {
                            System.exit(0);
                        }
                    }
                }
            }

        }
    }
    //clears the grid
    public void clearGrid () {
        for (int[] ints : board.grid) {
            Arrays.fill(ints, 0);
        }
    }
}


