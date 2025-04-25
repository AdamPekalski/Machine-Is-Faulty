package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import data.MachineData;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class AddDataPanel extends JPanel {
    private JCheckBox powerSurgeCheck;
    private JCheckBox coolingFailureCheck;
    private JCheckBox sensorErrorCheck;
    private JCheckBox manualOverrideCheck;
    private JCheckBox isFaultyCheck;
    private JButton addButton;
    private List<MachineData> dataset;
    private String csvFilePath = "data/rule_based_predictive_dataset.csv";

    public AddDataPanel(List<MachineData> dataset) {
        this.dataset = dataset;

        setLayout(new GridLayout(6, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create checkboxes for features and label
        powerSurgeCheck = createCheckBox("Power Surge Detected");
        coolingFailureCheck = createCheckBox("Cooling System Failure");
        sensorErrorCheck = createCheckBox("Sensor Error");
        manualOverrideCheck = createCheckBox("Manual Override Active");
        isFaultyCheck = createCheckBox("Machine is Faulty");

        // Create "Add Data" button
        addButton = new JButton("Add Data");
        addButton.setBackground(new Color(40, 167, 69));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addData();
            }
        });

        // Add components to panel
        add(powerSurgeCheck);
        add(coolingFailureCheck);
        add(sensorErrorCheck);
        add(manualOverrideCheck);
        add(isFaultyCheck);
        add(addButton);
    }

    private JCheckBox createCheckBox(String text) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        return checkBox;
    }

    private void addData() {
        MachineData newData = new MachineData(
            powerSurgeCheck.isSelected(),
            coolingFailureCheck.isSelected(),
            sensorErrorCheck.isSelected(),
            manualOverrideCheck.isSelected(),
            isFaultyCheck.isSelected()
        );

        dataset.add(newData);
        saveDataToCSV(newData);

        JOptionPane.showMessageDialog(this, "New data added successfully! Please retrain the classifier.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveDataToCSV(MachineData data) {
        try (FileWriter writer = new FileWriter(csvFilePath, true)) {
            writer.write(String.format(
                "%s,%s,%s,%s,%s%n",
                data.isPowerSurge() ? "yes" : "no",
                data.isCoolingFailure() ? "yes" : "no",
                data.isSensorError() ? "yes" : "no",
                data.isManualOverride() ? "yes" : "no",
                data.isFaulty() ? "yes" : "no"
            ));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving data to CSV: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}