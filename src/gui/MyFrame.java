package gui;
import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;

import thread.RunnableGenerateSun;
import thread.RunnableGenerateSun.*;

import sun.Sun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;


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
    JButton deckButton;

    JLabel pvzLogo;
    JLabel numSun;

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
    static final Color BORDER_DECK_COLOR = new Color(0x855200);


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
        deckPanel = CreatePanel(10, 10, TILE_WIDTH * 8, TILE_HEIGHT);
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
<<<<<<< Updated upstream
                deckButton = CreateButton(TILE_WIDTH * i, 0, 50, 100, BUTTON_COLOR, null, deckPanel, 
                        new ImageIcon("src/assets/sun.png"));
=======
                deckButton = CreateButton(TILE_WIDTH * i -10, 0, 70, 60, BUTTON_COLOR, null, deckPanel, 
                        new ImageIcon("../src/assets/sun.png"));
>>>>>>> Stashed changes
                numSun = new JLabel();
                numSun.setBounds(200, 250, 300,200);
                numSun.setText(Integer.toString(Sun.getTotalSun()));
                numSun.setVisible(true);
                numSun.setOpaque(false);
                deckButton.add(numSun);
                deckButtons.add(deckButton);
                
                deckButtons.get(i).setEnabled(true);
                
            }
            else{
<<<<<<< Updated upstream
                deckButton = CreateButton(TILE_WIDTH * i + 10, 0, TILE_WIDTH + 10, TILE_HEIGHT, BORDER_DECK_COLOR, null, deckPanel, new ImageIcon("src/assets/deck.png"));
=======
                deckButton = CreateButton(TILE_WIDTH * i + 10, 0, TILE_WIDTH, TILE_HEIGHT, BORDER_DECK_COLOR, null, deckPanel, new ImageIcon("../src/assets/decktile.png"));
>>>>>>> Stashed changes
                deckButtons.add(deckButton);
                
            }
        }

        for (int i = 0; i < 10; i++) {
<<<<<<< Updated upstream
            if (i % 2 == 0) {
                inventoryButtons.add(CreateButton(TILE_WIDTH * (i % 5), i / 5 * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, GRASS2_COLOR, null, inventoryPanel));
            }
            else {
                inventoryButtons.add(CreateButton(TILE_WIDTH * (i % 5), i / 5 * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, GRASS1_COLOR, null, inventoryPanel));
            }
=======
            inventoryButtons.add(CreateButton(TILE_WIDTH * (i % 5), i / 5 * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, GRASS2_COLOR, null, inventoryPanel, new ImageIcon("../src/assets/decktile.png")));
            setPlants(true, "../src/assets/sunflower.png", i);
            
>>>>>>> Stashed changes
        }

        readyButton = CreateButton(40, 390, LARLGE_WIDTH, LARLGE_HEIGHT, BUTTON_COLOR, "READY");

        for (int i = 0; i < 6; i++) {
            ArrayList<JButton> tempMapRow = new ArrayList<JButton>(11);
            for (int j = 0; j < 11; j++) {
<<<<<<< Updated upstream
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
=======
                
                if (j == 0) {
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, BUTTON_COLOR, null, mapPanel, new ImageIcon("../src/assets/bricktile.png")));
                } else if(j == 10){
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, BUTTON_COLOR,
                            null, mapPanel, new ImageIcon("../src/assets/gravetile.png")));
                    
                }
                else if (i == 2 || i == 3) {
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, WATER_COLOR, null, mapPanel, 
                            new ImageIcon("../src/assets/watertile.png")));
                }
                else if ((i + j) % 2 == 0) {
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, GRASS2_COLOR, null, mapPanel, new ImageIcon("../src/assets/grasstile1.png")));
                }
                else {
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, GRASS1_COLOR, null, mapPanel, 
                            new ImageIcon("../src/assets/grasstile2.png")));
                } 
>>>>>>> Stashed changes
            }
            mapButtons.add(tempMapRow);
        }

