package ml;

import data.MachineData;
import java.util.List;
import java.util.ArrayList;

public class NaiveBayesClassifier {
    private double pFaulty;
    private double pNotFaulty;
    private double[][][] featureProbabilities;

    public void train(List<MachineData> trainingData) {
        int totalSamples = trainingData.size();
        int faultyCount = 0;
        
        // Count faulty samples
        for (MachineData data : trainingData) {
            if (data.isFaulty()) faultyCount++;
        }
        
        // Calculate prior probabilities
        pFaulty = (double) faultyCount / totalSamples;
        pNotFaulty = 1.0 - pFaulty;
        
        // Initialize feature probabilities array
        // [feature][value][class] where class 0 = not faulty, 1 = faulty
        featureProbabilities = new double[4][2][2];
        
        // Calculate conditional probabilities for each feature
        for (int feature = 0; feature < 4; feature++) {
            for (int value = 0; value < 2; value++) {
                int countFaulty = 0;
                int countNotFaulty = 0;
                
                for (MachineData data : trainingData) {
                    boolean featureValue = getFeatureValue(data, feature);
                    if (featureValue == (value == 1)) {
                        if (data.isFaulty()) countFaulty++;
                        else countNotFaulty++;
                    }
                }
                
                // Apply Laplace smoothing
                featureProbabilities[feature][value][0] = (countNotFaulty + 1.0) / (totalSamples - faultyCount + 2);
                featureProbabilities[feature][value][1] = (countFaulty + 1.0) / (faultyCount + 2);
            }
        }
    }
    
    private boolean getFeatureValue(MachineData data, int feature) {
        switch (feature) {
            case 0: return data.isPowerSurge();
            case 1: return data.isCoolingFailure();
            case 2: return data.isSensorError();
            case 3: return data.isManualOverride();
            default: throw new IllegalArgumentException("Invalid feature index");
        }
    }
    
    public boolean predict(MachineData data) {
        double pFaultyGivenFeatures = pFaulty;
        double pNotFaultyGivenFeatures = pNotFaulty;
        
        // Calculate probability for each feature
        for (int feature = 0; feature < 4; feature++) {
            boolean value = getFeatureValue(data, feature);
            int valueIndex = value ? 1 : 0;
            
            pFaultyGivenFeatures *= featureProbabilities[feature][valueIndex][1];
            pNotFaultyGivenFeatures *= featureProbabilities[feature][valueIndex][0];
        }
        
        return pFaultyGivenFeatures > pNotFaultyGivenFeatures;
    }
}
