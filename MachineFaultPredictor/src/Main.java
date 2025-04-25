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

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(trainer);
            frame.setVisible(true);
        });
    }
}
