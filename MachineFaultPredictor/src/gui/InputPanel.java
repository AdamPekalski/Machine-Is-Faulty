package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class represents the input panel of the GUI, where users can select various machine conditions
public class InputPanel extends JPanel {
    private JCheckBox powerSurgeCheck;
    private JCheckBox coolingFailureCheck;
    private JCheckBox sensorErrorCheck;
    private JCheckBox manualOverrideCheck;
    private JButton predictButton;
    private MainFrame mainFrame;

    // Constructor initializes the input panel with checkboxes and a button
    public InputPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(5, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create checkboxes
        powerSurgeCheck = new JCheckBox("Power Surge Detected");
        coolingFailureCheck = new JCheckBox("Cooling System Failure");
        sensorErrorCheck = new JCheckBox("Sensor Error");
        manualOverrideCheck = new JCheckBox("Manual Override Active");
        
        // Create predict button
        predictButton = new JButton("Predict Machine Status");
        predictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makePrediction();
            }
        });
        
        // Add components to panel
        add(powerSurgeCheck);
        add(coolingFailureCheck);
        add(sensorErrorCheck);
        add(manualOverrideCheck);
        add(predictButton);
    }
    
    // Method to handle prediction logic when the button is clicked
    private void makePrediction() {
        mainFrame.makePrediction(
            powerSurgeCheck.isSelected(),
            coolingFailureCheck.isSelected(),
            sensorErrorCheck.isSelected(),
            manualOverrideCheck.isSelected()
        );
    }
}
