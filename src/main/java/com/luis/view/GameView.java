package com.luis.view;

import com.luis.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView implements Runnable, ActionListener {
    private GameController gameController;
    private ControlPanel controlPanel;
    private Viewer viewer;
    private Thread thread;

    public GameView(GameController gameController) {
        System.out.println("GameView creado");
        this.gameController = gameController;

        createFrame();
        setUpListeners();

        thread = new Thread(this);
        thread.start();
    }

    public void createFrame() {
        controlPanel = new ControlPanel();
        viewer = new Viewer();

        JFrame frame = new JFrame("Jumi's Balls");
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        gbc.gridx = 0;  // columna 0
        gbc.gridy = 0;  // fila 0
        gbc.gridwidth = 1;  // ocupan 1 columnas
        gbc.gridheight = 1;  // ocupan 1 fila
        gbc.fill = GridBagConstraints.BOTH;  // se expandirse
        gbc.weightx = 0;  // No hay espacio adicional horizontal
        gbc.weighty = 0;  // No hay espacio adicional vertical
        frame.add(viewer.getCanvas(), gbc);

        gbc.gridy = 1;  // Coloca los botones en la fila 1
        gbc.fill = GridBagConstraints.NONE;  // no se expandirse
        frame.add(controlPanel.getButtonPanel(), gbc);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public void setUpListeners() {
        controlPanel.getButtonPauseOrResume().addActionListener(this);
        controlPanel.getButtonStop().addActionListener(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(16);
                viewer.render(gameController.getBalls());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == controlPanel.getButtonPauseOrResume()) {
            gameController.pauseOrResume();
        } else if (e.getSource() == controlPanel.getButtonStop()) {
            gameController.stop();
        }
    }
}
