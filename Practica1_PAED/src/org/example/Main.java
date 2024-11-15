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
                    QuickSort(tasks, 0, tasks.size()-1);
                    break;
                case 4:
                    //MergeSort(tasks);
                    mergeSort(tasks, 0, tasks.size()-1);

                    break;
                default:
                    System.out.print("Invalid option!");
            }
        }while(op<1 || op>4);

        // Imprimir las tareas ordenadas
        for (Task task : tasks) {
            System.out.print(task.getName());
            System.out.print("-"+task.getDificultat());
            System.out.println("-"+task.getProgress());
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

        /*
    *
    * FillArrTasks
    * Main objective: Fill the array with the tasks from the dataSet.
    * Parameters: String path.
    * Return: none.
    *
        */

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

        /*
    *
    * Selection sort algorithm
    * Main objective: Sort the task arr by using Selection sort technique
    * Parameters: ArrayList<Task> tasks
    * Return: none
    *
        */
    private static void selectionSort(ArrayList<Task> tasks) {
        //Gets the size of the task list
        int n = tasks.size();
        //Iterates through the list of tasks
        for (int i = 0; i < n - 1; i++) {
           //Assume that the current element is the minimum
            int min = i;
            // Compares the current element with the following ones
            for (int j = i + 1; j <= n-1; j++) {
                //If it finds a smaller element
                if (tasks.get(j).getName().compareTo(tasks.get(min).getName()) < 0) {
                    //Updates the index of the minimum
                    min = j;
                }
            }

            //Swaps the current element with the minimum element found
        Task temp = tasks.get(min);
            tasks.set(min, tasks.get(i));
            tasks.set(i, temp);
        }
    }

        /*
   *
   *Main objective: Sort the task arr by using Insertion sort technique.
   * Parameters: ArrayList<Task> tasks
   * Return: none
   *
        */
    private static void insertionSort(ArrayList<Task> tasks) {
        //Gets the size of the task list
        int n = tasks.size();
        //Iterates through the list of tasks
        for (int i = 1; i < n; i++) {
            //Takes the current element as the key
            Task key = tasks.get(i);
            //Initializes j with the index of the previous element
            int j = i - 1;
            //Moves elements larger than the key one position forward
            while (j >= 0 && tasks.get(j).getName().compareTo(key.getName()) > 0) {
                //Moves the element one position forward
                tasks.set(j + 1, tasks.get(j));
            }
            //Inserts the key in the correct position
        tasks.set(j + 1, key);
        }

    }



            /*
    *
    * MergeSort
    * Main objective: Divide & conquer algorithm that divides the task arr into two halves,
    *   sorts the two halves, and then merges the two sorted halves.
    * Parameters: ArrayList<Task> tasks, int i, int j
    * Return: none
    *
            */

    //Necesita optimización!!
    private static void mergeSort(ArrayList<Task> tasks, int i, int j) {
        if (i < j) {
            int m = (i + j) / 2;
            mergeSort(tasks, i, m); //LEFT
            mergeSort(tasks, m + 1, j); //RIGHT
            merge(tasks, i, m, j);
        }
    }


            /*
     *
     * Merge
     * Main objective: Sort the task arr by using Merge sort technique, following a priority order.
     * Parameters: ArrayList<Task> tasks, int i, int j
     * Return: none
     *
            */
    private static void merge(ArrayList<Task> tasks, int i, int meitat, int j) {

        int left = i;
        int right = meitat + 1;
        int cursor = i;
        ArrayList<Task> aux = new ArrayList<>(tasks);

        /*
        -->PRIORITY:
            1.TASK NAME
            2.DIFICULTY OF THE TASK
            3.PROGRES OF THE TASK
         */
        while ((left <= meitat)&&(right <= j)) {
            //We look if the name match
            if (tasks.get(left).getName().equals(tasks.get(right).getName())){
                //If the name does not match then we look if the Dificulty match
                if(tasks.get(left).getDificultat()==(tasks.get(right).getDificultat()))
                {
                    //If not, we directly order the tasks for the progess
                    if(tasks.get(left).getProgress()>tasks.get(right).getProgress())
                    {
                        //If the progress of the left task is greater than the right task, we swap the tasks
                        aux.set(cursor, tasks.get(left));
                        //and we advance the left index
                        left++;
                    }else{
                        //If the progress of the right task is greater than the left task, we swap the tasks
                        aux.set(cursor, tasks.get(right));
                        //and we advance the right index
                        right++;
                    }
                }else{

                    if(tasks.get(left).getDificultat()>tasks.get(right).getDificultat())
                    {
                        aux.set(cursor, tasks.get(left));
                        left++;
                    }else{
                        aux.set(cursor, tasks.get(right));
                        right++;
                    }
                }
            }else {
                if (tasks.get(left).getName().compareTo(tasks.get(right).getName()) < 0) {
                    aux.set(cursor, tasks.get(left));
                    left++;
                } else {
                    aux.set(cursor, tasks.get(right));
                    right++;
                }
                cursor++;
            }

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

    /*
    *
    * QuickSort(particio):Set the pivot and compare the elements of the array with the pivot,
    *   following a priority order by calling the compareTasks void function.
    * Parameters: ArrayList<Task> tasks, int i, int j
    * Return: int left position.
    *
     */
    private static int particio(ArrayList<Task> tasks, int i, int j) {
        int left = i;
        int right = j;
        int meitat = (i + j) / 2;
        Task pivot = tasks.get(meitat);

        while (left <= right) {
            // we compare by calling the function that takes into account the priority to advance the left index
            while (compareTasks(tasks.get(left), pivot) < 0) {
                left++;
            }
            // we compare by calling the function that takes into account the priority to advance the right index
            while (compareTasks(tasks.get(right), pivot) > 0) {
                right--;
            }

            if (left <= right) {
                // swap classes in the array
                Task aux = tasks.get(left);
                tasks.set(left, tasks.get(right));
                tasks.set(right, aux);
                left++;
                right--;
            }
        }
        return left;
    }

    /*
    * compareTasks
    * Main objective: Compare two tasks by name, difficulty and progress.
    * Parameters: Task t1, Task t2
    * Return: int (0--> false, 1--> true).
    *
     */

    private static int compareTasks(Task t1, Task t2) {
        /*
        -->PRIORITY:
            1.TASK NAME
            2.DIFICULTY OF THE TASK
            3.PROGRES OF THE TASK
         */

        // Compare by name
        int nameCompare = t1.getName().compareTo(t2.getName());
        if (nameCompare != 0) {
            return nameCompare;
        }
        // If th name match, we compare by dificulty
        int dificultyCompare = Integer.compare(t1.getDificultat(), t2.getDificultat());
        if (dificultyCompare != 0) {
            return dificultyCompare;
        }
        // If the dificulty also match, we order this two tasks by the progress
        return Integer.compare(t1.getProgress(), t2.getProgress());
    }

        /*
    *
    * QuickSort
    * Main objective: Setting the new pivot and calling the
    *   particio function to compare the elements of the array with the pivot.
    * Parameters: ArrayList<Task> tasks, int i, int j
    * Return: none
    *
        */
    private static void QuickSort(ArrayList<Task> tasks, int i, int j) {
        if (i < j) {
            int p = particio(tasks, i, j);
            QuickSort(tasks, i, p - 1);
            QuickSort(tasks, p, j);
        }
    }

        /*
    *
    * askForString
    * Main objective: Ask for a string to the user.
    * Parameters: String message, Scanner scanner
    * Return: String.
    *
        */
    private static String askForString(String message, Scanner scanner) {
        System.out.print(message);
        return scanner.nextLine();
    }

}