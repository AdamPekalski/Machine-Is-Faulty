import javax.swing.SwingUtilities;
import gui.MainFrame;
import ml.ModelTrainer;
import ml.PredictorAccuracy;
import data.MachineData;
import ml.DataPreprocessor;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Load the dataset
        DataPreprocessor preprocessor = new DataPreprocessor();
        String dataPath = "data/rule_based_predictive_dataset.csv";
        List<MachineData> dataset = preprocessor.loadData(dataPath);

        // Split the dataset into training and testing sets
        List<MachineData>[] splitData = PredictorAccuracy.splitDataset(dataset);
        List<MachineData> trainingSet = splitData[0];
        List<MachineData> testingSet = splitData[1];

        // Train the model
        ModelTrainer trainer = new ModelTrainer();
        trainer.train(trainingSet);

        // Launch the GUI
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(trainer);
            frame.setVisible(true);
        });
    }
}
