package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroPage extends JFrame implements ActionListener {

    private ImageIcon icon;
    private ImageIcon introPic;
    private JLabel tetrisLogoLabel;
    private JButton startButton;
    private JButton infoButton;
    private static String username;

    IntroPage() {
        introPic = new ImageIcon("Tetris/Resources/tIcon.png");
        icon = new ImageIcon("Tetris/Resources/tetris.png");
        tetrisLogoLabel = new JLabel(introPic);
        username = "";
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tetris");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(0x123456));
        this.setSize(800,600);
        this.setResizable(false);
        this.add(tetrisLogoLabel, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);

        startButton = new JButton("Start Game!");
        startButton.setActionCommand("start");
        startButton.addActionListener(this);
        startButton.setFocusable(false);
        startButton.setBackground(new Color(0x343536));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
        this.add(startButton, BorderLayout.SOUTH);

        infoButton = new JButton("Game info");
        infoButton.setActionCommand("info");
        infoButton.addActionListener(this);
        infoButton.setFocusable(false);
        infoButton.setBackground(new Color(0x343536));
        infoButton.setForeground(Color.WHITE);
        infoButton.setFont(new Font("Times new Roman", Font.BOLD, 23));
        this.add(infoButton, BorderLayout.NORTH);

        this.setVisible(true);
//        tetrisLogo = new ImageIcon(".//Images//tetris.png");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(startButton.getActionCommand())) {
            boolean condition = true;
            while(condition) {
             setUsername(JOptionPane.showInputDialog(this, "Please insert your name...", "Name Please", JOptionPane.PLAIN_MESSAGE));
                if (!username.isEmpty()) {
                    condition = false;
                    new GameFrame();
                    this.dispose();
                } else {
                    JOptionPane.showConfirmDialog(this, "Please insert a name", "Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if(e.getActionCommand().equals(infoButton.getActionCommand())) {
            new GameInfo();
        }
    }

    public static void setUsername(String newUsername) {
        username = newUsername;
    }
    public static String getUsername() {
        return username;
    }
}
