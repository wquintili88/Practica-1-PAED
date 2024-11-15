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
        }while(op<1 || op>6);
        //while(op<1 && op>6); -> no funciona siempre es false

        do {
            System.out.println("Which algorithm do you want to use:");
            System.out.println("\t1.selectionSort");
            System.out.println("\t2.insertionSort");
            System.out.println("\t3.QuickSort");
            System.out.println("\t4.Merge Sort");
            System.out.println("Introduce option: ");
            op = scanner.nextInt();

            switch (op) {
                case 1:
                    // Ordenar las tareas utilizando el algoritmo de selección
                    selectionSort(tasks);
                    /*
                     * NO TERMINA DE FUNCIONAR CORRECTAMENTE YA QUE LA ULTIMA TAREA NO SE ORDENA
                     */
                    break;
                case 2:
                    // Ordenar las tareas utilizando el algoritmo de inserción
                    insertionSort(tasks);
                    /*
                     * NO TERMINA DE FUNCIONAR CORRECTAMENTE YA QUE LA ULTIMA TAREA NO SE ORDENA
                     */
                    break;
                case 3:
                    //QuickSort(tasks);
                    break;
                case 4:
                    //MergeSort(tasks);
                    break;
                default:
                    System.out.print("Invalid option!");
            }
        }while(op<1 || op>3);

        // Imprimir las tareas ordenadas
        for (Task task : tasks) {
            System.out.println(task.getName());
        }

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
    // Metodo para ordenar una lista de tareas utilizando el algoritmo de selección
    private static void selectionSort(ArrayList<Task> tasks) {
        int n = tasks.size();                                                   // Obtiene el tamaño de la lista de tareas
        for (int i = 0; i < n - 1; i++) {                                       // Itera sobre la lista hasta el penúltimo elemento
            int min = i;                                                        // Asume que el elemento actual es el mínimo
            for (int j = i + 1; j <= n-1; j++) {                                 // Compara el elemento actual con los siguientes
                if (tasks.get(j).getName().compareTo(tasks.get(min).getName()) < 0) { // Si encuentra un elemento menor
                    min = j;                                                    // Actualiza el índice del mínimo
                }
            }
        Task temp = tasks.get(min);                                             // Intercambia el elemento mínimo encontrado con el elemento actual
            tasks.set(min, tasks.get(i));
            tasks.set(i, temp);
        }
        //return "Selection sort";                                              // Retorna el nombre del algoritmo utilizado
    }

    // Metodo para ordenar una lista de tareas utilizando el algoritmo de inserción
    private static void insertionSort(ArrayList<Task> tasks) {
        int n = tasks.size();                                                   // Obtiene el tamaño de la lista de tareas
        for (int i = 1; i < n; i++) {                                       // Itera desde el segundo elemento hasta el penúltimo
            Task key = tasks.get(i);                                            // Toma el elemento actual como clave
            int j = i - 1;                                                      // Inicializa j con el índice del elemento anterior
                                                                                // Desplaza los elementos mayores que la clave una posición hacia adelante
            while (j >= 0 && tasks.get(j).getName().compareTo(key.getName()) > 0) {
                tasks.set(j + 1, tasks.get(j));                                 // Mueve el elemento una posición hacia adelante
            j = j - 1;                                                          // Decrementa j
            }
        tasks.set(j + 1, key);                                                  // Coloca la clave en su posición correcta
        }
        //return "Insertion sort";                                              // Retorna el nombre del algoritmo utilizado
    }

    private void mergeSort(ArrayList<Task> tasks, int i , int j) {
        if (i < j) {
            int m = (i + j) / 2;
            mergeSort(tasks, i, m); //LEFT
            mergeSort(tasks, m + 1, j); //RIGHT
            merge(tasks, i, m, j);
        }
        //return "Merge sort";
    }
    private void merge(ArrayList<Task> tasks, int i, int meitat, int j) {

        int left = i;
        int right = meitat + 1;
        int cursor = i;
        ArrayList<Task> aux = new ArrayList<>(tasks);
        while ((left <= meitat)&&(right <= j)) {
            if (tasks.get(left).getName().compareTo(tasks.get(right).getName()) < 0) {
                aux.set(cursor, tasks.get(left));
                left++;
            } else {
                aux.set(cursor, tasks.get(right));
                right++;
            }
            cursor++;
        }
        while (left <= meitat) {
            aux.set(cursor, tasks.get(left));
            left++;
            cursor++;
        }
        while (right <= j) {
            aux.set(cursor, tasks.get(right));
            right++;
            cursor++;
        }
        cursor = i;
        while (cursor <= j) {
            tasks.set(cursor, aux.get(cursor));
            cursor++;
        }
    }




    private static String askForString(String message, Scanner scanner) {
        System.out.print(message);
        return scanner.nextLine();
    }

}