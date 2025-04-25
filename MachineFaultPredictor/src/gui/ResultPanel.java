package gui;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    private JLabel resultLabel;
    private JLabel statusLabel;
    private JLabel recommendationLabel;
    private JLabel accuracyLabel; // New label for accuracy

    public ResultPanel() {
        setLayout(new GridLayout(4, 1)); // Adjust grid layout to fit the new label
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create labels
        resultLabel = new JLabel("Machine Status Prediction:", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        recommendationLabel = new JLabel("", SwingConstants.CENTER);
        recommendationLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        accuracyLabel = new JLabel("Model Accuracy: N/A", SwingConstants.CENTER); // Initialize accuracy label
        accuracyLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Add labels to panel
        add(resultLabel);
        add(statusLabel);
        add(recommendationLabel);
        add(accuracyLabel); // Add accuracy label
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
