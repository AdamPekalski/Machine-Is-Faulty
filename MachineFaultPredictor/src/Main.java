import ml.DataPreprocessor;
import ml.ModelTrainer;
import gui.MainFrame;
import data.MachineData;
import java.util.List;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Initialize components
        DataPreprocessor preprocessor = new DataPreprocessor();
        ModelTrainer trainer = new ModelTrainer();
        
        try {
            // Load and prepare data
            String dataPath = "data/predictive_dataset.csv";
            List<MachineData> allData = preprocessor.loadData(dataPath);
            
            if (allData.isEmpty()) {
                System.err.println("No data loaded from file: " + dataPath);
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
            System.out.printf("Model Accuracy: %.2f%%%n", accuracy * 100);
            
            // Launch GUI
            SwingUtilities.invokeLater(() -> {
                MainFrame frame = new MainFrame(trainer);
                frame.setVisible(true);
            });
        } catch (Exception e) {
            System.err.println("Error initializing application: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
