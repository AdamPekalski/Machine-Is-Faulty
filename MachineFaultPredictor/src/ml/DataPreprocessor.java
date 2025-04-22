package ml;

import data.MachineData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataPreprocessor {
    public List<MachineData> loadData(String filePath) {
        List<MachineData> data = new ArrayList<>();
        
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            
            if (is == null) {
                throw new RuntimeException("Could not find file: " + filePath);
            }
            
            // Skip header line
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] values = line.split(",");
                if (values.length >= 5) {
                    boolean powerSurge = "yes".equals(values[0].trim());
                    boolean coolingFailure = "yes".equals(values[1].trim());
                    boolean sensorError = "yes".equals(values[2].trim());
                    boolean manualOverride = "yes".equals(values[3].trim());
                    boolean isFaulty = "yes".equals(values[4].trim());
                    
                    data.add(new MachineData(powerSurge, coolingFailure, 
                                           sensorError, manualOverride, isFaulty));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading data: " + e.getMessage());
        }
        
        return data;
    }
}
