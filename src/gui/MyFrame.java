package gui;
import javax.imageio.ImageIO;
import javax.management.timer.Timer;
import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.lang.reflect.*;
import organism.plant.*;
import thread.RunnableGameTimer;
import thread.RunnableGenerateSun;
import thread.RunnableZombieSpawn;
import thread.RunnableGenerateSun.*;
import thread.ThreadManager;
import sun.Sun;
import exception.*;
import grid.*;

import organism.zombie.*;

import loadsave.*;
import java.io.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.Flow;

import static organism.plant.Plant.getPlantingCooldown;
import static organism.plant.Plant.getPlantingSpeed;

import java.awt.*;


public class MyFrame extends JFrame implements ActionListener, Serializable {
    private int currentFrame;
    private int count;
    private ArrayList<Integer> deckAvailability = new ArrayList<Integer>();
    private HashMap<Integer, Integer> plantStorage = new HashMap<Integer, Integer>();
    Thread GUIThread;
    Deck deck;
    Inventory inventory; 
    Lawn mainlawn;
    RunnableZombieSpawn runzombie;
    public int idxselectedplant = -1;
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

    ArrayList<ArrayList<JButton>> mapButtons = new ArrayList<ArrayList<JButton>>();
    ArrayList<JButton> deckButtons = new ArrayList<JButton>(7);
    ArrayList<JButton> inventoryButtons = new ArrayList<JButton>(10);
    ArrayList<JButton> tempMapRow;
    JButton startButton;
    JButton loadButton;
    JButton plantsListButton;
    JButton zombieListButton;
    JButton exitButton;
    JButton menuButton;
    JButton readyButton;
    JButton deckButton;
    JButton shovelButton;
    JLabel numSun;
    boolean rungame;

    static final int FRAME_WIDTH = 638;
    static final int FRAME_HEIGHT = 480;
    static final int TILE_WIDTH = 50;
    static final int TILE_HEIGHT = 60;
    static final int LARGE_WIDTH = 320;
    static final int LARGE_HEIGHT = 50;
    static final int SMALL_WIDTH = 100;
    static final int SMALL_HEIGHT = 30;
    
    static final Color BACKGROUND_COLOR = new Color(0xC4BFB6);
    static final Color BUTTON_COLOR = new Color(0x8C857B);
    static final Color GRASS1_COLOR = new Color(0x4C8C2B);
    static final Color GRASS2_COLOR = new Color(0x6CC24A);
    static final Color WATER_COLOR = new Color(0x59CBE8);
    static final Color BORDER_DECK_COLOR = new Color(0x855200);
    BufferedImage myImage;

    // for saving purposes
    // Load.LoadHolder Load.LoadHolder = new Load.LoadHolder();
  
    class ImagePanel extends JComponent implements Serializable {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void setImage(Image newimage){
        image = newimage;
    }
}
    
