package gui;


import javax.swing.*;


import organism.plant.Sunflower;
import thread.RunnableGameTimer;
import thread.RunnableGenerateSun;
import thread.RunnableGenerateSun.*;
import thread.ThreadManager;
import sun.Sun;

import grid.*;

import organism.zombie.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;


public class MenuFrame extends JFrame implements ActionListener {
    static final int FRAME_WIDTH = 300;
    static final int FRAME_HEIGHT = 150;
    static final Color BACKGROUND_COLOR = new Color(0xC4BFB6);
    public MenuFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        getContentPane().setBackground(BACKGROUND_COLOR);


        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
