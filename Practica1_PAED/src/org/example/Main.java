package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {

        int op=0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Which dataset do you want to analise:");
            System.out.println("\t1.ascending");
            System.out.println("\t2.ascendingSmall");
            System.out.println("\t3.descending");
            System.out.println("\t4.descendingSmall");
            System.out.println("\t5.random");
            System.out.println("\t6.randomSmall");
            System.out.println("Introduce option: ");
            op = scanner.nextInt();

            switch (op) {
                case 1:
                    FillArrTasks("Practica1_PAED/src/org/example/dataSets/ascending.paed");
                    break;
                case 2:
                    FillArrTasks("Practica1_PAED/src/org/example/dataSets/ascendingSmall.paed");
                    break;
                case 3:
                    FillArrTasks("Practica1_PAED/src/org/example/dataSets/descending.paed");
                    break;
                case 4:
                    FillArrTasks("Practica1_PAED/src/org/example/dataSets/descendingSmall.paed");
                    break;
                case 5:
                    FillArrTasks("Practica1_PAED/src/org/example/dataSets/random.paed");
                    break;
                case 6:
                    FillArrTasks("Practica1_PAED/src/org/example/dataSets/randomSmall.paed");
                    break;

                default:
                    System.out.print("Invalid option!");
            }
        }while(op<1 && op>6);

        /*   TEST ARR SAVED VALUES
        try {
            FillArrTasks(tasks);

            //test print name tasks
            System.out.println();

            for (int i=0; i<16; i++) {
                System.out.println(tasks.get(i).getName());
            }

        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        }
        */



    }

    public static void FillArrTasks(String path) throws FileNotFoundException {

        File archivo = new File(path);
        Scanner sc = new Scanner(archivo);
        int count=0;
        Scanner scanner = new Scanner(System.in);
        Task task;

        try {
            Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        int num_elements = Integer.parseInt(sc.nextLine().trim());
        System.out.println("Número de elementos: " + num_elements);

        while (count < num_elements && sc.hasNextLine()) {
            String linea = sc.nextLine();

            task = Task.fromLine(linea); // convert line into class
            tasks.add(task); // add class to Arraylist
            count++;

        }
    }

    private static String askForString(String message, Scanner scanner) {
        System.out.print(message);
        return scanner.nextLine();
    }

}