    ImagePanel imagepan = new ImagePanel(null);
    public MyFrame() {

       
        for(int i = 0; i < 6; i++){
            deckAvailability.add(1);
        }

        
        try {
            myImage = ImageIO.read(new File("src/assets/backgroundmainmenu.png"));
            imagepan.setImage(myImage);
            this.setContentPane(imagepan);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        getContentPane().setBackground(BACKGROUND_COLOR);
        SetPanels();
        SetButtons();
        SwitchToMenuFrame();
        setVisible(true);
    }


    public void SwitchToMenuFrame() {

        currentFrame = 0;
        menuPanel.setVisible(true);
        exitButton.setVisible(true);

    }

    public void SwitchToDeckFrame() {
        try{
            myImage = ImageIO.read(new File("src/assets/deckmenu.png"));
            imagepan.setImage(myImage);
            setContentPane(imagepan);
        } catch (IOException e3) {
            
            e3.printStackTrace();
        }
        currentFrame = 1;
        menuButton.setVisible(true);
        deckPanel.setVisible(true);
        inventoryPanel.setVisible(true);
        readyButton.setVisible(true);
        for (int i = 0; i < 6; i++) {
            plantStorage.put(i, -1);
        }

    }

    public void SwitchToGameFrame() {
        try {
            myImage = ImageIO.read(new File("src/assets/gameframe.png"));
            imagepan.setImage(myImage);
            setContentPane(imagepan);
        } catch (IOException e3) {
            
            e3.printStackTrace();
        }
        currentFrame = 2;
        menuButton.setVisible(true);
        mapPanel.setVisible(true);
        deckPanel.setVisible(true);
        shovelButton.setVisible(true);
    }

    public void RemoveButtons() {

        if (currentFrame == 0) {
            menuPanel.setVisible(false);
            exitButton.setVisible(false);
            }
        else if (currentFrame == 1) {
            menuButton.setVisible(false);
            deckPanel.setVisible(false);
            inventoryPanel.setVisible(false);
            readyButton.setVisible(false); 
            shovelButton.setVisible(false);  
        }
        else if (currentFrame == 2) {
            menuButton.setVisible(false);
            mapPanel.setVisible(false);
            deckPanel.setVisible(false);
            shovelButton.setVisible(false);  
        }

    }

    public void SetPanels() {
        menuPanel = CreatePanel(160, 210, LARGE_WIDTH, LARGE_HEIGHT*4 + 30);
        deckPanel = CreatePanel(10, 10, TILE_WIDTH * 8 + 10, TILE_HEIGHT);
        inventoryPanel = CreatePanel(70, 80, TILE_WIDTH * 5, TILE_HEIGHT * 5);
        mapPanel = CreatePanel(75, 90, TILE_WIDTH * 11, TILE_HEIGHT * 6);
    }

    public void SetButtons() {

        //Interaction Buttons
        startButton = CreateButton(0, 0, LARGE_WIDTH, LARGE_HEIGHT, BUTTON_COLOR, "START", menuPanel, new ImageIcon("src/assets/startbutton.png"));
        loadButton = CreateButton(0, LARGE_HEIGHT + 10, LARGE_WIDTH, LARGE_HEIGHT, BUTTON_COLOR, "HELP", menuPanel, 
                new ImageIcon("src/assets/loadbutton.png"));
        plantsListButton = CreateButton(0, (LARGE_HEIGHT + 10) * 2, LARGE_WIDTH, LARGE_HEIGHT, BUTTON_COLOR, "PLANTS LIST", menuPanel, 
                new ImageIcon("src/assets/plantlistbutton.png"));
        zombieListButton = CreateButton(0, (LARGE_HEIGHT + 10) * 3, LARGE_WIDTH, LARGE_HEIGHT, BUTTON_COLOR, "ZOMBIES LIST", menuPanel,
                new ImageIcon("src/assets/zombielistbutton.png"));

        exitButton = CreateButton(10, 10, SMALL_WIDTH, SMALL_HEIGHT, BUTTON_COLOR, "EXIT", menuPanel, 
                new ImageIcon("src/assets/exitbutton.png"));
       
        menuButton = CreateButton(530, 10, SMALL_WIDTH, SMALL_HEIGHT, BUTTON_COLOR, null, 
                new ImageIcon("src/assets/exitbutton.png"));
        readyButton = CreateButton(60, 390, LARGE_WIDTH + 10, LARGE_HEIGHT, BUTTON_COLOR, null,
                new ImageIcon("src/assets/readybutton.png"));

        //deckButtons
        deck = new Deck();
       
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                deckButton = CreateButton(TILE_WIDTH * i -10, 0, 70, 60, BUTTON_COLOR, null, deckPanel, 
                        new ImageIcon("src/assets/sun.png"));
                numSun = new JLabel();
                numSun.setText(Integer.toString(Sun.getTotalSun()));
                numSun.setAlignmentX(0.6f);
                numSun.setHorizontalTextPosition(JLabel.CENTER);
                numSun.setVerticalTextPosition(JLabel.CENTER);
                numSun.setVisible(true);
                numSun.setOpaque(false);
                deckButton.add(numSun);
                deckButtons.add(deckButton);
                deckButtons.get(i).setEnabled(true);
                
            }
            else{
                deckButton = CreateButton(TILE_WIDTH * i + 10, 0, TILE_WIDTH, TILE_HEIGHT, BORDER_DECK_COLOR, null, deckPanel, new ImageIcon("src/assets/decktile.png"));
                deckButton.setLayout(new GridBagLayout());
                deckButtons.add(deckButton);
            }
        }
        //shovel button
        shovelButton = CreateButton(380, 10, TILE_WIDTH, TILE_HEIGHT, BORDER_DECK_COLOR, null, new ImageIcon("src/assets/decktile.png"));
        shovelButton.setLayout(new GridBagLayout());
        JLabel shovel = new JLabel();
        ImageIcon img = new ImageIcon("src/assets/shovel.png");
        shovel.setBounds(0, 0, TILE_WIDTH, TILE_HEIGHT);
        shovel.setAlignmentX(-0.6f);
        shovel.setVisible(true);
        shovel.setIcon(new ImageIcon(img.getImage()
                .getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        shovelButton.add(shovel);
        //inventory button
        inventory = new Inventory();
        
        for (int i = 0; i < 10; i++) {

            inventoryButtons.add(CreateButton(TILE_WIDTH * (i % 5), i / 5 * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, GRASS2_COLOR, null, inventoryPanel, new ImageIcon(getPlantButtonSourceImg(inventory, i))));
            inventoryButtons.get(i).setLayout(new GridBagLayout());
           // setPlants(true, getPlantSourceImg(inventory, i), i);
            
        }
       
        //map buttons
        for (int i = 0; i < 6; i++) {
            tempMapRow = new ArrayList<JButton>();
            for (int j = 0; j < 11; j++) {
                
                if (j == 0) {
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, BUTTON_COLOR, null, mapPanel, new ImageIcon("src/assets/bricktile.png")));
                    
                } else if(j == 10){
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, BUTTON_COLOR,
                            null, mapPanel, new ImageIcon("src/assets/gravetile.png")));
                    
                }
                else if (i == 2 || i == 3) {
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, WATER_COLOR, null, mapPanel, 
                            new ImageIcon("src/assets/watertile.png")));
                }
                else if ((i + j) % 2 == 0) {
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, GRASS2_COLOR, null, mapPanel, new ImageIcon("src/assets/grasstile1.png")));
                }
                else {
                    tempMapRow.add(CreateButton(TILE_WIDTH * j, TILE_HEIGHT * i, TILE_WIDTH, TILE_HEIGHT, GRASS1_COLOR, null, mapPanel, 
                            new ImageIcon("src/assets/grasstile2.png")));
                } 
                tempMapRow.get(j).setLayout(new GridBagLayout());
            
            }
            mapButtons.add(tempMapRow);
            
        }
    }
    
    private String getZombieSourceImg(Lawn mainlawn, int i, int j) {
        String source = "";
        for(int k = 0; k < mainlawn.getLand().get(i).get(j).getZombies().size(); k++){
            if (mainlawn.getLand().get(i).get(j).getZombies().get(k) instanceof NormalZombie) {
                source = "src/assets/normalzombie.png";
            }
            if (mainlawn.getLand().get(i).get(j).getZombies().get(k) instanceof ConeheadZombie) {
                source = "src/assets/coneheadzombie.png";
            }
            if (mainlawn.getLand().get(i).get(j).getZombies().get(k) instanceof BucketheadZombie) {
                source = "src/assets/bucketheadzombie.png";
            }
            if (mainlawn.getLand().get(i).get(j).getZombies().get(k) instanceof NewspaperZombie) {
                source = "src/assets/newspaperzombie.png";
            }
            if (mainlawn.getLand().get(i).get(j).getZombies().get(k) instanceof PoleVaultingZombie) {
                source = "src/assets/polevaultingzombie.png";
            }

            if (mainlawn.getLand().get(i).get(j).getZombies().get(k) instanceof JackInTheBoxZombie) {
                source = "src/assets/jackintheboxzombie.png";
            }

            if (mainlawn.getLand().get(i).get(j).getZombies().get(k) instanceof SnorkelZombie) {
                source = "src/assets/snorkelzombie.png";
            }
            if (mainlawn.getLand().get(i).get(j).getZombies().get(k) instanceof DolphinRiderZombie) {
                source = "src/assets/dolphinriderzombie.png";
            }
            if (mainlawn.getLand().get(i).get(j).getZombies().get(k) instanceof Gargantuar) {
                source = "src/assets/gargantuar.png";
            }
            if (mainlawn.getLand().get(i).get(j).getZombies().get(k) instanceof DuckyTubeZombie) {
                source = "src/assets/duckytubezombie.png";
            }
            
            
        }
        return source;
    }
    public void setZombies(int i, int j){
        JLabel zombie = new JLabel();
        zombie.setHorizontalTextPosition(JLabel.CENTER);
        zombie.setVerticalTextPosition(JLabel.CENTER);
        zombie.setVisible(true);
        zombie.setOpaque(false);
        zombie.setIcon(new ImageIcon(new ImageIcon(getZombieSourceImg(mainlawn, i, j)).getImage()
                .getScaledInstance(40, 60, Image.SCALE_SMOOTH)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0; // Overlay on the same cell as the button
        gbc.anchor = GridBagConstraints.EAST;
        mapButtons.get(i).get(j).add(zombie, gbc);
        mapButtons.get(i).get(j).revalidate();
        mapButtons.get(i).get(j).repaint();
    }

    public void setPlants(boolean onInventory, String srcfile, int i){
       
        if(onInventory){
            JButton inventorybuttonnew = CreateButton(TILE_WIDTH * (i % 5), i / 5 * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, GRASS2_COLOR,
                            null, inventoryPanel, new ImageIcon(getPlantButtonSourceImg(inventory, i)));
            inventoryButtons.set(i, inventorybuttonnew);
        } else {
            //eckButtons.get(i).add(plant);
            
            JButton deckButtonnew = CreateButton(TILE_WIDTH * i + 10, 0, TILE_WIDTH, TILE_HEIGHT, BORDER_DECK_COLOR, null,
                    deckPanel, new ImageIcon(srcfile));
            deckButtonnew.setVisible(true);
            deckButtons.get(i).setVisible(false);
            deckButtons.set(i, deckButtonnew);
            deckButtons.get(i).revalidate();
        } 
    }

    public void removePlant(String srcfile, int i){
        JButton deckButtonnew = CreateButton(TILE_WIDTH * i + 10, 0, TILE_WIDTH, TILE_HEIGHT, BORDER_DECK_COLOR, null,
                deckPanel, new ImageIcon(srcfile));
        deckButtonnew.setVisible(true);
        deckButtons.get(i).setVisible(false);
        deckButtons.set(i, deckButtonnew);
        deckButtons.get(i).revalidate();
    }

    public void setPlants(String srcfile, int i, int j, int idplant) throws InvalidDeployException{
        JLabel plant = new JLabel();
        ImageIcon img = new ImageIcon(srcfile);
        plant.setBounds(0, 0, TILE_WIDTH, TILE_HEIGHT);
        plant.setVisible(true);
        plant.setIcon(new ImageIcon(img.getImage()
                .getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.CENTER;
        mapButtons.get(i).get(j).add(plant, gbc);

        if(deck.getPlayablePlants().get(idplant).getClassPlant() == Lilypad.class){
            mapButtons.get(i).get(j).setComponentZOrder(plant, 1);
        } else {
            mapButtons.get(i).get(j).setComponentZOrder(plant, 0);
        }
       
    }
    
    private String getPlantSourceImg(Deck deck, int i){
        String source = "";
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Sunflower")) {
            source = "src/assets/sunflower.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("CherryBomb")) {
            source = "src/assets/cherrybomb.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Chomper")) {
            source = "src/assets/chomper.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Lilypad")) {
            source = "src/assets/lilypad.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Squash")) {
            source = "src/assets/squash.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Peashooter")) {
            source = "src/assets/peashooter.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Repeater")) {
            source = "src/assets/repeater.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Snowpea")) {
            source = "src/assets/snowpea.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("TangleKelp")) {
            source = "src/assets/tanglekelp.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Wallnut")) {
            source = "src/assets/wallnut.png";
        }
        return source;
    }

    private String getPlantButtonSourceImg(Deck deck, int i) {
        String source = "";
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Sunflower")) {
            source = "src/assets/sunflowerbutton.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("CherryBomb")) {
            source = "src/assets/cherrybombbutton.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Chomper")) {
            source = "src/assets/chomperbutton.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Lilypad")) {
            source = "src/assets/lilypadbutton.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Squash")) {
            source = "src/assets/squashbutton.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Peashooter")) {
            source = "src/assets/peashooterbutton.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Repeater")) {
            source = "src/assets/repeaterbutton.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Snowpea")) {
            source = "src/assets/snowpeabutton.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("TangleKelp")) {
            source = "src/assets/tanglekelpbutton.png";
        }
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("Wallnut")) {
            source = "src/assets/wallnutbutton.png";
        }
        return source;
    }

    private String getPlantButtonSourceImg(Inventory deck, int i) {
        String source = "";
        if (deck.getAllPlants().get(i).getClassPlant().getSimpleName().equals("Sunflower")) {
            source = "src/assets/sunflowerbutton.png";
        }
        if (deck.getAllPlants().get(i).getClassPlant().getSimpleName().equals("CherryBomb")) {
            source = "src/assets/cherrybombbutton.png";
        }
        if (deck.getAllPlants().get(i).getClassPlant().getSimpleName().equals("Chomper")) {
            source = "src/assets/chomperbutton.png";
        }
        if (deck.getAllPlants().get(i).getClassPlant().getSimpleName().equals("Lilypad")) {
            source = "src/assets/lilypadbutton.png";
        }
        if (deck.getAllPlants().get(i).getClassPlant().getSimpleName().equals("Squash")) {
            source = "src/assets/squashbutton.png";
        }
        if (deck.getAllPlants().get(i).getClassPlant().getSimpleName().equals("Peashooter")) {
            source = "src/assets/peashooterbutton.png";
        }
        if (deck.getAllPlants().get(i).getClassPlant().getSimpleName().equals("Repeater")) {
            source = "src/assets/repeaterbutton.png";
        }
        if (deck.getAllPlants().get(i).getClassPlant().getSimpleName().equals("SnowPea")) {
            source = "src/assets/snowpeabutton.png";
        }
        if (deck.getAllPlants().get(i).getClassPlant().getSimpleName().equals("TangleKelp")) {
            source = "src/assets/tanglekelpbutton.png";
        }
        if (deck.getAllPlants().get(i).getClassPlant().getSimpleName().equals("Wallnut")) {
            source = "src/assets/wallnutbutton.png";
        }
        return source;
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

    public JButton CreateButton(int x, int y, int width, int height, Color color, String text, ImageIcon i) {

        JButton newButton = new JButton(i);
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
    private void setDeckNotAvailable(int i){
        deckAvailability.set(i, 0);
    }
    
    private void setDeckAvailable(int i) {
        deckAvailability.set(i, 1);
    }
    private int getDeckAvalibility(){
        int index = 0;
        for(Integer i : deckAvailability){
            if(i == 1){
                break;
            }
            index++;
        }
        return index;
    }

    public void SetButtonDisabled(int i, String srcfile) {
        inventoryButtons.get(i).setVisible(false);
        JButton inventorybuttonnew = CreateButton(TILE_WIDTH * (i % 5), i / 5 * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT,
                GRASS2_COLOR,
                null, inventoryPanel, new ImageIcon(srcfile));
        inventorybuttonnew.setVisible(true);
        inventoryButtons.set(i, inventorybuttonnew);
        
    }

    public void SetButtonEnabled(int i, String srcfile) {
        inventoryButtons.get(i).setVisible(false);
        JButton inventorybuttonnew = CreateButton(TILE_WIDTH * (i % 5), i / 5 * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT,
                GRASS2_COLOR,
                null, inventoryPanel, new ImageIcon(srcfile));
        inventorybuttonnew.setVisible(true);
        inventoryButtons.set(i, inventorybuttonnew);
        
    }
    private void startPlantCooldown(Deck deck, int i){
        new Thread(new Runnable(){
            public void run(){
                int terminator = 0;
                PlantCard c = deck.getPlayablePlants().get(i - 1);
                terminator = c.getPlantingCooldown();
               
                while (terminator > 0) {
                    c.updatePlantingCooldown();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    terminator--;
                }
                deck.getPlayablePlants().get(i - 1).afterPlant();
                
            }
        }).start();
        
    }
    public boolean isPlantEnoughSun(int cost){
        if(cost <= Sun.getTotalSun()){
            return true;
        } else {
            return false;
        }
    }

    public JLabel CreateLabel() {return new JLabel();}
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 10; i++){ //buat add plant
            try{
                if(e.getSource() == inventoryButtons.get(i)){
                System.out.println(deck.getPlayablePlants());
                inventory.addPlant(inventory.getAllPlants().get(i), deck, getDeckAvalibility());
                setPlants(false, getPlantButtonSourceImg(inventory, i), getDeckAvalibility() + 1);
                
                plantStorage.put(getDeckAvalibility(), i);
                setDeckNotAvailable(getDeckAvalibility());
                deckButtons.get(getDeckAvalibility()).revalidate();
                SetButtonDisabled(i, "src/assets/decktiledisabled.png");
                
                
                inventoryButtons.get(i).revalidate();
            }
            } catch(InvalidInventoryException e2){
                e2.getMessage();
            }
            
        }
        if(currentFrame == 1){ // buat remove plant
            
            for(int i = 0; i < 6; i++){
                if(e.getSource() == deckButtons.get(i+1)){
                    setDeckAvailable(i);
                    try {
                       
                        System.out.println(deck.getPlayablePlants());
                        SetButtonEnabled(plantStorage.get(i),
                                getPlantButtonSourceImg(inventory, plantStorage.get(i)));
                        plantStorage.put(i, -1);
                        System.out.println(plantStorage);
                        // deckButtons.get(getDeckAvalibility() + 1).removeAll();
                        // deckButtons.get(getDeckAvalibility()).revalidate();
                        removePlant("src/assets/decktile.png", i + 1);
                        inventory.removePlant(deck, getDeckAvalibility());
                        System.out.println(deck.getPlayablePlants());
                        
                    } catch (InvalidInventoryException e1) {
                        
                        e1.getMessage();
                    }
                    
                    
                }

            }
        }
        if(currentFrame == 2){
            boolean plantselected = false;
            for (int i = 1; i < 7; i++) {
                if (e.getSource() == deckButtons.get(i)) {
                    System.out.println("plant yang diambil : "+ deck.getPlayablePlants().get(i-1).getClassPlant().getSimpleName());
                    plantselected = true;
                    idxselectedplant = i;
                }
            }
               
                    for (int j = 0; j < 6; j++) {
                        for (int k = 0; k < 11; k++) {
                            if (e.getSource() == mapButtons.get(j).get(k) && deckButtons.get(idxselectedplant).isEnabled()) {
                                System.out.println("dipencet map : pada x : " + j + " y : " + k);
                                try {
                                    setPlants(getPlantSourceImg(deck, idxselectedplant-1), j, k, idxselectedplant-1);
                                    deck.addPlantToMap(idxselectedplant - 1, mainlawn, j, k);
                                } catch (InvalidDeployException e1) {
                                    e1.getMessage();
                                }
                                mapButtons.get(j).get(k).revalidate();
                                startPlantCooldown(deck, idxselectedplant);
                                deckButtons.get(idxselectedplant).setEnabled(false);
                                Sun.reduceSun(deck.getPlayablePlants().get(idxselectedplant-1).getCost());

                            }
                        }
                    }
                  
        }
        
        if(e.getSource() != null) {
            // jika button apapun dipress
        }
        if (e.getSource() == menuButton && currentFrame == 2) {
            
            new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println("Threads in manager (save): " + ThreadManager.getInstance().getList().size());
                    Save.save("testSave.ser", mainlawn, ThreadManager.getInstance().getList(), deck);                    
                    System.out.println("Save executed");
                    ThreadManager.getInstance().stopAllThreads();
                    count = -1;
                    System.out.println("Thread Interrupted");
                }

            }).start();
            RemoveButtons();
            SwitchToMenuFrame();
            try {
                myImage = ImageIO.read(new File("src/assets/backgroundmainmenu.png"));
                imagepan.setImage(myImage);
                this.setContentPane(imagepan);
            } catch (IOException e4) {
                
                e4.printStackTrace();
            }
        }
        if(e.getSource() == startButton) {
           
            RemoveButtons();
            SwitchToDeckFrame();
        } else if(e.getSource() == loadButton) {
            RemoveButtons();
            SwitchToGameFrame();
        }
        else if(e.getSource() == exitButton) {
            dispose();
        }
        else if (e.getSource() == readyButton) {
            try{
                if(deck.getPlayablePlants().size() == deck.getMaxPlants()){

                } else {
                    throw new InvalidInventoryException("Deck Belum Penuh");
                }
            } catch(InvalidInventoryException eee){
                System.out.println(eee);
            }
            if(deck.getPlayablePlants().size() == deck.getMaxPlants()){
                RemoveButtons();
                SwitchToGameFrame();
                /*
                 * if (!Load.getHasLoaded()){
                 * System.out.println("Frame not loaded, loading other components...");
                 * if (Load.load("testSave.ser")) {
                 * mainlawn = Load.LoadHolder.lawn;
                 * runzombie = Load.LoadHolder.zomSpawn;
                 * ThreadManager.getInstance().addThread(runzombie);
                 * ThreadManager.getInstance().addThread(Load.LoadHolder.genSun);
                 * ThreadManager.getInstance().addThread(Load.LoadHolder.gameTimer);
                 * } else {
                 * 
                 * }
                 * } else {
                 * System.out.println("already loaded frame");
                 * }
                 * 
                 * 
                 */
                
            mainlawn = new Lawn();
            runzombie = new RunnableZombieSpawn(200, mainlawn);
            ThreadManager.getInstance().addThread(new RunnableGenerateSun(100));
            ThreadManager.getInstance().addThread(new RunnableGameTimer(200));
            ThreadManager.getInstance().addThread(runzombie);
            System.out.println("Threads in manager (load): " + ThreadManager.getInstance().getList().size());
            System.out.println("Game timer in manager (load): " + ThreadManager.getInstance().getRunnableGameTimer().getCurrentGameTime());
            ThreadManager.getInstance().startAllThreads();;

                new Sun();
                for (int i = 1; i < 7; i++) {
                    deckButtons.get(i).setEnabled(false);
                    startPlantCooldown(deck, i);
                }

                        // update the every text here
                
                GUIThread =
                new Thread(()-> {
                    count = 200;
                    rungame = true;
                    while(rungame){
                                SwingUtilities.invokeLater(() -> {
                                    for (int i = 1; i < deckButtons.size(); i++) {
                                           // System.out.println(isPlantEnoughSun(deck.getPlayablePlants().get(i-1).getCost()));
                                            if (!deckButtons.get(i).isEnabled()) {
                                                if (deck.getPlayablePlants().get(i-1).getPlantingCooldown() == deck.getPlayablePlants().get(i-1).getPlantingSpeed()
                                                        && isPlantEnoughSun(deck.getPlayablePlants().get(i-1).getCost())) {
                                                    deckButtons.get(i).setEnabled(true);
                                                } else if (deck.getPlayablePlants().get(i - 1)
                                                        .getPlantingCooldown() != deck.getPlayablePlants().get(i - 1)
                                                            .getPlantingSpeed()
                                                        && isPlantEnoughSun(
                                                                deck.getPlayablePlants().get(i-1).getCost())) {
                                                    deckButtons.get(i).setEnabled(false);
                                                }

                                            
                                        }
                                    }
                                    numSun.setText(Integer.toString(Sun.getTotalSun()));
                                    for (Runnable r : ThreadManager.getInstance().getList()) {
                                        if (r instanceof RunnableGameTimer) {
                                            if (((RunnableGameTimer) r).getCurrentGameTime() != 0) {
                                                this.setTitle("Game "
                                                        + String.valueOf(((RunnableGameTimer) r).getCurrentGameTime())
                                                        + " seconds remaining");
                                                count = ((RunnableGameTimer) r).getCurrentGameTime();
                                            } else {
                                                this.setTitle("Game paused");
                                            }

                                        }
                                    }
                                    for (int i = 0; i < mapButtons.size(); i++) {
                                        for (int j = 0; j < tempMapRow.size(); j++) {
                                            if (mainlawn.getLand().get(i).get(j).hasZombie()) {
                                                setZombies(i, j);
                                                // taro code moveZombies disini -Vald
                                                mapButtons.get(i).get(j).revalidate();
                                                ArrayList<Zombie> currentZombies = new ArrayList<>(
                                                        mainlawn.getLand().get(i).get(j).getZombies());
                                                for (Zombie z : currentZombies) {
                                                    z.setMoveCooldown(z.getMoveCooldown() - 1);
                                                    z.setAttackCooldown(z.getAttackCooldown() - 1);
                                                    if (mainlawn.getLand().get(i).get(j).hasPlant()) {
                                                        if (z instanceof VaultingInterface) {
                                                            VaultingInterface v = (VaultingInterface) z;
                                                            // System.out.println(z.getName() + "is Vaulting Over " +
                                                            // mainlawn.getLand().get(i).get(j-1).getPlant().getName());
                                                            if (!v.getHasVaulted()) {
                                                                System.out.println(z.getName() + " vaulting 2 tile");
                                                                v.vault(mainlawn.getLand().get(i).get(j),
                                                                        mainlawn.getLand().get(i).get(j - 1));
                                                                setZombies(i, j - 1);
                                                                if (mainlawn.getLand().get(i).get(j)
                                                                        .getPlant() instanceof Squash) {
                                                                    mainlawn.getLand().get(i).get(j - 1)
                                                                            .removeZombie(z);
                                                                }
                                                            }
                                                        } else if (mainlawn.getLand().get(i).get(j - 1).hasPlant()) {
                                                            if (z instanceof VaultingInterface) {
                                                                VaultingInterface v = (VaultingInterface) z;
                                                                // System.out.println(z.getName() + "is Vaulting Over "
                                                                // +
                                                                // mainlawn.getLand().get(i).get(j-1).getPlant().getName());
                                                                if (!v.getHasVaulted()) {
                                                                    System.out
                                                                            .println(z.getName() + " vaulting 3 tile");
                                                                    v.vault(mainlawn.getLand().get(i).get(j),
                                                                            mainlawn.getLand().get(i).get(j - 2));
                                                                    setZombies(i, j - 2);
                                                                }
                                                                // System.out.println("!!! END OF VAULTING TYPE !!!");
                                                                else if (z.getAttackCooldown() == 0) {
                                                                    mainlawn.getLand().get(i).get(j - 1).getPlant()
                                                                            .loseHealth(z.getAttackDamage());
                                                                    z.attack();
                                                                }
                                                            } else {
                                                                if (z.getAttackCooldown() == 0) {
                                                                    mainlawn.getLand().get(i).get(j - 1).getPlant()
                                                                            .loseHealth(z.getAttackDamage());
                                                                    z.attack();
                                                                }
                                                            }
                                                        } else {
                                                            if (z.getMoveCooldown() == 0) {
                                                                z.move(mainlawn.getLand().get(i).get(j),
                                                                        mainlawn.getLand().get(i).get(j - 1));
                                                                setZombies(i, j - 1);
                                                            }
                                                        }
                                                    } else if (mainlawn.getLand().get(i).get(j - 1).hasPlant()) {
                                                        if (z instanceof VaultingInterface) {
                                                            VaultingInterface v = (VaultingInterface) z;
                                                            // System.out.println(z.getName() + "is Vaulting Over " +
                                                            // mainlawn.getLand().get(i).get(j-1).getPlant().getName());
                                                            if (!v.getHasVaulted()) {
                                                                System.out.println(z.getName() + " vaulting 3 tile");
                                                                v.vault(mainlawn.getLand().get(i).get(j),
                                                                        mainlawn.getLand().get(i).get(j - 2));
                                                                setZombies(i, j - 2);
                                                                if (mainlawn.getLand().get(i).get(j - 1)
                                                                        .getPlant() instanceof Squash) {
                                                                    mainlawn.getLand().get(i).get(j - 2)
                                                                            .removeZombie(z);
                                                                }
                                                            }
                                                            // System.out.println("!!! END OF VAULTING TYPE !!!");
                                                            else if (z.getAttackCooldown() == 0) {
                                                                mainlawn.getLand().get(i).get(j - 1).getPlant()
                                                                        .loseHealth(z.getAttackDamage());
                                                                z.attack();
                                                            }
                                                        } else {
                                                            if (z.getAttackCooldown() == 0) {
                                                                mainlawn.getLand().get(i).get(j - 1).getPlant()
                                                                        .loseHealth(z.getAttackDamage());
                                                                z.attack();
                                                            }
                                                        }
                                                    } else {
                                                        if (z.getMoveCooldown() == 0) {
                                                            z.move(mainlawn.getLand().get(i).get(j),
                                                                    mainlawn.getLand().get(i).get(j - 1));
                                                            setZombies(i, j - 1);
                                                        }
                                                        // System.out.println("=== After ===");
                                                        if (!(mainlawn.getLand().get(i).get(j).hasZombie())) {
                                                            removeZombies(i, j);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                });
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }

                                for (int i = 0; i < 6; i++) {
                                    if (mainlawn.getLand().get(i).get(0).hasZombie()) {
                                        rungame = false;
                                    }
                                }
                                // System.out.println("Checking endgame");
                                // System.out.println("Rungame before and: " + rungame);
                                // System.out.println("ZombieCount: " + runzombie.getZombieCount());
                                if (count <= 0) {
                                    rungame = rungame && (count <= 0 && runzombie.getZombieCount() > 0);
                                }
                                count--;
                    }
                    ThreadManager.getInstance().stopAllThreads();
                        // System.out.println("Rungame after and: " + rungame);
                        // System.out.println("=====================");
                    });
                    GUIThread.start();
                   
                
            } else {
                try {
                    throw new InvalidInventoryException("Deck Belum Penuh");
                } catch (InvalidInventoryException e1) {
                    
                    e1.getMessage();
                }
            }
          
        } 
            
            
                // System.out.println("Checking endgame");
                // System.out.println("Rungame before and: " + rungame);
                // System.out.println("ZombieCount: " + runzombie.getZombieCount());
               
            

            
    }
        

}


