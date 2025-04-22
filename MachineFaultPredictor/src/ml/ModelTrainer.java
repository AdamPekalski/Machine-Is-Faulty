package ml;

import data.MachineData;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class ModelTrainer {
    private NaiveBayesClassifier classifier;
    
    public ModelTrainer() {
        this.classifier = new NaiveBayesClassifier();
    }
    
    public void train(List<MachineData> trainingData) {
        classifier.train(trainingData);
    }
    
    public double evaluate(List<MachineData> testData) {
        int correctPredictions = 0;
        
        for (MachineData data : testData) {
            boolean prediction = classifier.predict(data);
            if (prediction == data.isFaulty()) {
                correctPredictions++;
            }
        }
        
        return (double) correctPredictions / testData.size();
    }
    
    public boolean predict(MachineData data) {
        return classifier.predict(data);
    }
    
    public static List<MachineData>[] splitData(List<MachineData> data, double trainRatio) {
        List<MachineData> trainingData = new ArrayList<>();
        List<MachineData> testData = new ArrayList<>();
        
        Random random = new Random();
        for (MachineData instance : data) {
            if (random.nextDouble() < trainRatio) {
                trainingData.add(instance);
            } else {
                testData.add(instance);
            }
        }
        
        return new List[]{trainingData, testData};
    }
}
