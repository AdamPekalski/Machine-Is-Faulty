package gui;

import javax.swing.*;
import java.awt.*;
import ml.ModelTrainer;
import ml.PredictorAccuracy;
import ml.DataPreprocessor;
import data.MachineData;
import java.util.List;

public class MainFrame extends JFrame {
    private PredictionPanel predictionPanel;
    private AddDataPanel addDataPanel;
    private ResultsPanel resultsPanel;
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

        // Split the dataset
        List<MachineData>[] splitData = PredictorAccuracy.splitDataset(dataset);
        List<MachineData> trainingSet = splitData[0];
        List<MachineData> testingSet = splitData[1];

        // Train the model and calculate accuracies
        trainer.train(trainingSet);
        double testingSetAccuracy = trainer.evaluate(testingSet);
        double overallAccuracy = trainer.evaluate(dataset);

        // Create panels
        predictionPanel = new PredictionPanel(this);
        predictionPanel.updateOverallAccuracy(overallAccuracy); // Pass overall accuracy
        addDataPanel = new AddDataPanel(dataset);
        resultsPanel = new ResultsPanel(trainingSet.size(), testingSet.size(), testingSetAccuracy, overallAccuracy);

        // Create "Train Classifier" button
        trainButton = new JButton("Train Classifier");
        trainButton.addActionListener(e -> trainClassifier());

        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Predict & Results", predictionPanel);
        tabbedPane.addTab("Add Data", addDataPanel);
        tabbedPane.addTab("Training & Testing Results", resultsPanel);

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
            List<MachineData>[] splitData = PredictorAccuracy.splitDataset(dataset);
            List<MachineData> trainingData = splitData[0];
            List<MachineData> testData = splitData[1];

            // Train the model
            trainer.train(trainingData);

            // Evaluate the model
            double testingSetAccuracy = trainer.evaluate(testData);
            double overallAccuracy = trainer.evaluate(dataset);

            // Update ResultsPanel
            resultsPanel = new ResultsPanel(trainingData.size(), testData.size(), testingSetAccuracy, overallAccuracy);

            // Update PredictionPanel with overall accuracy
            predictionPanel.updateOverallAccuracy(overallAccuracy);

            // Refresh the GUI
            JTabbedPane tabbedPane = (JTabbedPane) getContentPane().getComponent(0);
            tabbedPane.setComponentAt(2, resultsPanel);

            JOptionPane.showMessageDialog(this, "Classifier trained successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error training classifier: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void makePrediction(boolean powerSurge, boolean coolingFailure, boolean sensorError, boolean manualOverride) {
        // Check if the model is trained
        if (!trainer.isTrained()) {
            JOptionPane.showMessageDialog(this, "Please train the model before making predictions.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a MachineData object with the input features
        MachineData data = new MachineData(powerSurge, coolingFailure, sensorError, manualOverride, false);

        // Predict whether the machine is faulty
        boolean isFaulty = trainer.predict(data);

        // Update the PredictionPanel with the result
        predictionPanel.updateResult(isFaulty);
    }
}
