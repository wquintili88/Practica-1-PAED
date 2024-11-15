package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private String name;
    private String date_limit;
    private int temps;
    private int dificultat;
    private int progress;
    private String color;
    private String edifici;

    public Task(String name, String date_limit, int temps, int dificultat, int progress, String color, String edifici) {
        this.name = name;
        this.date_limit = date_limit;
        this.temps = temps;
        this.dificultat = dificultat;
        this.progress = progress;
        this.color = color;
        this.edifici = edifici;
    }

    public static Task fromLine(String line) {
        String[] parts = line.split(";");
        return new Task(
                parts[0],
                parts[1],
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4]),
                parts[5],
                parts[6]
        );
    }


    public String getName() {
        return name;
    }
    public int getDificultat() { return dificultat; }
    public int getProgress() { return progress; }
}