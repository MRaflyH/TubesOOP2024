package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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

public class ExitFrame extends JFrame implements ActionListener {
    JButton saveWithoutExitButton;
    JButton saveWithExitButton;

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
    
    public ExitFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(320, 420);
        setResizable(false);
        setVisible(true);

        BufferedImage myImage;
        try {
            myImage = ImageIO.read(new File("src/assets/exitgamebackground.png"));
            ImagePanel imagePanel = new ImagePanel(myImage);
            imagePanel.setLayout(null);

            // Create buttons
            JPanel buttonPanel = createPanel(50, 200, 200, 150);
            saveWithoutExitButton = createButton(5, 0, 200, 50, buttonPanel, new ImageIcon(
                    "src/assets/exitwithoutsavebutton.png"));
            saveWithExitButton = createButton(5, 60, 200, 50, buttonPanel, new ImageIcon(
                    "src/assets/exitwithsavebutton.png")); 

            // Set button text
            saveWithoutExitButton.setText("Save Without Exit");
            saveWithExitButton.setText("Save With Exit");
            buttonPanel.setVisible(true);

            imagePanel.add(buttonPanel); 
            setContentPane(imagePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveWithoutExitButton) {
            new MyFrame();
            dispose();
            
        } else if (e.getSource() == saveWithExitButton) {
            //taro code save disini
            new MyFrame();
            dispose(); 
        }
    }
}
