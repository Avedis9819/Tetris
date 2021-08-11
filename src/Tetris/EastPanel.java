package Tetris;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EastPanel extends JPanel {

    private final int squareSize;
    private Tetromino nextShape;
    private Tetromino secondNextShape;
    private static Board board;

    public EastPanel() {

        board = GameEngine.board;
        this.setLayout(new BorderLayout());
        this.squareSize = board.squareSize;
        this.nextShape = UpcomingTetromino.upcomingTetromino.get(0);
        this.secondNextShape = UpcomingTetromino.upcomingTetromino.get(1);
        this.setPreferredSize(new Dimension(200, 650));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.setOpaque(true);
        this.setBackground(new Color(0x343536));

        JLabel upcomingShapes = new JLabel("Upcoming Shapes");
        JLabel heldTetromino = new JLabel("Held Tetromino");
        JLabel playerName = new JLabel("Player Name: " + IntroPage.getUsername());
        upcomingShapes.setForeground(Color.WHITE);
        heldTetromino.setForeground(Color.WHITE);
        playerName.setForeground(Color.WHITE);
        upcomingShapes.setHorizontalAlignment(JLabel.CENTER);
        heldTetromino.setHorizontalAlignment(JLabel.CENTER);

        this.add(upcomingShapes, BorderLayout.NORTH);
        this.add(heldTetromino, BorderLayout.CENTER);
        this.add(playerName, BorderLayout.SOUTH);
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setPreferredSize(new Dimension(198, 590));
        this.setBackground(new Color(0x343536));
        this.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.GRAY, 3);
        this.setBorder(border);

    }

    public void updateEastPanel() {
        this.nextShape = UpcomingTetromino.upcomingTetromino.get(0);
        this.secondNextShape = UpcomingTetromino.upcomingTetromino.get(1);
        repaint();

    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int row = 0; row < nextShape.getStateOfTetromino().length; row++) {
            for (int col = 0; col < nextShape.getStateOfTetromino()[row].length; col++) {

                if (nextShape.getStateOfTetromino()[row][col] != 0) {
                    graphics.setColor(nextShape.getColor());
                    graphics.fillRect(50 + (col * squareSize), (row * squareSize) + 40, squareSize, squareSize);
                    graphics.setColor(new Color(0x373737));
                    graphics.drawRect(50 + (col * squareSize), (row * squareSize) + 40, squareSize, squareSize);

                }
            }
        }

        for(int row = 0; row < secondNextShape.getStateOfTetromino().length; row++) {
            for(int col = 0; col < secondNextShape.getStateOfTetromino()[row].length; col++){
                if (secondNextShape.getStateOfTetromino()[row][col] != 0) {
                    graphics.setColor(secondNextShape.getColor());
                    graphics.fillRect(50 + (col * squareSize), (row * squareSize) + 175, squareSize, squareSize);
                    graphics.setColor(new Color(0x373737));
                    graphics.drawRect(50 + (col * squareSize), (row * squareSize) + 175, squareSize, squareSize);

                }
            }
        }
        if (board.heldShape == null)
            return;
        for (int row = 0; row < board.heldShape.getStateOfTetromino().length; row++) {
            for (int col = 0; col < board.heldShape.getStateOfTetromino()[row].length; col++) {
                if (board.heldShape.getStateOfTetromino()[row][col] != 0) {

                    graphics.setColor(board.heldShape.getColor());
                    graphics.fillRect(50 + (col * squareSize), (row * squareSize) + 325, squareSize, squareSize);
                    graphics.setColor(new Color(0x373737));
                    graphics.drawRect(50 + (col * squareSize), (row * squareSize) + 325, squareSize, squareSize);

                }
            }
        }
    }
}
