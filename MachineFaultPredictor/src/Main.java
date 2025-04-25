import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import gui.MainFrame;
import ml.ModelTrainer;

public class Main {
    public static void main(String[] args) {
        // Launch the GUI
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(new ModelTrainer());
            frame.setVisible(true);
        });
    }
}
