package Tetris;

import java.util.Random;
import java.awt.Color;

public class Tetromino {
    private Random randomNumberGenerator = new Random();
    private Shape shape;
    private int height;
    private int width;
    private int currShapeX;
    private int currShapeY;
    private int[] numberOfNonZerosWidth;
    private int[] numberOfNonZerosHeight;
    private Color color;
    private int index;
    private int[][][] positions;
    private int[][] stateOfTetromino;
    private int tetrominoNumber;

    public Tetromino() {
        setRandomShape();
        setPositions(shape.coordinates);
        setStateOfTetromino(initialStateOfTetrimono());
        setWidthAndHeight();
        setColor(tetrominoColor());
        setCurrShapeX(4);
        setCurrShapeY(0);
        setTetrominoNumber(shape.getNumber());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getCurrShapeX() {
        return currShapeX;
    }

    public void setCurrShapeX(int currShapeX) {
        this.currShapeX = currShapeX;
    }

    public int getCurrShapeY() {
        return currShapeY;
    }

    public void setCurrShapeY(int currShapeY) {
        this.currShapeY = currShapeY;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int[][] getStateOfTetromino() {
        return stateOfTetromino;
    }

    public void setStateOfTetromino(int[][] stateOfTetromino) {
        this.stateOfTetromino = stateOfTetromino;
    }

    public int[][][] getPositions() {
        return positions;
    }

    public void setPositions(int[][][] positions) {
        this.positions = positions;
    }

    public int getTetrominoNumber() {
        return tetrominoNumber;
    }

    public void setTetrominoNumber(int tetrominoNumber) {
        this.tetrominoNumber = tetrominoNumber;
    }

    //sets width and height according to the shape
    void setWidthAndHeight() {
        int r = stateOfTetromino.length;
        int c = stateOfTetromino[0].length;

        int[] h = new int[r];
        int[] w = new int[c];

        for (int i = 0; i < r; i++) {
            int count = 0;
            for (int j = 0; j < c; j++) {
                if(stateOfTetromino[i][j] != 0) {
                    w[j] += 1;
                    count += 1;
                }
            }
            h[i] = count;
        }

        this.numberOfNonZerosWidth = w;
        this.numberOfNonZerosHeight = h;

        int width = w.length;
        int height = h.length;
        for(int i: w) {
            if(i == 0) {
                width--;
            }
        }

        for(int i: h) {
            if(i == 0) {
                height--;
            }
        }
        setWidth(width);
        setHeight(height);
    }


    //checks whether the original tetromino matrix has extra zero rows or columns
    public boolean areSidesZeros(char side) {
        if(side == 'r') {
            return numberOfNonZerosWidth[numberOfNonZerosWidth.length-1] == 0;
        } else if( side == 'l') {
            return numberOfNonZerosWidth[0] == 0;
        } else if(side == 'd') {
            return (numberOfNonZerosHeight[numberOfNonZerosWidth.length-1] == 0) || (numberOfNonZerosHeight[numberOfNonZerosWidth.length-2] == 0);
        }
        return false;
    }


    //randomly generate the initial state of tetromino
    private int[][] initialStateOfTetrimono() {
        setIndex(randomNumberGenerator.nextInt(positions.length));
        return positions[index];
    }

    //randomly generates the color of tetromino
    public Color tetrominoColor() {

        if (shape == Shape.I_SHAPE || shape.getNumber() == 1) {
            return new Color(0x005DFF);

        } else if (shape == Shape.J_SHAPE || shape.getNumber() == 2) {
            return new Color(0xf44336);

        } else if (shape == Shape.L_SHAPE || shape.getNumber() == 3 ) {
            return new Color(0x6FB45A);

        } else if (shape == Shape.O_SHAPE || shape.getNumber() == 4) {
            return new Color(0xFFBD24);

        } else if (shape == Shape.S_SHAPE || shape.getNumber() == 5) {
            return new Color(0x009FFF);

        } else if (shape == Shape.T_SHAPE || shape.getNumber() == 6) {
            return new Color(0x8CD023);

        } else if (shape == Shape.Z_SHAPE || shape.getNumber() == 7 ) {
            return new Color(0xC6140D);

        }
        return Color.white;
    }

    //randomly generates the shape of tetromino
    private void setRandomShape() {
        this.shape = Shape.getRandomShape();
    }

}
