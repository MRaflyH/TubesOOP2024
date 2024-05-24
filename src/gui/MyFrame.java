package gui;
import javax.imageio.ImageIO;

import javax.swing.*;

import organism.plant.*;
import thread.RunnableGameTimer;
import thread.RunnableGenerateSun;
import thread.RunnableZombieSpawn;
import visitor.PlantVisitor;
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
import java.awt.image.RescaleOp;
import java.util.*;

import java.awt.*;


public class MyFrame extends JFrame implements ActionListener, Serializable {
    private int currentFrame;
    private int count;
    private boolean gamelose = false;
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
    JPanel plantListPanel;
    JPanel mapPanel;
    JPanel deckPanel;
    JPanel inventoryPanel;
    JPanel swapPanel;
    JPanel helpPanel;

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
    JButton backMenuButton;
    JButton nextArrowButton;
    JButton previousArrowButton;
    JButton helpButton;
    JLabel helpText;
    JLabel numSun;
    boolean rungame;
    boolean winFlag;

    JButton swap1;
    JButton swap2;
    JTextField swap11;
    JTextField swap12;
    JTextField swap21;
    JTextField swap22;

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
        try {
            myImage = ImageIO.read(new File("src/assets/backgroundmainmenu.png"));
            imagepan.setImage(myImage);
            this.setContentPane(imagepan);
        } catch (IOException e) {

            e.printStackTrace();
        }
        currentFrame = 0;
        menuPanel.setVisible(true);
        menuButton.setVisible(true);

    }

    public void SwitchToPlantListFrame(){
        try {
            myImage = ImageIO.read(new File("src/assets/plantlist.png"));
            imagepan.setImage(myImage);
            setContentPane(imagepan);
           
        } catch (IOException e3) {

            e3.printStackTrace();
        }
        currentFrame = 3;
        plantListPanel.setVisible(true);
        backMenuButton.setVisible(true);
        nextArrowButton.setVisible(true);
        previousArrowButton.setVisible(true);
        menuButton.setVisible(false);

    }

    public void SwitchToPlantListFrame2() {
        try {
            myImage = ImageIO.read(new File("src/assets/plantlist2.png"));
            imagepan.setImage(myImage);
            setContentPane(imagepan);

        } catch (IOException e3) {

            e3.printStackTrace();
        }
        currentFrame = 4;
        plantListPanel.setVisible(true);
        backMenuButton.setVisible(true);
        nextArrowButton.setVisible(true);
        previousArrowButton.setVisible(true);
        menuButton.setVisible(false);

    }

    public void SwitchToZombieListFrame() {
        try {
            myImage = ImageIO.read(new File("src/assets/zombielist.png"));
            imagepan.setImage(myImage);
            setContentPane(imagepan);

        } catch (IOException e3) {

            e3.printStackTrace();
        }
        currentFrame = 5;
        plantListPanel.setVisible(true);
        backMenuButton.setVisible(true);
        nextArrowButton.setVisible(true);
        previousArrowButton.setVisible(true);
        menuButton.setVisible(false);

    }
    
    public void SwitchToZombieListFrame2() {
        try {
            myImage = ImageIO.read(new File("src/assets/zombielist2.png"));
            imagepan.setImage(myImage);
            setContentPane(imagepan);

        } catch (IOException e3) {

            e3.printStackTrace();
        }
        currentFrame = 6;
        plantListPanel.setVisible(true);
        backMenuButton.setVisible(true);
        nextArrowButton.setVisible(true);
        previousArrowButton.setVisible(true);
        menuButton.setVisible(false);

    }

    public void SwitchToHelpFrame() {
        try {
            myImage = ImageIO.read(new File("src/assets/backgroundmainmenu.png"));
            imagepan.setImage(myImage);
            this.setContentPane(imagepan);
        } catch (IOException e) {

            e.printStackTrace();
        }
        currentFrame = 7;
        helpPanel.setVisible(true);
        backMenuButton.setVisible(true);
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
        swapPanel.setVisible(true);
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
            backMenuButton.setVisible(false);
            }
        else if (currentFrame == 1) {
            menuButton.setVisible(false);
            deckPanel.setVisible(false);
            inventoryPanel.setVisible(false);
            readyButton.setVisible(false); 
            shovelButton.setVisible(false);  
            backMenuButton.setVisible(false);
            swapPanel.setVisible(false);
        }
        else if (currentFrame == 2) {
            menuButton.setVisible(false);
            mapPanel.setVisible(false);
            deckPanel.setVisible(false);
            shovelButton.setVisible(false);  
            backMenuButton.setVisible(false);
        } else if(currentFrame == 3 || currentFrame == 4 || currentFrame == 5 || currentFrame == 6){
            menuButton.setVisible(false);
            mapPanel.setVisible(false);
            deckPanel.setVisible(false);
            backMenuButton.setVisible(false);
            shovelButton.setVisible(false);
            nextArrowButton.setVisible(false);
            previousArrowButton.setVisible(false);
        } else if (currentFrame == 7) {
            helpPanel.setVisible(false);
            backMenuButton.setVisible(false);
        }

    }

    public void SetPanels() {
        menuPanel = CreatePanel(160, 210, LARGE_WIDTH + 10 + SMALL_WIDTH, LARGE_HEIGHT*4 + 30);
        plantListPanel = CreatePanel(160, 210, LARGE_WIDTH, LARGE_HEIGHT*4 + 30);
        deckPanel = CreatePanel(10, 10, TILE_WIDTH * 8 + 10, TILE_HEIGHT);
        inventoryPanel = CreatePanel(70, 80, TILE_WIDTH * 5, TILE_HEIGHT * 4);
        mapPanel = CreatePanel(75, 90, TILE_WIDTH * 11, TILE_HEIGHT * 6);
        swapPanel = CreatePanel(70, 80 + TILE_HEIGHT * 4, TILE_WIDTH * 4 + SMALL_WIDTH * 2 + 50, SMALL_HEIGHT);
        helpPanel = CreatePanel(160, 210, LARGE_WIDTH, LARGE_HEIGHT*4 + 30);
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
        helpButton = CreateButton(LARGE_WIDTH + 10, (LARGE_HEIGHT + 10) * 3, SMALL_WIDTH, SMALL_HEIGHT, BUTTON_COLOR, "HELP", menuPanel);
        menuButton = CreateButton(530, 10, SMALL_WIDTH, SMALL_HEIGHT, BUTTON_COLOR, null, 
                new ImageIcon("src/assets/exitbutton.png"));
        backMenuButton = CreateButton(0, 20, SMALL_WIDTH, SMALL_HEIGHT, BUTTON_COLOR, null,
                new ImageIcon("src/assets/backbutton.png"));
        nextArrowButton = CreateButton(480, 330, SMALL_WIDTH, SMALL_WIDTH, BUTTON_COLOR, null,
                new ImageIcon("src/assets/nextarrow.png"));
        previousArrowButton = CreateButton(
                360, 
                330, SMALL_WIDTH, SMALL_WIDTH, BUTTON_COLOR, null,
                new ImageIcon("src/assets/previousarrow.png"));

        readyButton = CreateButton(60, 390, LARGE_WIDTH, LARGE_HEIGHT, BUTTON_COLOR, null,
                new ImageIcon("src/assets/readybutton.png"));

        //deckButtons
        deck = new Deck();
       
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                deckButton = CreateButton(TILE_WIDTH * i -10, 0, 70, 60, BUTTON_COLOR, null, deckPanel, 
                        new ImageIcon("src/assets/sun.png"));
                numSun = new JLabel();
                numSun.setText(Integer.toString(Sun.getInstance().getTotalSun()));
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

        // swap
        swap1 = CreateButton(TILE_WIDTH * 2 + 20, 0, SMALL_WIDTH, SMALL_HEIGHT, BACKGROUND_COLOR, "D Swap", swapPanel);
        swap2 = CreateButton(TILE_WIDTH * 4 + SMALL_WIDTH + 50, 0, SMALL_WIDTH, SMALL_HEIGHT, BACKGROUND_COLOR, "Inv Swap", swapPanel);

        swap11 = new JTextField();
        swap11.setBounds(0, 0, TILE_WIDTH,SMALL_HEIGHT);
        swapPanel.add(swap11);
        swap12 = new JTextField();
        swap12.setBounds(TILE_WIDTH + 10, 0, TILE_WIDTH,SMALL_HEIGHT);
        swapPanel.add(swap12);
        swap21 = new JTextField();
        swap21.setBounds(TILE_WIDTH * 2 + SMALL_WIDTH + 30, 0, TILE_WIDTH,SMALL_HEIGHT);
        swapPanel.add(swap21);
        swap22 = new JTextField();
        swap22.setBounds(TILE_WIDTH * 3 + SMALL_WIDTH + 40, 0, TILE_WIDTH,SMALL_HEIGHT);
        swapPanel.add(swap22);

        // help
        helpText = new JLabel();
        helpText.setBounds(0, 0, LARGE_WIDTH, LARGE_HEIGHT*4 + 30);
        helpText.setBackground(BACKGROUND_COLOR);
        helpText.setOpaque(true);
        helpText.setText("<html>Selamat datang di \"Michael vs. Lalapan\", sebuah game parodi yang menggabungkan kegembiraan dari Plant Vs. Zombie dengan sentuhan lokal yang unik! Dalam game ini, Anda akan membantu Michael, seorang juru masak yang gigih, untuk melindungi restoran lalapannya dari serangan para hama lapar yang tak terhentikan. <br/><br/>" +
        "START: choose deck and \"SIKAT LALAPAN\" to start<br/>" + 
        "LOAD: load saved game <br/>" + 
        "PLANTS LIST: show plant list <br/>" + 
        "ZOMBIE LIST: show zombie list <br/>" + 
        "</html>");
        helpText.setHorizontalTextPosition(SwingConstants.CENTER);
        helpText.setVerticalTextPosition(SwingConstants.CENTER);
        helpText.setVisible(true);
        helpPanel.add(helpText);

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
    private void setZombies(int i, int j){
        JLabel zombie = new JLabel();
        zombie.setHorizontalTextPosition(JLabel.CENTER);
        zombie.setVerticalTextPosition(JLabel.CENTER);
        zombie.setVisible(true);
        zombie.setOpaque(false);
        zombie.setIcon(new ImageIcon(new ImageIcon(getZombieSourceImg(mainlawn, i, j)).getImage()
                .getScaledInstance(40, 60, Image.SCALE_SMOOTH)));
        GridBagConstraints gbc = new GridBagConstraints();
        int componentCount = mapButtons.get(i).get(j).getComponentCount();
        gbc.gridx = componentCount;
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.CENTER;
        mapButtons.get(i).get(j).add(zombie, gbc);
        mapButtons.get(i).get(j).revalidate();
        mapButtons.get(i).get(j).repaint();
    }

    private void setPlants(boolean onInventory, String srcfile, int i){
       
        if(onInventory){
            JButton inventorybuttonnew = CreateButton(TILE_WIDTH * (i % 5), i / 5 * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, GRASS2_COLOR,
                            null, inventoryPanel, new ImageIcon(getPlantButtonSourceImg(inventory, i)));
            inventorybuttonnew.setVisible(true);
            inventoryButtons.get(i).setVisible(false);
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

    private void removePlant(String srcfile, int i){
        JButton deckButtonnew = CreateButton(TILE_WIDTH * i + 10, 0, TILE_WIDTH, TILE_HEIGHT, BORDER_DECK_COLOR, null,
                deckPanel, new ImageIcon(srcfile));
        deckButtonnew.setVisible(true);
        deckButtons.get(i).setVisible(false);
        deckButtons.set(i, deckButtonnew);
        deckButtons.get(i).revalidate();
    }

    // original version
    private void setPlants(String srcfile, int i, int j, int idplant) throws InvalidDeployException{
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
        if (j != 0 && j != 10) {
                if (deck.getPlayablePlants().get(idplant).getClassPlant() == Lilypad.class || deck.getPlayablePlants()
                        .get(idplant).getClassPlant() == TangleKelp.class) {
                    if(!mainlawn.getLand().get(i).get(j).hasPlant()){
                        if (i == 2 || i == 3) {
                            mapButtons.get(i).get(j).add(plant, gbc);
                            mapButtons.get(i).get(j).setComponentZOrder(plant, 0);
                        } else {
                            throw new InvalidDeployException(
                                    "Plant aquatic tidak dapat ditanam pada tile yang dipilih!");
                        }
                    } else {
                        throw new InvalidDeployException("Plant tidak dapat ditanam! slot sudah diisi");
                    }
                   
                } else if ((deck.getPlayablePlants().get(idplant).getClassPlant() != Lilypad.class
                        || deck.getPlayablePlants().get(idplant).getClassPlant() != TangleKelp.class) && i != 2 && i != 3) {
                            if(!mainlawn.getLand().get(i).get(j).hasPlant()){
                                mapButtons.get(i).get(j).add(plant, gbc);
                                mapButtons.get(i).get(j).setComponentZOrder(plant, 0);
                            } else {
                                throw new InvalidDeployException("Plant tidak dapat ditanam! slot sudah diisi");
                            }
                } else {
                    if(mapButtons.get(i).get(j).getComponents().length == 1){
                        if ((i == 2 || i == 3) && mapButtons.get(i).get(j).getComponents().length > 0) {
                            if(deck.getPlayablePlants().get(idplant).getClassPlant() != TangleKelp.class){
                                mapButtons.get(i).get(j).add(plant, gbc);
                            mapButtons.get(i).get(j).setComponentZOrder(mapButtons.get(i).get(j).getComponent(0), 1);
                            mapButtons.get(i).get(j).setComponentZOrder(plant, 0);
                            } else{
                                throw new InvalidDeployException("Tangklekelp tidak dapat ditanam pada Lilypad!");
                            }
                            
                        } else {
                            throw new InvalidDeployException("Plant tidak dapat ditanam! Belum ada lilypad!");
                        }
                    } else {
                        throw new InvalidDeployException("Plant tidak dapat ditanam! slot sudah diisi");
                    }
                    
                }
            
        } else {
            throw new InvalidDeployException("Plant tidak dapat ditanam di tile yang dipilih!");
        }  
    }

    // overloaded for loading
    private void setPlants(String srcfile, int i, int j) {
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
        mapButtons.get(i).get(j).setComponentZOrder(plant, 0);
    }

    private void applyAttacked(int i, int j) {
        // Get the original image icon
        
        JLabel label = (JLabel) mapButtons.get(i).get(j).getComponent(0);
        ImageIcon originalIcon = (ImageIcon) label.getIcon();
        if (originalIcon == null) {
            // No plant present, do nothing
            return;
        }

        // Convert ImageIcon to Image
        Image originalImage = originalIcon.getImage();

        // Create a BufferedImage for manipulation
        BufferedImage bufferedImage = new BufferedImage(
                originalIcon.getIconWidth(),
                originalIcon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);

        // Draw the original image onto the BufferedImage
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();

        // Apply a red filter to the BufferedImage
        RescaleOp op = new RescaleOp(new float[] { 1.5f, 0, 0, 1 }, new float[] { 0, 0, 0, 0 }, null);
        BufferedImage redImage = op.filter(bufferedImage, null);

        // Create a new ImageIcon from the red-filtered BufferedImage
        ImageIcon redIcon = new ImageIcon(redImage);

        // Set the red-filtered ImageIcon to the label
       
        new Thread(new Runnable(){

            @Override
            public void run() {
                label.setIcon(redIcon);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
           
                    e.printStackTrace();
                }
                label.setIcon(originalIcon);
            }

        }).start();
    }

    private void removePlantsOnMap(int i, int j) throws InvalidDeployException{
        if(mapButtons.get(i).get(j).getComponents().length <= 0){
            throw new InvalidDeployException("Tidak ada tanaman! Tidak dapat menggali!");
        } else{
            mapButtons.get(i).get(j).removeAll();
        }
    }
    
    // original version
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
        if (deck.getPlayablePlants().get(i).getClassPlant().getSimpleName().equals("SnowPea")) {
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

    // overloaded version for loading
    private String getPlantSourceImg(String plantClass){
        String source = "";
        if (plantClass.equals("Sunflower")) {
            source = "src/assets/sunflower.png";
        }
        if (plantClass.equals("CherryBomb")) {
            source = "src/assets/cherrybomb.png";
        }
        if (plantClass.equals("Chomper")) {
            source = "src/assets/chomper.png";
        }
        if (plantClass.equals("Lilypad")) {
            source = "src/assets/lilypad.png";
        }
        if (plantClass.equals("Squash")) {
            source = "src/assets/squash.png";
        }
        if (plantClass.equals("Peashooter")) {
            source = "src/assets/peashooter.png";
        }
        if (plantClass.equals("Repeater")) {
            source = "src/assets/repeater.png";
        }
        if (plantClass.equals("SnowPea")) {
            source = "src/assets/snowpea.png";
        }
        if (plantClass.equals("TangleKelp")) {
            source = "src/assets/tanglekelp.png";
        }
        if (plantClass.equals("Wallnut")) {
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
                c.afterPlant();
            }
        }).start();
        
    }
    public boolean isPlantEnoughSun(int cost){
        if(cost <= Sun.getInstance().getTotalSun()){
            return true;
        } else {
            return false;
        }
    }
    private void updateReadyButton(){
        boolean fullcheck = true;
        int i  = 0;
        while(i < deck.getPlayablePlants().size()){
            if(deck.getPlayablePlants().get(i) == null){
                fullcheck = false;
                break;
            }
            i++;
        }
        if (fullcheck) {
            readyButton.setEnabled(true);
        }
    }

    private void startGame(){

        GUIThread =
            new Thread(()-> {
                count = 200;
                rungame = true;
                winFlag = true;
                try{
                    while(rungame){
                                SwingUtilities.invokeLater(() -> {
                                    for (int i = 1; i < deckButtons.size(); i++) {
                                        if (isPlantEnoughSun(deck.getPlayablePlants().get(i - 1).getCost())) {
                                            if (deck.getPlayablePlants().get(i - 1).getPlantingCooldown() == deck
                                                    .getPlayablePlants().get(i - 1).getPlantingSpeed()) {
                                                deckButtons.get(i).setEnabled(true);
                                            } else if (deck.getPlayablePlants().get(i - 1)
                                                    .getPlantingCooldown() != deck.getPlayablePlants().get(i - 1)
                                                            .getPlantingSpeed()) {
                                                deckButtons.get(i).setEnabled(false);
                                            } 
                                        } else {
                                            deckButtons.get(i).setEnabled(false);
                                        }
                                    }
                                    numSun.setText(Integer.toString(Sun.getInstance().getTotalSun()));
                                    for (Runnable r : ThreadManager.getInstance().getList()) {
                                        if (r instanceof RunnableGameTimer) {
                                            if (((RunnableGameTimer) r).getCurrentGameTime() > 100) {
                                                this.setTitle("Game "
                                                        + String.valueOf(((RunnableGameTimer) r).getCurrentGameTime())
                                                        + " seconds remaining - DAY TIME");
                                                count = ((RunnableGameTimer) r).getCurrentGameTime();
                                            } else if (((RunnableGameTimer) r).getCurrentGameTime() < 100){
                                                this.setTitle("Game "
                                                        + String.valueOf(((RunnableGameTimer) r).getCurrentGameTime())
                                                        + " seconds remaining - NIGHT TIME");
                                            } else if((((RunnableGameTimer) r).getCurrentGameTime() == 0)){
                                                this.setTitle("Gametime run out! ");
                                            }

                                        }
                                    }
                                    // Thread visitorThread = null;
                                    for (int i = 0; i < 6; i++) {
                                        PlantVisitor visitor = new PlantVisitor(mainlawn, i);
                                        Thread visitorThread = new Thread(visitor);
                                        visitorThread.start();
                                    }
                                  
                                    for (int i = 0; i < mapButtons.size(); i++) {
                                        for (int j = 0; j < tempMapRow.size(); j++) {
                                            
                                            if(!mainlawn.getLand().get(i).get(j).hasPlant() || mainlawn.getLand().get(i).get(j).getPlant().isDead()){
                                                mapButtons.get(i).get(j).removeAll();
                                            }
                                            if (mainlawn.getLand().get(i).get(j).hasZombie()) {
                                                setZombies(i, j);
                                    
                                                mapButtons.get(i).get(j).revalidate();
                                                ArrayList<Zombie> currentZombies = new ArrayList<>(
                                                        mainlawn.getLand().get(i).get(j).getZombies());
                                                for (Zombie z : currentZombies) {
                                                    if (z.isSlow() && (z.getcurrentSlow()-1 == 0)){
                                                        System.out.println("Zombie: " + z.getName() + " is slow, CD: " + z.getMoveCooldown());
                                                        z.reduceCurrentSlow(1);
                                                        z.setMoveCooldown(z.getMoveCooldown()/2);
                                                    } else if (z.isSlow()){
                                                        z.reduceCurrentSlow(1);
                                                    }
                                                    // if (z.getcurrentSlow() == 0) z.setMoveCooldown(z.getMoveCooldown()/2);
                                                    z.setMoveCooldown(z.getMoveCooldown() - 1);
                                                    z.setAttackCooldown(z.getAttackCooldown() - 1);
                                                    if(z.HasBeenAttacked()){
                                                        applyAttacked(i, j);
                                                    }
                                                    if (mainlawn.getLand().get(i).get(j).hasPlant() && j>=1) {
                                                        if (z instanceof VaultingInterface) {
                                                            VaultingInterface v = (VaultingInterface) z;
                                                        
                                                            if (!v.getHasVaulted()) {
                                                                System.out.println(z.getName() + " vaulting 2 tile");
                                                                v.vault(mainlawn.getLand().get(i).get(j),
                                                                        mainlawn.getLand().get(i).get(j - 1));
                                                                setZombies(i, j - 1);
                                                            }
                                                            else if (z.getAttackCooldown() == 0) {
                                                                mainlawn.getLand().get(i).get(j).getPlant()
                                                                        .loseHealth(z.getAttackDamage());
                                                                z.attack();
                                                                applyAttacked(i, j);
                                                            }
                                                        } else {
                                                            if (z.getAttackCooldown() == 0) {
                                                                mainlawn.getLand().get(i).get(j).getPlant()
                                                                        .loseHealth(z.getAttackDamage());
                                                                z.attack();
                                                                applyAttacked(i, j);
                                                            }
                                                        }
                                                    } 
                                                    else if (mainlawn.getLand().get(i).get(j - 1).hasPlant()  && j>=1) {
                                                        if (z instanceof VaultingInterface) {
                                                            VaultingInterface v = (VaultingInterface) z;
                                                            // System.out.println(z.getName() + "is Vaulting Over " +
                                                            // mainlawn.getLand().get(i).get(j-1).getPlant().getName());
                                                            if (!v.getHasVaulted()) {
                                                                System.out.println(z.getName() + " vaulting 3 tile");
                                                                v.vault(mainlawn.getLand().get(i).get(j),
                                                                        mainlawn.getLand().get(i).get(j - 2));
                                                                setZombies(i, j - 2);
                                                            }
                                                            // System.out.println("!!! END OF VAULTING TYPE !!!");
                                                            else if (z.getAttackCooldown() == 0) {
                                                                mainlawn.getLand().get(i).get(j - 1).getPlant()
                                                                        .loseHealth(z.getAttackDamage());
                                                                z.attack();
                                                                applyAttacked(i, j - 1);
                                                            }
                                                        } else {
                                                            if (z.getAttackCooldown() == 0) {
                                                                mainlawn.getLand().get(i).get(j - 1).getPlant()
                                                                        .loseHealth(z.getAttackDamage());
                                                                z.attack();
                                                                applyAttacked(i, j - 1);
                                                            }
                                                        }
                                                    } else {
                                                        if (z.getMoveCooldown() == 0  && j>=1) {
                                                            z.move(mainlawn.getLand().get(i).get(j),
                                                                    mainlawn.getLand().get(i).get(j - 1));
                                                            setZombies(i, j - 1);
                                                            removeZombies(i, j);
                                                        }
                                                        // System.out.println("=== After ===");
                                                        if (!mainlawn.getLand().get(i).get(j).hasZombie()) {
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

                                // Checking for endgame
                                for (int i = 0; i < 6; i++) {
                                    if (mainlawn.getLand().get(i).get(0).hasZombie()) {
                                        gamelose = true;
                                        rungame = false;
                                    }
                                }
                                if (count <= 0) {
                                    rungame = (runzombie.getZombieCount() > 0);
                                   
                                }
                                if (ThreadManager.getInstance().getList().size() <= 0){
                                    rungame = false;
                                }
                                count--;
                    }
                    if(gamelose){
                        new AfterGameFrame(false);
                        dispose();
                    } else {
                        new AfterGameFrame(true);
                        dispose();
                    }
                    
                    System.out.println("Game ended normally");
                    ThreadManager.getInstance().stopAllThreads();
                } catch (Exception e) {
                    System.out.println("GUIThread masuk exception");
                }
            });
        GUIThread.start();
    }

    private boolean selectedshovel = false;
    public JLabel CreateLabel() {return new JLabel();}
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 10; i++){ //buat add plant
            try{
                
                if(e.getSource() == inventoryButtons.get(i) && !plantStorage.containsValue(i)){
                inventory.addPlant(inventory.getAllPlants().get(i), deck, getDeckAvalibility());
                setPlants(false, getPlantButtonSourceImg(inventory, i), getDeckAvalibility() + 1);
                
                plantStorage.put(getDeckAvalibility(), i);
                setDeckNotAvailable(getDeckAvalibility());
                deckButtons.get(getDeckAvalibility()).revalidate();
                SetButtonDisabled(i, "src/assets/decktiledisabled.png");
                inventoryButtons.get(i).revalidate();
                updateReadyButton();

                // for (Integer j : deckAvailability) {
                //     System.out.print(j + " ");
                // }
                // System.out.println();
                // for (Integer j : plantStorage.keySet()) {
                //     System.out.print(j + ": " + plantStorage.get(j) + ", ");
                // }
                // System.out.println();
                // for (PlantCard j : deck.getPlayablePlants()) {
                //     System.out.print(j + ", ");
                // }
                // System.out.println("\n");

            }
            } catch(InvalidInventoryException e2){
                e2.getMessage();
            }
            
        }
        if(currentFrame == 1){ // buat remove plant
            for(int i = 0; i < 6; i++){
                  
                    try {
                        if(e.getSource() == deckButtons.get(i+1) && plantStorage.get(i) != -1){
                            setDeckAvailable(i);
                            SetButtonEnabled(plantStorage.get(i), getPlantButtonSourceImg(inventory, plantStorage.get(i)));
                            plantStorage.put(i, -1);
                            removePlant("src/assets/decktile.png", i + 1);
                            inventory.removePlant(deck, i);
                            readyButton.setEnabled(false);

                            // for (Integer j : deckAvailability) {
                            //     System.out.print(j + " ");
                            // }
                            // System.out.println();
                            // for (Integer j : plantStorage.keySet()) {
                            //     System.out.print(j + ": " + plantStorage.get(j) + ", ");
                            // }
                            // System.out.println();
                            // for (PlantCard j : deck.getPlayablePlants()) {
                            //     System.out.print(j + ", ");
                            // }
                            // System.out.println("\n");
                        } 

                    } catch (InvalidInventoryException e1) {
                        
                        System.out.println(e1.getMessage());
                    }
                    
                    
                }
                
            }
        
        if(currentFrame == 2){ //listner untuk ingame
       
            if (e.getSource() == shovelButton) {
                selectedshovel = true;
                
            } else {
                for (int i = 1; i < 7; i++) {
                    if (e.getSource() == deckButtons.get(i)) {
                        idxselectedplant = i;
                    }
            }
                   
            }
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 11; k++) {
                    try {
                        if (selectedshovel) {
                            if(e.getSource() == mapButtons.get(j).get(k)){
                                try {
                                    removePlantsOnMap(j, k);
                                    deck.removePlantFromMap(mainlawn, j, k);
                                } catch (InvalidDeployException e1) {
                                    System.out.println(e1.getMessage());
                                }
                                selectedshovel = false;
                            }
                        } else if (idxselectedplant != -1 && e.getSource() == mapButtons.get(j).get(k)
                                && deckButtons.get(idxselectedplant).isEnabled()) {
                            // System.out.println("map clicked : pada x : " + j + " y : " + k);
                            setPlants(getPlantSourceImg(deck, idxselectedplant - 1), j, k,
                                    idxselectedplant - 1);
                            deck.addPlantToMap(idxselectedplant - 1, mainlawn, j, k);
                            mapButtons.get(j).get(k).revalidate();
                            startPlantCooldown(deck, idxselectedplant);
                            deck.getPlayablePlants().get(idxselectedplant - 1).afterPlant();
                            deckButtons.get(idxselectedplant).setEnabled(false);
                            Sun.getInstance()
                                    .reduceSun(
                                            deck.getPlayablePlants().get(idxselectedplant - 1).getCost());
                        }
                    } catch (InvalidDeployException e1) {
                        System.out.println(e1.getMessage());
                    }

                }
            }
        }
        if (e.getSource() == swap1) {
            try {
                Integer n1 = Integer.parseInt(swap11.getText()) -1;
                Integer n2 = Integer.parseInt(swap12.getText()) -1;

                // System.out.printf("%d %d\n", n1, n2);

                if (deckAvailability.get(n1) == 0 && deckAvailability.get(n2) == 0) {
                    int temp = plantStorage.get(n1);
                    plantStorage.put(n1, plantStorage.get(n2));
                    plantStorage.put(n2, temp);
                    inventory.swapPlant(deck.getPlayablePlants(), n1, n2);
                    setPlants(false, getPlantButtonSourceImg(inventory, plantStorage.get(n1)), n1);
                    setPlants(false, getPlantButtonSourceImg(inventory, plantStorage.get(n2)), n2);
                }

                // for (Integer j : deckAvailability) {
                //     System.out.print(j + " ");
                // }
                // System.out.println();
                // for (Integer j : plantStorage.keySet()) {
                //     System.out.print(j + ": " + plantStorage.get(j) + ", ");
                // }
                // System.out.println();
                // for (PlantCard j : deck.getPlayablePlants()) {
                //     System.out.print(j + ", ");
                // }
                // System.out.println("\n");

            }
            catch (Exception err) {
            }
        }
        if (e.getSource() == swap2) {
            try {
                Integer n1 = Integer.parseInt(swap21.getText()) - 1;
                Integer n2 = Integer.parseInt(swap22.getText()) - 1;

                // inventory.swapPlant(inventory.getAllPlants(), n1, n2);

                if (!plantStorage.containsValue(n1) && !plantStorage.containsValue(n2)) {
                    inventory.swapPlant(inventory.getAllPlants(), n1, n2);
                    setPlants(true, getPlantButtonSourceImg(inventory, n1+1), n1);
                    setPlants(true, getPlantButtonSourceImg(inventory, n2+1), n2);
                }

                // for (Integer j : deckAvailability) {
                //     System.out.print(j + " ");
                // }
                // System.out.println();
                // for (Integer j : plantStorage.keySet()) {
                //     System.out.print(j + ": " + plantStorage.get(j) + ", ");
                // }
                // System.out.println();
                // for (PlantCard j : deck.getPlayablePlants()) {
                //     System.out.print(j + ", ");
                // }
                // System.out.println();
                // for (PlantCard j : inventory.getAllPlants()) {
                //     System.out.print(j + ", ");
                // }
                // System.out.println("\n");
            }
            catch (Exception err) {
            }
        }
        if(e.getSource() == plantsListButton){
            RemoveButtons();
            SwitchToPlantListFrame();
        }
        if (e.getSource() == zombieListButton) {
            RemoveButtons();
            SwitchToZombieListFrame();
        }
        if(e.getSource() == nextArrowButton && currentFrame == 3){
            RemoveButtons();
            SwitchToPlantListFrame2();
        }
        if (e.getSource() == previousArrowButton && currentFrame == 4) {
            RemoveButtons();
            SwitchToPlantListFrame();
        }
        if (e.getSource() == nextArrowButton && currentFrame == 5) {
            RemoveButtons();
            SwitchToZombieListFrame2();
        }
        if (e.getSource() == previousArrowButton && currentFrame == 6) {
            RemoveButtons();
            SwitchToZombieListFrame();
        }
        if (e.getSource() == backMenuButton) {
            RemoveButtons();
            SwitchToMenuFrame();
        }
        if(e.getSource() == startButton) {
            RemoveButtons();
            SwitchToDeckFrame();
            readyButton.setEnabled(false);
        } 
        if (e.getSource() == helpButton) {
            RemoveButtons();
            SwitchToHelpFrame();
        }
        if(e.getSource() == loadButton) {
            if (Load.load("testSave.ser")) {
                deck = Load.LoadHolder.gameDeck;
                Sun.getInstance().initializeSun(Load.LoadHolder.gameSun.getTotalSun());
                mainlawn = Load.LoadHolder.lawn;
                runzombie = Load.LoadHolder.zomSpawn;
                ThreadManager.getInstance().addThread(runzombie);
                ThreadManager.getInstance().addThread(Load.LoadHolder.genSun);
                ThreadManager.getInstance().addThread(Load.LoadHolder.gameTimer);
                System.out.println("Threads in manager (load): " + ThreadManager.getInstance().getList().size());
                System.out.println("Game timer in manager (load): " + ThreadManager.getInstance().getRunnableGameTimer().getCurrentGameTime());
                ThreadManager.getInstance().startAllThreads();

                for (int i = 0; i < deck.getPlayablePlants().size(); i++){
                    setPlants(false, getPlantButtonSourceImg(deck, i), getDeckAvalibility() + 1);
                    plantStorage.put(getDeckAvalibility(), i);
                    setDeckNotAvailable(getDeckAvalibility());
                    deckButtons.get(getDeckAvalibility()).revalidate();
                    if (deck.getPlayablePlants().get(i).getPlantingCooldown() != deck.getPlayablePlants().get(i).getPlantingSpeed()){
                        SetButtonDisabled(i, "src/assets/decktiledisabled.png");
                        startPlantCooldown(deck, i+1);
                    }
                }

                for (int i = 0; i < mainlawn.getLand().size(); i++){
                    for (int j = 0; j < mainlawn.getLand().get(0).size(); j++){
                        if (mainlawn.getLand().get(i).get(j).hasPlant()){
                            Plant p = mainlawn.getLand().get(i).get(j).getPlant();
                            String pc = p.getClass().getSimpleName();
                            if (pc.equals("Lilypad") && ((Lilypad)p).hasPlant()){
                                setPlants(getPlantSourceImg(pc), i, j);
                                setPlants(getPlantSourceImg(((Lilypad)p).getPlant().getClass().getSimpleName()), i, j);
                            } else {
                                setPlants(getPlantSourceImg(pc), i, j);
                            }
                        }
                    }
                }

                RemoveButtons();
                SwitchToGameFrame();

                startGame();
            } else {
                // System.out.println("No Saved File Found!");
                System.out.println("Please start a new game");
            }

        }
        if(e.getSource() == menuButton && currentFrame == 2) {
            Save.SaveHolder.lawn = mainlawn;
            Save.SaveHolder.threads = ThreadManager.getInstance().getList();
            Save.SaveHolder.gameDeck = deck;
            Save.SaveHolder.gameSun = Sun.getInstance();

            for (int i = 0; i < deck.getPlayablePlants().size(); i++){
                System.out.println("Plant at " + i + "'s cooldown is: " + deck.getPlayablePlants().get(i).getPlantingCooldown());
            }

            // GUIThread.interrupt();
            new ExitFrame();
            dispose();
        } else if(e.getSource() == menuButton){
            if (currentFrame == 1) {
                RemoveButtons();
                SwitchToMenuFrame();
            }
            else {
                dispose();
                System.exit(0);    
            }

        }
        if (e.getSource() == readyButton) {
            if(deck.getPlayablePlants().size() == deck.getMaxPlants()){
                RemoveButtons();
                SwitchToGameFrame();
                
                Sun.getInstance().initializeSun();
                for (int i = 1; i < 7; i++) {
                    deckButtons.get(i).setEnabled(false);
                    //startPlantCooldown(deck, i);
                }

                // update the every text here
                mainlawn = new Lawn();
                runzombie = new RunnableZombieSpawn(200, mainlawn);
                ThreadManager.getInstance().addThread(new RunnableGenerateSun(100));
                ThreadManager.getInstance().addThread(new RunnableGameTimer(200));
                ThreadManager.getInstance().addThread(runzombie);

                System.out.println("Threads in manager (start): " + ThreadManager.getInstance().getList().size());
                System.out.println("Game timer in manager (start): " + ThreadManager.getInstance().getRunnableGameTimer().getCurrentGameTime());
                ThreadManager.getInstance().startAllThreads();

                startGame();
            } else {
                try {
                    throw new InvalidInventoryException("Deck Belum Penuh");
                } catch (InvalidInventoryException e1) {
                    e1.getMessage();
                }
            }
          
        }    
    }
        

}