<<<<<<< Updated upstream
=======

    }
    public void setZombies(int i, int j){
        JLabel zombie = new JLabel();
        zombie.setBounds(TILE_WIDTH, TILE_HEIGHT, 18, 30);
        zombie.setHorizontalTextPosition(JLabel.CENTER);
        zombie.setVerticalTextPosition(JLabel.CENTER);
        zombie.setVisible(true);
        zombie.setOpaque(false);
        zombie.setIcon(new ImageIcon(new ImageIcon("../src/assets/normalzombie.png").getImage()
                .getScaledInstance(zombie.getWidth(), zombie.getHeight(), Image.SCALE_DEFAULT)));
        mapButtons.get(i).get(j).add(zombie);
    }

    public void setPlants(boolean onInventory, String srcfile, int i){
        JLabel plant = new JLabel();
        plant.setBounds(TILE_WIDTH, TILE_HEIGHT, 19, 30);
        plant.setHorizontalTextPosition(JLabel.CENTER);
        plant.setVerticalTextPosition(JLabel.CENTER);
        plant.setVisible(true);
        plant.setOpaque(false);
        plant.setIcon(new ImageIcon(new ImageIcon(srcfile).getImage()
                .getScaledInstance(plant.getWidth(), plant.getHeight(), Image.SCALE_SMOOTH)));
        if(onInventory){
            inventoryButtons.get(i).add(plant);
        } else {
            deckButtons.get(i).add(plant);
        }
        
        
    }

    
    // INI GW NYOBA BUAT DEBUGGING ZOMBIE MOVE
    public void removeZombies(int i, int j) {
        for (Component component : mapButtons.get(i).get(j).getComponents()) {
            // Check if component is a JLabel and if it has an icon
            // System.out.println("Component: " + component);
            if (component instanceof JLabel) { 
                JLabel label = (JLabel) component; // Safely cast to JLabel
                if (label.getIcon() != null) { 
                    label.setIcon(null);
                }
            }
        }
>>>>>>> Stashed changes
    }

    public void SetLabels() {

        pvzLogo = new JLabel();
        pvzLogo.setBounds(160, 40, 320, 150);
        pvzLogo.setIcon(new ImageIcon(new ImageIcon("../src/assets/Plants_vs_Zombies_logo.png").getImage().getScaledInstance(pvzLogo.getWidth(), pvzLogo.getHeight(), Image.SCALE_DEFAULT)));
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

    public JButton CreateButton(int x, int y, int width, int height, Color color, String text, JPanel panel, ImageIcon i) {

        JButton newButton = new JButton(i);
        newButton.setBounds(x, y, width, height);
        newButton.addActionListener(this);
        newButton.setOpaque(false);
        newButton.setContentAreaFilled(false);
        newButton.setBorderPainted(false);
        newButton.setFocusPainted(false);
        newButton.setVisible(true);
        panel.add(newButton);
        return newButton;

    }

    public JLabel CreateLabel() {return new JLabel();}

    @Override
    public void actionPerformed(ActionEvent e) {
<<<<<<< Updated upstream
=======
        for(int i = 0; i < 10; i++){
            try{
                if(e.getSource() == inventoryButtons.get(i)){
                setPlants(false, "../src/assets/sunflower.png", getDeckAvalibility() + 1);
                plantOnDeck.push(i);
                deckButtons.get(getDeckAvalibility() + 1).revalidate();
                SetButtonDisabled(inventoryButtons.get(i), inventoryPanel, new ImageIcon("../src/assets/decktiledisabled.png"));
                setDeckNotAvailable();
                inventoryButtons.get(i).revalidate();
                System.out.println(getDeckAvalibility());
                System.out.println("Clicked on: " + " " + i);
            }
            } catch(Exception e2){
                System.out.println("SUDAH PENUH BOS");
            }
            
        }
        if(currentFrame == 1){
            for(int i = 0; i < 6; i++){
                if(e.getSource() == deckButtons.get(getDeckAvalibility())){
                    setDeckAvailable();
                    SetButtonEnabled(disabledPlant.pop(), plantOnDeck.pop());
                    System.out.println("SIZE DISABLED PLANT : " + disabledPlant.size());
                    System.out.println("SIZE PLANT ON DECK : " +  plantOnDeck.size());
                    deckButtons.get(getDeckAvalibility() + 1).removeAll();
                    deckButtons.get(getDeckAvalibility()).revalidate();
                    for(int j = 0; j < 10; j++){
                        inventoryButtons.get(j).revalidate();
                    }
                    System.out.println(getDeckAvalibility());
                    
                }
>>>>>>> Stashed changes

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
            Runnable RunnableGenerateSun = new RunnableGenerateSun();
            Thread t = new Thread(RunnableGenerateSun);
            t.start();
            new Thread(() -> {
            int count = 200;
            while (count >= 0) {
                int innerCount = count;
                    // update the label text with the remaining time
                    SwingUtilities.invokeLater(() -> {
                       numSun.setText(Integer.toString(Sun.getTotalSun()));
                    });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                count--;
            }}).start();
        }
        else if(e.getSource() == menuButton) {
            RemoveButtons();
            SwitchToMenuFrame();
        }

    }

}
