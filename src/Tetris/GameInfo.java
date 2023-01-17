package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInfo extends JFrame implements ActionListener {

    private JButton closeButton;
    private ImageIcon icon;
    GameInfo() {
        JTextArea text = new JTextArea();
        icon = new ImageIcon("Tetris/Resources/tetris.png");
        this.setIconImage(icon.getImage());
        closeButton = new JButton("Close");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tetris - Info Page");
        this.setResizable(false);
        this.setSize(515, 640);
        this.add(closeButton,BorderLayout.SOUTH);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        closeButton.setActionCommand("close");
        closeButton.addActionListener(this);
        closeButton.setFocusable(false);
        text.setForeground(Color.WHITE);
        text.setText("Created by: Avo Alexanian and Gayane Ghazaryan\n\n " +
                "Controls: \n" +
                "- \"Left\" and \"right\" arrow keys to move your shape around.\n" +
                "- \"Up\" arrow key to rotate your shape clockwise.\n" +
                "- \"Z\" to add and retrieve your held shape.\n"+
                "- \"Space Bar\" to hard drop the shape \n" +
                "- \"Down arrow\" to slowly move the shape down.\n" +
                "- \"ESC\" to pause the game.");


        text.setFont(new Font("", Font.BOLD, 16));
        text.setOpaque(true);
        text.setBackground(new Color(0x343536));;
        this.add(text, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(closeButton.getActionCommand())) {
            this.dispose();
        }
    }
}
