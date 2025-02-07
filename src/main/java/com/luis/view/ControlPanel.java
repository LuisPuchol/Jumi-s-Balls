package com.luis.view;

import javax.swing.*;

public class ControlPanel {
    private JPanel buttonPanel;
    private JButton buttonPauseOrResume;
    private JButton buttonStop;

    public ControlPanel() {
        this.buttonPauseOrResume = new JButton("Pause/Resume");
        this.buttonStop = new JButton("Stop");

        buttonPanel = new JPanel();

        buttonPanel.add(buttonPauseOrResume);
        buttonPanel.add(buttonStop);
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JButton getButtonPauseOrResume() {
        return buttonPauseOrResume;
    }

    public void setButtonPauseOrResume(JButton buttonPauseOrResume) {
        this.buttonPauseOrResume = buttonPauseOrResume;
    }

    public JButton getButtonStop() {
        return buttonStop;
    }

    public void setButtonStop(JButton buttonStop) {
        this.buttonStop = buttonStop;
    }
}
