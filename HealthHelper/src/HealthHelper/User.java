package HealthHelper;

import java.io.Serializable;

public class User implements Serializable {
    User(int id, Data data, String date) {
        this.id = id;
        this.data = data;
        this.date = date;
    }

    private int id;
    private Data data;
    private String date;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
