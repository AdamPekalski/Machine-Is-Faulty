package gui;

import javax.swing.*;
import java.awt.*;

// This class represents the result panel of the GUI, where the prediction results are displayed.
public class ResultPanel extends JPanel {
    private JLabel resultLabel;
    private JLabel statusLabel;
    private JLabel recommendationLabel;

    // Constructor initializes the result panel with labels for displaying results
    public ResultPanel() {
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create labels
        resultLabel = new JLabel("Machine Status Prediction:", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        recommendationLabel = new JLabel("", SwingConstants.CENTER);
        recommendationLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Add labels to panel
        add(resultLabel);
        add(statusLabel);
        add(recommendationLabel);
    }
    
    // Method to update the result based on the prediction
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
}
