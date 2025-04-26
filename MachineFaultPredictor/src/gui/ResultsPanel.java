package gui;

import javax.swing.*;
import java.awt.*;

public class ResultsPanel extends JPanel {
    private JLabel trainingSetSizeLabel;
    private JLabel testingSetSizeLabel;
    private JLabel testingSetAccuracyLabel;
    private JLabel overallAccuracyLabel;

    public ResultsPanel(int trainingSetSize, int testingSetSize, double testingSetAccuracy, double overallAccuracy) {
        setLayout(new GridLayout(4, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create labels
        trainingSetSizeLabel = new JLabel("Training Set Size: " + trainingSetSize, SwingConstants.CENTER);
        trainingSetSizeLabel.setFont(new Font("Arial", Font.BOLD, 14));

        testingSetSizeLabel = new JLabel("Testing Set Size: " + testingSetSize, SwingConstants.CENTER);
        testingSetSizeLabel.setFont(new Font("Arial", Font.BOLD, 14));

        testingSetAccuracyLabel = new JLabel(String.format("Testing Set Accuracy: %.2f%%", testingSetAccuracy * 100), SwingConstants.CENTER);
        testingSetAccuracyLabel.setFont(new Font("Arial", Font.BOLD, 14));

        overallAccuracyLabel = new JLabel(String.format("Overall Dataset Accuracy: %.2f%%", overallAccuracy * 100), SwingConstants.CENTER);
        overallAccuracyLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Add labels to panel
        add(trainingSetSizeLabel);
        add(testingSetSizeLabel);
        add(testingSetAccuracyLabel);
        add(overallAccuracyLabel);
    }
}