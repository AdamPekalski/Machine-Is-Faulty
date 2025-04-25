package gui;

import javax.swing.*;
import java.awt.*;
import ml.ModelTrainer;
import ml.DataPreprocessor;
import data.MachineData;
import java.util.List;

public class MainFrame extends JFrame {
    private PredictionPanel predictionPanel;
    private AddDataPanel addDataPanel;
    private JButton trainButton;
    private ModelTrainer trainer;
    private DataPreprocessor preprocessor;
    private List<MachineData> dataset;

    public MainFrame(ModelTrainer trainer) {
        this.trainer = trainer;
        this.preprocessor = new DataPreprocessor();
        setTitle("Machine Fault Predictor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load the dataset
        String dataPath = "data/rule_based_predictive_dataset.csv";
        this.dataset = preprocessor.loadData(dataPath);

        // Create panels
        predictionPanel = new PredictionPanel(this);
        addDataPanel = new AddDataPanel(dataset);

        // Create "Train Classifier" button
        trainButton = new JButton("Train Classifier");
        trainButton.addActionListener(e -> trainClassifier());

        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Predict & Results", predictionPanel);
        tabbedPane.addTab("Add Data", addDataPanel);

        // Add components to frame
        add(tabbedPane, BorderLayout.CENTER);
        add(trainButton, BorderLayout.SOUTH);

        // Set window properties
        setMinimumSize(new Dimension(700, 500));
        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
    }

    private void trainClassifier() {
        try {
            if (dataset.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Dataset is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Split data into training and test sets
            List<MachineData>[] splitData = ModelTrainer.splitData(dataset, 0.8);
            List<MachineData> trainingData = splitData[0];
            List<MachineData> testData = splitData[1];

            // Train the model
            trainer.train(trainingData);

            // Evaluate the model
            double accuracy = trainer.evaluate(testData);

            // Update accuracy in the GUI
            predictionPanel.updateAccuracy(accuracy);

            JOptionPane.showMessageDialog(this, "Classifier trained successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error training classifier: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void makePrediction(boolean powerSurge, boolean coolingFailure, boolean sensorError, boolean manualOverride) {
        if (!trainer.isTrained()) {
            JOptionPane.showMessageDialog(this, "Please train the model before making predictions.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MachineData data = new MachineData(powerSurge, coolingFailure, sensorError, manualOverride, false);
        boolean isFaulty = trainer.predict(data);
        predictionPanel.updateResult(isFaulty);
    }
}
