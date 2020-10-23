package HealthHelper;

public class User {


    public User(int id, float temperature, float glucose, int pulse, int rate, String pressure, String date) {
        this.id = id;
        this.temperature = temperature;
        this.glucose = glucose;
        this.pulse = pulse;
        this.rate = rate;
        this.pressure = pressure;
        this.date = date;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private float temperature;
    private float glucose;
    private int pulse;
    private int rate;
    private String pressure;
    private String date;
}
