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

    public static <Strinf> List<Task> readTasksFromFile(Strinf filePath) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 7) {
                    String name = fields[0];
                    String date_limit = fields[1];
                    int temps = Integer.parseInt(fields[2]);
                    int dificultat = Integer.parseInt(fields[3]);
                    int progress = Integer.parseInt(fields[4]);
                    String color = fields[5];
                    String edifici = fields[6];
                    tasks.add(new Task(name, date_limit, temps, dificultat, progress, color, edifici));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;

    }

}