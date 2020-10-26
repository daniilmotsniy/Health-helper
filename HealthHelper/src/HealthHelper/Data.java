package HealthHelper;

public class Data {
    Data(float temperature, float glucose, int pulse, int rate, String pressure){
        this.temperature = temperature;
        this.glucose = glucose;
        this.pulse = pulse;
        this.rate = rate;
        this.pressure = pressure;
    }

    private float temperature;
    private float glucose;
    private int pulse;
    private int rate;
    private String pressure;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getGlucose() {
        return glucose;
    }

    public void setGlucose(float glucose) {
        this.glucose = glucose;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
