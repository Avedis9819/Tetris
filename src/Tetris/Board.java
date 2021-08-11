
package Tetris;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    private GameEngine engine;
    private Timer timer;

    public final int height;
    public final int width;
    public final int[][] grid;


    public Tetromino currentTetromino;
    public final int squareSize;
    public UpcomingTetromino nextTetromino;
    public Tetromino heldShape;

    public Board() {

        this.setLayout(null);
        this.setBounds(0, 0, 800,640);
        this.nextTetromino = new UpcomingTetromino();
        this.heldShape = null;
        engine = new GameEngine();
        this.height = 20;
        this.width = 10;
        this.grid = new int[this.height][this.width];
        this.squareSize = 30;
        this.setBackground(new Color(0x242526));
        this.currentTetromino = UpcomingTetromino.pop();
        timer = new Timer(500, this);
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

        for(int row = 0; row < this.grid.length; row++) {
            for(int col = 0; col < this.grid[row].length; col++) {
                System.out.print(this.grid[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
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
                this.currentTetromino = UpcomingTetromino.pop();
                GameFrame.eastPanel.updateEastPanel();
                engine.adding = false;
            }

        }

    }



}