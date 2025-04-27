# Naive Bayes Machine Fault Predictor - Adam Pekalski C23475872

## Project Overview
This application is a Naive Bayes GUI predictor that predicts if a Machine is faulty based on a given dataset. The application implements the Naive Bayes classifier algorithm to analyze machine fault patterns based on specific features listed in the frequency table.

The dataset used to train the predictor was generated using ChatGPT 4o.

## Java Classes

Each class is seperated into packages, as I found it easier to keep functionality when spread across multiple files as opposed to less.
Throughout the project, I tried to stick to Java convention as much as possible, to follow best practices.

### Main Package
#### 1. Main.java
   - The initiator of the application that initializes the classifier, loads the dataset and then launches the GUI.

### ML Package

#### 2. DataPreprocessor.java
  - Handles loading and preprocessing of the dataset from a CSV file.

#### 3. PredictorAccuracy.java
   - Splits the dataset into training and testing sets while maintaining proportional distribution.

#### 4. ModelTrainer.java
   - Manages training, evaluation, and prediction using the Naive Bayes classifier.

#### 5. NaiveBayesClassifier.java
   - Implements the Naive Bayes algorithm for training and predicting machine faults.

### Data package

#### 6. MachineData.java
   - Represents the data structure for machine conditions and fault status.

### GUI Package

#### 7. MainFrame.java
   -Main GUI frame that integrates all panels and manages user interactions.

#### 8. PredictionPanel.java
   - GUI panel for making predictions and displaying results.

#### 9. ResultsPanel.java
   - GUI panel for displaying training/testing set sizes and model accuracies.

#### 10. AddDataPanel.java
   - GUI panel for adding new data to the dataset.

#### 11. InputPanel.java
   - GUI panel for selecting machine conditions as input for predictions.

#### 12. ResultPanel.java
   - GUI panel for displaying prediction results and recommendations.


## Frequency Table for the Dataset
The frequency table shows the counts for different feature combinations in the dataset:

| Feature | Value | Mahince Not faulty = yes | Machine Is Faulty = no |
|---------|-------|----------------------|---------------------|
| PowerSurge | Yes | 63 | 35 |
| Powesurge | No | 26 | 76 |
| SensorError | Yes | 65 | 36 |
| SensorError | No | 24 | 75 |
| CoolingFailure | Yes | 63 | 38 |
| CoolingFailure | No | 26 | 73 |
| ManualOverdrive | Yes | 26 | 74 |
| ManualOverdrive | No | 63 | 37 |

Total count: 200 data instances
- Machine Is Faulty: 89 instances
- Machine Is Not Faulty : 111 instances


## Functionality 

 **Prediction**
   - Predict whether a Machine is faulty
   - Display results with probabilities

 **Data Management**
   - Add new data instances to the dataset through user input
   - Save updated dataset to the CSV file

 **Model Training**
   - Display training status and completion message
   - Train the Naive Bayes classifier on the dataset
   - Data retrains after adding new data

 **Model Evaluation**
   - Evaluate accuracy with different test splits
   - Display evaluation metrics and results using a simple interface

 **User Interface**
   - Basic user interface for navigation
   - User-friendly input controls (check boxes and buttons)

## Issues during production
When creating this assignment I ran into some pit falls along the way. These are the biggest ones:

 **Gradle**
    - I initialy intedned to make this project using gradle as I wanted to learn how to use it, this did not work ouut
    and it was scrapped

**Dataset**
    - I realised that my initial dataset was not realistic for the use of the Naive Bayes algorithm. Lacking in meaningful patterns, my model was stuck at 30-45% accuracy. Changing the dataset to one that followed patterns allowed me to achieve up to 80% on average.

**GUI**
    - Struggled heavily with styling and creating a flashy gui for clean and satisfying user experience. Settled for super basic
    entry level design for the sake of functionality.


## Future Improvements

If I had more time, I would add the following features:

1. **Gradle**
   - I would take the time to learn how to use gradle to aid in creating a cleaner and safer product with the use of the compiling capabilities of gradle
   - This could lead to possibly a cleaner file structure in the program

2. **Visualization/Visuals**
   - Add charts and graphs for data exploration and visiualization
   - Implement a sharper and more up to date with modern standards GUI

3. **DataSet Variablity**
   - Allow multiple datasets to be used for training
  
4 . **Smarter Model**
   - Retain trained datasets in memory so that multiple datasets can be tested and trained by the same model
   - This could lead to more accurate results with a more trained model
   