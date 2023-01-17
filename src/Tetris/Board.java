
package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Board extends JPanel implements ActionListener {

    private GameEngine engine;
    protected Timer timer;
    protected boolean heldThisRound;
    protected final int height;
    protected final int width;
    protected final int[][] grid;
    protected Tetromino currentTetromino;
    protected final int squareSize;
    protected UpcomingTetromino nextTetromino;
    protected Tetromino heldShape;
    private int difficulty;


    public Board() {
        this.setLayout(null);
        this.setBounds(0, 0, 1100,740);
        this.nextTetromino = new UpcomingTetromino();
        this.heldShape = null;
        this.heldThisRound = false;
        engine = new GameEngine();
        this.height = 20;
        this.width = 10;
        this.grid = new int[this.height][this.width];
        this.squareSize = 30;
        this.setBackground(new Color(0x242526));
        this.currentTetromino = UpcomingTetromino.pop();
        difficulty = IntroPage.getChoice();
        System.out.println(difficulty);
        setDifficulty();
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int i = 0; i < currentTetromino.getStateOfTetromino().length; i++) {
            for (int j = 0; j < currentTetromino.getStateOfTetromino()[i].length; j++) {
                if (currentTetromino.getStateOfTetromino()[i][j] == 1) {
                    graphics.setColor(currentTetromino.getColor());
                    graphics.fillRect((j + this.currentTetromino.getCurrShapeX() - 1) * squareSize, (i + this.currentTetromino.getCurrShapeY() - 1) * squareSize, squareSize, squareSize);
                    graphics.setColor(new Color(0x373737));
                    graphics.drawRect((j + this.currentTetromino.getCurrShapeX() - 1) * squareSize, (i + this.currentTetromino.getCurrShapeY() - 1) * squareSize, squareSize, squareSize);
                }
            }
        }

        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {

                if (this.grid[i][j] == 1) {
                    graphics.setColor(new Color(0x005DFF));

                } else if (this.grid[i][j] == 2) {
                    graphics.setColor(new Color(0xf44336));

                } else if (this.grid[i][j] == 3) {
                    graphics.setColor(new Color(0x6FB45A));

                } else if (this.grid[i][j] == 4) {
                    graphics.setColor(new Color(0xFFBD24));

                } else if (this.grid[i][j] == 5) {
                    graphics.setColor(new Color(0x009FFF));

                } else if (this.grid[i][j] == 6) {
                    graphics.setColor(new Color(0x8CD023));

                } else if (this.grid[i][j] == 7) {
                    graphics.setColor(new Color(0xC6140D));
                }

                if (this.grid[i][j] != 0) {
                    graphics.fillRect(j * squareSize, i * squareSize, squareSize, squareSize);

                }
            }
        }
        graphics.setColor(new Color(0X474747));

        for (int i = 0; i <= this.height; i++) {
            graphics.drawLine(0, squareSize * i, this.width * squareSize, i * squareSize);
        }
        for (int i = 0; i <= this.width; i++) {
            graphics.drawLine(i * squareSize, 0, i * squareSize, this.height * squareSize);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Moves the shapes down
        if (engine.NoBottomCollision()) {
            this.currentTetromino.setCurrShapeY(this.currentTetromino.getCurrShapeY()+1);
            this.repaint();
        }
        else {
            if (engine.adding) {
                engine.addToGrid();
                engine.clearAllFullRows();
                currentTetromino = UpcomingTetromino.pop();
                GameFrame.eastPanel.updateEastPanel();
                engine.adding = false;
                heldThisRound = false;
            }
        }
    }

    public int getDifficulty() {
        return difficulty;
    }

    private void setDifficulty() {
        this.difficulty = IntroPage.getChoice();
        System.out.println(difficulty);
        if(difficulty == 0)
            timer = new Timer(300, this);
        else if(difficulty == 1)
            timer = new Timer(250, this);
        else
            timer = new Timer(150, this);
    }

    private void updateDifficulty() {
        if(this.difficulty == 0)
            timer.setInitialDelay(300);
        else if(difficulty == 1)
            timer.setInitialDelay(250);
        else
            timer.setInitialDelay(150);
    }

    protected void restartGame() {
        engine.clearGrid();
        this.currentTetromino = new Tetromino();
        this.heldShape = null;
        GameFrame.eastPanel.resetScore();
        GameFrame.eastPanel.updateEastPanel();
        repaint();
    }
}

