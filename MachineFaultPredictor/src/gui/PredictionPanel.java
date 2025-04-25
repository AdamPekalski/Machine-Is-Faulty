package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PredictionPanel extends JPanel {
    private JCheckBox powerSurgeCheck;
    private JCheckBox coolingFailureCheck;
    private JCheckBox sensorErrorCheck;
    private JCheckBox manualOverrideCheck;
    private JButton predictButton;
    private JLabel resultLabel;
    private JLabel statusLabel;
    private JLabel recommendationLabel;
    private JLabel accuracyLabel; // New label for accuracy
    private MainFrame mainFrame;

    public PredictionPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        powerSurgeCheck = new JCheckBox("Power Surge Detected");
        coolingFailureCheck = new JCheckBox("Cooling System Failure");
        sensorErrorCheck = new JCheckBox("Sensor Error");
        manualOverrideCheck = new JCheckBox("Manual Override Active");
        predictButton = new JButton("Predict Machine Status");
        predictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makePrediction();
            }
        });

        inputPanel.add(powerSurgeCheck);
        inputPanel.add(coolingFailureCheck);
        inputPanel.add(sensorErrorCheck);
        inputPanel.add(manualOverrideCheck);
        inputPanel.add(predictButton);

        // Create result panel
        JPanel resultPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        resultLabel = new JLabel("Prediction Result:", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        recommendationLabel = new JLabel("", SwingConstants.CENTER);
        recommendationLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        accuracyLabel = new JLabel("Model Accuracy: N/A", SwingConstants.CENTER); // Initialize accuracy label
        accuracyLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        resultPanel.add(resultLabel);
        resultPanel.add(statusLabel);
        resultPanel.add(recommendationLabel);
        resultPanel.add(accuracyLabel); // Add accuracy label

        // Add input and result panels to the main panel
        add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
    }

    private void makePrediction() {
        boolean powerSurge = powerSurgeCheck.isSelected();
        boolean coolingFailure = coolingFailureCheck.isSelected();
        boolean sensorError = sensorErrorCheck.isSelected();
        boolean manualOverride = manualOverrideCheck.isSelected();

        mainFrame.makePrediction(powerSurge, coolingFailure, sensorError, manualOverride);
    }

    public void updateResult(boolean isFaulty) {
        if (isFaulty) {
            statusLabel.setText("MACHINE IS FAULTY");
            statusLabel.setForeground(Color.RED);
            recommendationLabel.setText("Recommendation: Immediate maintenance required");
        } else {
            statusLabel.setText("MACHINE IS OPERATIONAL");
            statusLabel.setForeground(Color.GREEN);
            recommendationLabel.setText("Recommendation: Continue normal operation");
        }
    }

    public void updateAccuracy(double accuracy) {
        accuracyLabel.setText(String.format("Model Accuracy: %.2f%%", accuracy * 100));
    }
}