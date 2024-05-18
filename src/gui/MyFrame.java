package gui;
import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Queue;
import java.awt.*;


public class MyFrame extends JFrame implements ActionListener {
    private int currentFrame;
    private int count;
    private List<Integer> deckAvailability = new ArrayList<Integer>();
    private Stack<JButton> disabledPlant = new Stack<JButton>();
    private Stack<Integer> plantOnDeck = new Stack<Integer>();
    Thread GUIThread;
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
    JButton helpButton;
    JButton plantsListButton;
    JButton zombieListButton;
    JButton exitButton;
    JButton menuButton;
    JButton readyButton;
    JButton deckButton;
    JLabel numSun;

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
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }
        currentFrame = 1;
        menuButton.setVisible(true);
        deckPanel.setVisible(true);
        inventoryPanel.setVisible(true);
        readyButton.setVisible(true);

    }

    public void SwitchToGameFrame() {
        try {
            myImage = ImageIO.read(new File("src/assets/gameframe.png"));
            imagepan.setImage(myImage);
            setContentPane(imagepan);
        } catch (IOException e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }
        currentFrame = 2;
        menuButton.setVisible(true);
        mapPanel.setVisible(true);
        deckPanel.setVisible(true);
        
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
        }
        else if (currentFrame == 2) {
            menuButton.setVisible(false);
            mapPanel.setVisible(false);
            deckPanel.setVisible(false);
        }

    }

    public void SetPanels() {
        menuPanel = CreatePanel(160, 210, LARGE_WIDTH, LARGE_HEIGHT*4 + 30);
        deckPanel = CreatePanel(10, 10, TILE_WIDTH * 8 + 10, TILE_HEIGHT);
        inventoryPanel = CreatePanel(70, 80, TILE_WIDTH * 5, TILE_HEIGHT * 5);
        mapPanel = CreatePanel(75, 90, TILE_WIDTH * 11, TILE_HEIGHT * 6);
    }

    public void SetButtons() {

        startButton = CreateButton(0, 0, LARGE_WIDTH, LARGE_HEIGHT, BUTTON_COLOR, "START", menuPanel, new ImageIcon("src/assets/startbutton.png"));
        helpButton = CreateButton(0, LARGE_HEIGHT + 10, LARGE_WIDTH, LARGE_HEIGHT, BUTTON_COLOR, "HELP", menuPanel, 
                new ImageIcon("src/assets/loadbutton.png"));
        plantsListButton = CreateButton(0, (LARGE_HEIGHT + 10) * 2, LARGE_WIDTH, LARGE_HEIGHT, BUTTON_COLOR, "PLANTS LIST", menuPanel, 
                new ImageIcon("src/assets/plantlistbutton.png"));
        zombieListButton = CreateButton(0, (LARGE_HEIGHT + 10) * 3, LARGE_WIDTH, LARGE_HEIGHT, BUTTON_COLOR, "ZOMBIES LIST", menuPanel,
                new ImageIcon("src/assets/zombielistbutton.png"));

        exitButton = CreateButton(10, 10, SMALL_WIDTH, SMALL_HEIGHT, BUTTON_COLOR, "EXIT", menuPanel, 
                new ImageIcon("src/assets/exitbutton.png"));
       
        menuButton = CreateButton(530, 10, SMALL_WIDTH, SMALL_HEIGHT, BUTTON_COLOR, null, 
                new ImageIcon("src/assets/exitbutton.png"));

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
                deckButtons.add(deckButton);
            }
        }

        for (int i = 0; i < 10; i++) {
            inventoryButtons.add(CreateButton(TILE_WIDTH * (i % 5), i / 5 * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, GRASS2_COLOR, null, inventoryPanel, new ImageIcon("src/assets/decktile.png")));
            setPlants(true, "src/assets/sunflower.png", i);
            
        }

        readyButton = CreateButton(60, 390, LARGE_WIDTH + 10, LARGE_HEIGHT, BUTTON_COLOR, null, new ImageIcon("src/assets/readybutton.png"));

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
            }
            mapButtons.add(tempMapRow);
        }


    }
    public void setZombies(int i, int j){
        JLabel zombie = new JLabel();
        zombie.setBounds(TILE_WIDTH, TILE_HEIGHT, 18, 30);
        zombie.setHorizontalTextPosition(JLabel.CENTER);
        zombie.setVerticalTextPosition(JLabel.CENTER);
        zombie.setVisible(true);
        zombie.setOpaque(false);
        zombie.setIcon(new ImageIcon(new ImageIcon("src/assets/normalzombie.png").getImage()
                .getScaledInstance(zombie.getWidth(), zombie.getHeight(), Image.SCALE_DEFAULT)));
        mapButtons.get(i).get(j).add(zombie);
    }

    public void setPlants(boolean onInventory, String srcfile, int i){
        JLabel plant = new JLabel();
        ImageIcon img = new ImageIcon(srcfile);
        plant.setBounds(0,0, TILE_WIDTH, TILE_HEIGHT);
        plant.setVisible(true);
        plant.setIcon(new ImageIcon(img.getImage()
                .getScaledInstance(19, 30, Image.SCALE_DEFAULT)));
        if(onInventory){
            inventoryButtons.get(i).add(plant);
        } else {
            deckButtons.get(i).add(plant);
        }
        
        
    }

    private String getPlantSourceImg(Deck deck){
        String source = "";
        for(Plant c : deck.getPlayablePlants()){
            if(c instanceof Sunflower){
                source ="src/assets/sunflower.png";
            }
            if (c instanceof CherryBomb) {
                source = "src/assets/cherrybomb.png";
            }
            if (c instanceof Chomper) {
                source = "src/assets/chomper.png";
            }
            if (c instanceof Lilypad) {
                source = "src/assets/lilypad.png";
            }
            if (c instanceof Peashooter) {
                source = "src/assets/peashooter.png";
            }
            if (c instanceof Repeater) {
                source = "src/assets/repeater.png";
            }
            if (c instanceof SnowPea) {
                source = "src/assets/snowpea.png";
            }
            if (c instanceof TangleKelp) {
                source = "src/assets/tanglekelp.png";
            }
            if (c instanceof Wallnut) {
                source = "src/assets/wallnut.png";
            }
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
    private void setDeckNotAvailable(){
        int i = 0;
        while(i < deckAvailability.size()){
            if(deckAvailability.get(i) == 0){
                i++;
            } else {
                deckAvailability.set(i, 0);
                break;
            }
           
        }
    }
    
    private void setDeckAvailable() {
        int i = deckAvailability.size()-1;
        while (i >= 0) {
            if (deckAvailability.get(i) == 1) {
                i--;
            } else {
                deckAvailability.set(i, 1);
                break;
            }

        }
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

    public void SetButtonDisabled(JButton button, JPanel panel, ImageIcon i) {
        JButton newButton = CreateButton(button.getX(), button.getY(), button.getWidth(), button.getHeight(), null, null,panel, i);
        button.setVisible(false);
        newButton.setVisible(true);
        disabledPlant.push(newButton);
    }

    public void SetButtonEnabled(JButton button1, int Jbuttonindex) {
        button1.setVisible(false);
        inventoryButtons.get(Jbuttonindex).setVisible(true);
    }

    public JLabel CreateLabel() {return new JLabel();}
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 10; i++){ //buat add plant
            try{
                if(e.getSource() == inventoryButtons.get(i)){
                setPlants(false, "src/assets/sunflower.png", getDeckAvalibility() + 1);
                plantOnDeck.push(i);
                deckButtons.get(getDeckAvalibility() + 1).revalidate();
                SetButtonDisabled(inventoryButtons.get(i), inventoryPanel, new ImageIcon("src/assets/decktiledisabled.png"));
                setDeckNotAvailable();
                inventoryButtons.get(i).revalidate();
                System.out.println(getDeckAvalibility());
                System.out.println("Clicked on: " + " " + i);
            }
            } catch(Exception e2){
                System.out.println("SUDAH PENUH BOS");
            }
            
        }
        if(currentFrame == 1){ // buat remove plant
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

            }
        }
        if(e.getSource() != null) {
            // jika button apapun dipress
        }
        if (e.getSource() == menuButton && currentFrame == 2) {
        
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    ThreadManager.stopAllThreads();
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
                // TODO Auto-generated catch block
                e4.printStackTrace();
            }
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
            Lawn mainlawn = new Lawn();
            RunnableZombieSpawn runzombie = new RunnableZombieSpawn(200, mainlawn);
            ThreadManager.addThread(new RunnableGenerateSun(100));
            ThreadManager.addThread(new RunnableGameTimer(200));
            ThreadManager.addThread(runzombie);
            ThreadManager.startAllThreads();
            
            new Sun();
            GUIThread =
            new Thread(() -> {
            System.out.println("GUI Thread Started");
            Boolean rungame = true;
            count = 200;
            while (rungame) {
                System.out.println("while loop entered");
                System.out.println("---------------");
                // BERARTI MAIN GAME LOOP DI SINI YA? ~Dama yes ini thread buat swing (GUI thread only)

                // update the every text here
                SwingUtilities.invokeLater(() -> {
                       numSun.setText(Integer.toString(Sun.getTotalSun()));
                       for(Runnable r : ThreadManager.getList()){
                            if(r instanceof RunnableGameTimer){
                                if(((RunnableGameTimer) r).getCurrentGameTime() != 0){
                                            this.setTitle("Game "
                                                    + String.valueOf(((RunnableGameTimer) r).getCurrentGameTime())
                                                    + " seconds remaining");
                                } else {
                                        this.setTitle("Game paused");
                                }
                                
                            }
                                 
                       }
                        for (int i = 0; i < mapButtons.size(); i++) {
                            for (int j = 0; j < tempMapRow.size(); j++) {
                                    if(mainlawn.getLand().get(i).get(j).hasZombie()){
                                        setZombies(i, j);
                                        //taro code moveZombies disini -Vald
                                        
                                        ArrayList<Zombie> currentZombies = new ArrayList<>(mainlawn.getLand().get(i).get(j).getZombies());
                                        for (Zombie z : currentZombies){
                                            // System.out.println("Zombie " + z + " has" + z.getMoveCooldown() + " secs moveCD.");
                                            z.setMoveCooldown(z.getMoveCooldown() - 1);
                                            if(z.getMoveCooldown() == 0){
                                                // System.out.println();
                                                // System.out.printf("Zombie " + z + " is moving from (" + i + "," + j +"). \n");
                                                // System.out.printf("Zombie at old tile (%d, %d) exist? %s \n", i, j, mainlawn.getLand().get(i).get(j).getZombies().contains(z));
                                                // System.out.printf("Zombie at new tile (%d, %d) exist? %s \n", i, j-1, mainlawn.getLand().get(i).get(j-1).getZombies().contains(z));
                                                z.move(mainlawn.getLand().get(i).get(j), mainlawn.getLand().get(i).get(j-1));
                                                // System.out.println("=== After ===");
                                                removeZombies(i, j);
                                                setZombies(i, j-1);
                                                System.out.println("zombie set at: (" + i + "," + (j-1) +")");
                                                System.out.printf("Zombie at old tile (%d, %d) exist? %s \n", i, j, mainlawn.getLand().get(i).get(j).getZombies().contains(z));
                                                System.out.printf("Zombie at new tile (%d, %d) exist? %s \n", i, j-1, mainlawn.getLand().get(i).get(j-1).getZombies().contains(z));
                                                System.out.println();
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
                    if (mainlawn.getLand().get(i).get(0).hasZombie()){
                        rungame = false;
                    }
                }
                System.out.println("Checking endgame");
                System.out.println("Rungame before and: " + rungame);
                System.out.println("ZombieCount: " + runzombie.getZombieCount());
                if (count <= 0) {
                    rungame = rungame && (count <= 0 && runzombie.getZombieCount() > 0);
                }
                count--;

                System.out.println("Rungame after and: " + rungame);
                System.out.println("=====================");
            }});
            GUIThread.start();

            
        }
        

    }

}
