package data;

public class MachineData {
    private boolean powerSurge;
    private boolean coolingFailure;
    private boolean sensorError;
    private boolean manualOverride;
    private boolean isFaulty;

    public MachineData(boolean powerSurge, boolean coolingFailure, 
                      boolean sensorError, boolean manualOverride, boolean isFaulty) {
        this.powerSurge = powerSurge;
        this.coolingFailure = coolingFailure;
        this.sensorError = sensorError;
        this.manualOverride = manualOverride;
        this.isFaulty = isFaulty;
    }

    // Getters
    public boolean isPowerSurge() { return powerSurge; }
    public boolean isCoolingFailure() { return coolingFailure; }
    public boolean isSensorError() { return sensorError; }
    public boolean isManualOverride() { return manualOverride; }
    public boolean isFaulty() { return isFaulty; }

    @Override
    public String toString() {
        return String.format("Power Surge: %s, Cooling Failure: %s, Sensor Error: %s, Manual Override: %s, Faulty: %s",
                powerSurge, coolingFailure, sensorError, manualOverride, isFaulty);
    }
} 