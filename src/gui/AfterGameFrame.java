package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import thread.*;

class ImagePanel extends JComponent {
    private Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void setImage(Image newimage) {
        image = newimage;
    }
}

public class AfterGameFrame extends JFrame implements ActionListener {
    JButton mainMenuButton;


    public JButton createButton(int x, int y, int width, int height, JPanel panel, ImageIcon i) {
        JButton newButton = new JButton(i);
        newButton.setBounds(x, y, width, height);
        newButton.addActionListener(this);
        newButton.setOpaque(false);
        newButton.setContentAreaFilled(false);
        newButton.setBorderPainted(false);
        newButton.setFocusPainted(false);
        panel.add(newButton);
        return newButton;
    }

    public JPanel createPanel(int x, int y, int width, int height) {
        JPanel newPanel = new JPanel();
        newPanel.setBounds(x, y, width, height);
        newPanel.setOpaque(false);
        newPanel.setLayout(null);
        newPanel.setVisible(false);
        add(newPanel);
        return newPanel;
    }

    public AfterGameFrame(boolean isWin) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(330, 420);
        setResizable(false);
        setVisible(true);

        BufferedImage myImage;
        try {
            if(isWin){
                myImage = ImageIO.read(new File("src/assets/winframe.png"));
                
            } else {
                myImage = ImageIO.read(new File("src/assets/loseframe.png"));
            }
            ImagePanel imagePanel = new ImagePanel(myImage);
            imagePanel.setLayout(null);

            // Create buttons
            JPanel buttonPanel = createPanel(50, 200, 200, 150);
            mainMenuButton = createButton(5, 40, 200, 50, buttonPanel, new ImageIcon(
                    "src/assets/backmainmenubutton.png"));

            buttonPanel.setVisible(true);

            imagePanel.add(buttonPanel);
            setContentPane(imagePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenuButton) {
            ThreadManager.getInstance().stopAllThreads();
            new MyFrame();
            dispose();
        } 
    }
}