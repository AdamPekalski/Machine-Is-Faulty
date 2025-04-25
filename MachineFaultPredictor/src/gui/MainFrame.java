package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ml.ModelTrainer;
import ml.DataPreprocessor;
import data.MachineData;
import java.util.List;

public class MainFrame extends JFrame {
    private InputPanel inputPanel;
    private ResultPanel resultPanel;
    private JButton trainButton;
    private ModelTrainer trainer;
    private DataPreprocessor preprocessor;

    public MainFrame(ModelTrainer trainer) {
        this.trainer = trainer;
        this.preprocessor = new DataPreprocessor();
        setTitle("Machine Fault Predictor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create panels
        inputPanel = new InputPanel(this);
        resultPanel = new ResultPanel();

        // Create "Train Classifier" button
        trainButton = new JButton("Train Classifier");
        trainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trainClassifier();
            }
        });

        // Add components to frame
        add(inputPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
        add(trainButton, BorderLayout.NORTH);

        // Set window properties
        setMinimumSize(new Dimension(400, 300));
        setPreferredSize(new Dimension(500, 400));
        pack();
        setLocationRelativeTo(null);
    }

    private void trainClassifier() {
        try {
            // Load dataset
            String dataPath = "data/rule_based_predictive_dataset.csv";
            List<MachineData> allData = preprocessor.loadData(dataPath);

            if (allData.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Dataset is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Split data into training and test sets
            List<MachineData>[] splitData = ModelTrainer.splitData(allData, 0.8);
            List<MachineData> trainingData = splitData[0];
            List<MachineData> testData = splitData[1];

            // Train the model
            trainer.train(trainingData);

            // Evaluate the model
            double accuracy = trainer.evaluate(testData);

            // Update accuracy in the GUI
            resultPanel.updateAccuracy(accuracy);

            JOptionPane.showMessageDialog(this, "Classifier trained successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error training classifier: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void makePrediction(boolean powerSurge, boolean coolingFailure, boolean sensorError, boolean manualOverride) {
        MachineData data = new MachineData(powerSurge, coolingFailure, sensorError, manualOverride, false);
        boolean isFaulty = trainer.predict(data);
        resultPanel.updateResult(isFaulty);
    }
}
