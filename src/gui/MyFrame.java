package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MyFrame extends JFrame implements ActionListener {
    private int currentFrame;
    /*
     * 0: menu
     * 1: deck
     * 2: game
     * 3: help
     * 4: plant list
     * 5: zombie list
     */
    
    JPanel menuPanel;
    JPanel mapPanel;
    JPanel deckPanel;
    JPanel inventoryPanel;

    ArrayList<ArrayList<JButton>> mapButtons = new ArrayList<ArrayList<JButton>>(6);
    ArrayList<JButton> deckButtons = new ArrayList<JButton>(7);
    ArrayList<JButton> inventoryButtons = new ArrayList<JButton>(10);

    JButton startButton;
    JButton helpButton;
    JButton plantsListButton;
    JButton zombieListButton;
    JButton exitButton;
    JButton menuButton;
    JButton readyButton;

    JLabel pvzLogo;

    static final int FRAME_WIDTH = 640;
    static final int FRAME_HEIGHT = 480;
    static final int TILE_WIDTH = 50;
    static final int TILE_HEIGHT = 60;
    static final int LARLGE_WIDTH = 320;
    static final int LARLGE_HEIGHT = 50;
    static final int SMALL_WIDTH = 100;
    static final int SMALL_HEIGHT = 30;
    
    static final Color BACKGROUND_COLOR = new Color(0xC4BFB6);
    static final Color BUTTON_COLOR = new Color(0x8C857B);
    static final Color GRASS1_COLOR = new Color(0x4C8C2B);
    static final Color GRASS2_COLOR = new Color(0x6CC24A);
    static final Color WATER_COLOR = new Color(0x59CBE8);


    public MyFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        getContentPane().setBackground(BACKGROUND_COLOR);

        SetPanels();
        SetButtons();
        SetLabels();

        SwitchToMenuFrame();

        setVisible(true);

    }

    public void SwitchToMenuFrame() {

        currentFrame = 0;
        menuPanel.setVisible(true);
        pvzLogo.setVisible(true);
        exitButton.setVisible(true);

    }

    public void SwitchToDeckFrame() {

        currentFrame = 1;
        menuButton.setVisible(true);
        deckPanel.setVisible(true);
        inventoryPanel.setVisible(true);
        readyButton.setVisible(true);

    }

    public void SwitchToGameFrame() {

        currentFrame = 2;
        menuButton.setVisible(true);
        mapPanel.setVisible(true);
        deckPanel.setVisible(true);

    }

    public void RemoveButtons() {

        if (currentFrame == 0) {
            menuPanel.setVisible(false);
            pvzLogo.setVisible(false);
            exitButton.setVisible(false);
            }
        else if (currentFrame == 1) {
            menuButton.setVisible(false);
            deckPanel.setVisible(false);
            inventoryPanel.setVisible(false);
            readyButton.setVisible(false);    
        }
        else if (currentFrame == 2) {
            menuButton.setVisible(false);
            mapPanel.setVisible(false);
            deckPanel.setVisible(false);
        }

    }

    public void SetPanels() {

        menuPanel = CreatePanel(160, 210, LARLGE_WIDTH, LARLGE_HEIGHT*4 + 30);
        deckPanel = CreatePanel(10, 10, TILE_WIDTH * 7, TILE_HEIGHT);
        inventoryPanel = CreatePanel(70, 80, TILE_WIDTH * 5, TILE_HEIGHT * 5);
        mapPanel = CreatePanel(50, 80, TILE_WIDTH * 11, TILE_HEIGHT * 6);

    }

    public void SetButtons() {

        startButton = CreateButton(0, 0, LARLGE_WIDTH, LARLGE_HEIGHT, BUTTON_COLOR, "START", menuPanel);
        helpButton = CreateButton(0, LARLGE_HEIGHT + 10, LARLGE_WIDTH, LARLGE_HEIGHT, BUTTON_COLOR, "HELP", menuPanel);
        plantsListButton = CreateButton(0, (LARLGE_HEIGHT + 10) * 2, LARLGE_WIDTH, LARLGE_HEIGHT, BUTTON_COLOR, "PLANTS LIST", menuPanel);
        zombieListButton = CreateButton(0, (LARLGE_HEIGHT + 10) * 3, LARLGE_WIDTH, LARLGE_HEIGHT, BUTTON_COLOR, "ZOMBIES LIST", menuPanel);

        exitButton = CreateButton(10, 10, SMALL_WIDTH, SMALL_HEIGHT, BUTTON_COLOR, "EXIT");

        menuButton = CreateButton(530, 10, SMALL_WIDTH, SMALL_HEIGHT, BUTTON_COLOR, "MENU");

        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                deckButtons.add(CreateButton(TILE_WIDTH * i, 0, TILE_WIDTH, TILE_HEIGHT, BUTTON_COLOR, null, deckPanel));
                deckButtons.get(i).setEnabled(false);
            }
            else if (i % 2 == 0) {
                deckButtons.add(CreateButton(TILE_WIDTH * i, 0, TILE_WIDTH, TILE_HEIGHT, GRASS2_COLOR, null, deckPanel));
            }
            else {
                deckButtons.add(CreateButton(TILE_WIDTH * i, 0, TILE_WIDTH, TILE_HEIGHT, GRASS1_COLOR, null, deckPanel));
            }
        }

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                inventoryButtons.add(CreateButton(TILE_WIDTH * (i % 5), i / 5 * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, GRASS2_COLOR, null, inventoryPanel));
            }
            else {
                inventoryButtons.add(CreateButton(TILE_WIDTH * (i % 5), i / 5 * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, GRASS1_COLOR, null, inventoryPanel));
            }
        }

        readyButton = CreateButton(40, 390, LARLGE_WIDTH, LARLGE_HEIGHT, BUTTON_COLOR, "READY");

        for (int i = 0; i < 6; i++) {
            ArrayList<JButton> tempMapRow = new ArrayList<JButton>(11);
            for (int j = 0; j < 11; j++) {
                if (j == 0 || j == 10) {
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, BUTTON_COLOR, null, mapPanel));
                    tempMapRow.get(j).setEnabled(false);
                }
                else if (i == 2 || i == 3) {
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, WATER_COLOR, null, mapPanel));
                }
                else if ((i + j) % 2 == 0) {
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, GRASS2_COLOR, null, mapPanel));
                }
                else {
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, GRASS1_COLOR, null, mapPanel));
                }
            }
            mapButtons.add(tempMapRow);
        }

    }

    public void SetLabels() {

        pvzLogo = new JLabel();
        pvzLogo.setBounds(160, 40, 320, 150);
        pvzLogo.setIcon(new ImageIcon(new ImageIcon("../assets/Plants_vs_Zombies_logo.png").getImage().getScaledInstance(pvzLogo.getWidth(), pvzLogo.getHeight(), Image.SCALE_DEFAULT)));
        pvzLogo.setHorizontalAlignment(JLabel.CENTER);
        pvzLogo.setVerticalAlignment(JLabel.CENTER);
        pvzLogo.setVisible(true);
        add(pvzLogo);

    }

    public JPanel CreatePanel(int x, int y, int width, int height) {
        
        JPanel newPanel = new JPanel();
        newPanel.setBounds(x, y, width, height);
        newPanel.setOpaque(false);
        newPanel.setLayout(null);
        newPanel.setVisible(false);
        add(newPanel);
        return newPanel;

    }

    public JButton CreateButton(int x, int y, int width, int height, Color color, String text) {

        JButton newButton = new JButton();
        newButton.setBounds(x, y, width, height);
        newButton.addActionListener(this);
        newButton.setBorderPainted(false);
        newButton.setOpaque(true);
        newButton.setBackground(color);
        newButton.setText(text);
        newButton.setVisible(false);
        add(newButton);
        return newButton;

    }

    public JButton CreateButton(int x, int y, int width, int height, Color color, String text, JPanel panel) {

        JButton newButton = new JButton();
        newButton.setBounds(x, y, width, height);
        newButton.addActionListener(this);
        newButton.setBorderPainted(false);
        newButton.setOpaque(true);
        newButton.setBackground(color);
        newButton.setText(text);
        newButton.setVisible(true);
        panel.add(newButton);
        return newButton;

    }

    public JLabel CreateLabel() {return new JLabel();}

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() != null) {
            // jika button apapun dipress
        }

        if(e.getSource() == startButton) {
            RemoveButtons();
            SwitchToDeckFrame();
        }
        else if(e.getSource() == exitButton) {
            dispose();
        }
        else if (e.getSource() == readyButton) {
            RemoveButtons();
            SwitchToGameFrame();
        }
        else if(e.getSource() == menuButton) {
            RemoveButtons();
            SwitchToMenuFrame();
        }

    }

}
