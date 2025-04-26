package ml;

import data.MachineData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PredictorAccuracy {

    public static List<MachineData>[] splitDataset(List<MachineData> dataset) {
        List<MachineData> faultyData = new ArrayList<>();
        List<MachineData> nonFaultyData = new ArrayList<>();

        // Separate the dataset into faulty and non-faulty entries
        for (MachineData data : dataset) {
            if (data.isFaulty()) {
                faultyData.add(data);
            } else {
                nonFaultyData.add(data);
            }
        }

        // Shuffle the data to remove any order bias
        Collections.shuffle(faultyData);
        Collections.shuffle(nonFaultyData);

        // Calculate the split sizes
        int faultyTrainSize = (int) Math.round(faultyData.size() * 0.75);
        int nonFaultyTrainSize = (int) Math.round(nonFaultyData.size() * 0.75);

        // Create training and testing sets
        List<MachineData> trainingSet = new ArrayList<>();
        List<MachineData> testingSet = new ArrayList<>();

        // Add 75% of faulty and non-faulty data to the training set
        trainingSet.addAll(faultyData.subList(0, faultyTrainSize));
        trainingSet.addAll(nonFaultyData.subList(0, nonFaultyTrainSize));

        // Add the remaining rows to the testing set
        testingSet.addAll(faultyData.subList(faultyTrainSize, faultyData.size()));
        testingSet.addAll(nonFaultyData.subList(nonFaultyTrainSize, nonFaultyData.size()));

        // Ensure the total rows match the original dataset size
        if (trainingSet.size() + testingSet.size() != dataset.size()) {
            throw new IllegalStateException("Split sizes do not match the original dataset size.");
        }

        return new List[]{trainingSet, testingSet};
    }
}