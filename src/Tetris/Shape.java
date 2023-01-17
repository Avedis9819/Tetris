package Tetris;

import java.util.Random;

public enum Shape {
    I_SHAPE(1, new int[][][]{
            {{0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}},

            {{0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0}},

            {{0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}},

            {{0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0}},
    }),
    J_SHAPE(2, new int[][][]{
            {{0, 1, 0},
                    {0, 1, 0},
                    {1, 1, 0}},

            {{1, 0, 0},
                    {1, 1, 1},
                    {0, 0, 0}},

            {{0, 1, 1},
                    {0, 1, 0},
                    {0, 1, 0}},

            {{0, 0, 0},
                    {1, 1, 1},
                    {0, 0, 1}}
    }),
    L_SHAPE(3, new int[][][]{
            {{0, 1, 0},
                    {0, 1, 0},
                    {0, 1, 1}},

            {{0, 0, 0},
                    {1, 1, 1},
                    {1, 0, 0}},

            {{1, 1, 0},
                    {0, 1, 0},
                    {0, 1, 0}},

            {{0, 0, 1},
                    {1, 1, 1},
                    {0, 0, 0}}}),
    O_SHAPE(4, new int[][][]{{
            {0, 1, 1},
            {0, 1, 1},
            {0, 0, 0},
    }}),

    S_SHAPE(5, new int[][][]{
            {{0, 1, 1},
                    {1, 1, 0},
                    {0, 0, 0}},

            {{0, 1, 0},
                    {0, 1, 1},
                    {0, 0, 1}},

            {{0, 0, 0},
                    {0, 1, 1},
                    {1, 1, 0}},

            {{1, 0, 0},
                    {1, 1, 0},
                    {0, 1, 0}}
    }),
    T_SHAPE(6, new int[][][]{
            {{0, 0, 0},
                    {1, 1, 1},
                    {0, 1, 0}},

            {{0, 1, 0},
                    {1, 1, 0},
                    {0, 1, 0}},

            {{0, 1, 0},
                    {1, 1, 1},
                    {0, 0, 0}},

            {{0, 1, 0},
                    {0, 1, 1},
                    {0, 1, 0}}
    }),

    Z_SHAPE(7, new int[][][]{
            {{1, 1, 0},
                    {0, 1, 1},
                    {0, 0, 0}},

            {{0, 0, 1},
                    {0, 1, 1},
                    {0, 1, 0}},

            {{0, 0, 0},
                    {1, 1, 0},
                    {0, 1, 1}},

            {{0, 1, 0},
                    {1, 1, 0},
                    {1, 0, 0}}

    });


    private static final Random randomNumberGenerator = new Random();

    private final int number;
    int[][][] coordinates;


    Shape(int number, int[][][] coordinates) {
        this.coordinates = coordinates;
        this.number = number;
    }

    public static Shape getRandomShape() {
        return Shape.values()[randomNumberGenerator.nextInt(Shape.values().length)];

    }

    public int getNumber() {
        return this.number;
    }

}