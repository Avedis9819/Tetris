package Tetris;

import java.util.ArrayList;
import java.util.Arrays;

public class UpcomingTetromino {
    public static ArrayList<Tetromino> upcomingTetromino;

    public UpcomingTetromino() {
        upcomingTetromino = new ArrayList<>(Arrays.asList(new Tetromino(),new Tetromino(),new Tetromino()));
    }

    public static void push() {
        upcomingTetromino.add(new Tetromino());
    }


    public static Tetromino pop() {
        if(!isEmpty()) {
            push();
            return upcomingTetromino.remove(0);
        }
        return null;
    }

    public Tetromino peek(int index) {
        if(!isEmpty() && index < upcomingTetromino.size()) {
            return upcomingTetromino.get(index);
        }
        return null;
    }

    public static boolean isEmpty() {
        return upcomingTetromino.isEmpty();
    }

}


