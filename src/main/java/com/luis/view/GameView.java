package com.luis.view;

import com.luis.controller.GameController;
import com.luis.model.BallDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameView implements Runnable, ActionListener {
    private GameController gameController;
    private ControlPanel controlPanel;
    private Viewer viewer;
    private Thread thread;

    private volatile boolean running;

    // Constantes para el bucle de juego
    private static final int TARGET_FPS = 60;
    private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; // en nanosegundos

    public GameView(GameController gameController) {
        System.out.println("GameView creado");
        this.gameController = gameController;
        this.running = true;

        createFrame();
        setUpListeners();


        SwingUtilities.invokeLater(() -> {
            thread = new Thread(this);
            thread.start();
        });
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
        gbc.fill = GridBagConstraints.BOTH;  // se expande
        gbc.weightx = 0;  // No hay espacio adicional horizontal
        gbc.weighty = 0;  // No hay espacio adicional vertical
        frame.add(viewer.getCanvas(), gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;  // no se expande
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


        while (running) {
            try {
                ArrayList<BallDTO> balls = new ArrayList<>();
                if (gameController != null) {
                    balls = gameController.getBalls();
                }

                // Actualizar y renderizar
                viewer.render(balls);

                Thread.sleep(16);
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
