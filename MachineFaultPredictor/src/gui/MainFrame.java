package gui;

import javax.swing.*;
import java.awt.*;
import ml.ModelTrainer;
import data.MachineData;

// This class represents the main frame of the GUI application for predicting machine faults.
public class MainFrame extends JFrame {
    private InputPanel inputPanel;
    private ResultPanel resultPanel;
    private ModelTrainer trainer;

    // Constructor initializes the main frame with input and result panels
    public MainFrame(ModelTrainer trainer) {
        this.trainer = trainer;
        setTitle("Machine Fault Predictor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create panels
        inputPanel = new InputPanel(this);
        resultPanel = new ResultPanel();
        
        // Add panels to frame
        add(inputPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
        
        // Set window properties
        setMinimumSize(new Dimension(400, 300));
        setPreferredSize(new Dimension(500, 400));
        pack();
        setLocationRelativeTo(null);
    }
    
    // Method to make a prediction based on user input from the input panel
    public void makePrediction(boolean powerSurge, boolean coolingFailure, 
                             boolean sensorError, boolean manualOverride) {
        MachineData data = new MachineData(powerSurge, coolingFailure, 
                                         sensorError, manualOverride, false);
        boolean isFaulty = trainer.predict(data);
        resultPanel.updateResult(isFaulty);
    }
}
