import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ToDoList tasksList = new ToDoList();
        int choice = 0;



        // loading tasks from database into arraylist
        tasksList.loadDatabaseTasks(ConnectionToDatabase.getConnection());
        System.out.println("**************************");
        System.out.println(" Your Personal To-Do-List");
        System.out.println("**************************");

        while (choice != 8) {

            System.out.println("1.Create Task");
            System.out.println("2.Update Task");
            System.out.println("3.Delete Task");
            System.out.println("4.View all Tasks");
            System.out.println("5.Mark Task as Completed");
            System.out.println("6.Filter Tasks By Status");
            System.out.println("7.Sort Tasks\n");
            System.out.println("8.Exit program\n");
            System.out.print("Enter your choice:");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> tasksList.addTask();
                case 2 -> tasksList.updateTask();
                case 3 -> tasksList.deleteTask();
                case 4 -> tasksList.showAllTasks();
                case 5 -> tasksList.checkTask();
                case 6 -> tasksList.filterTasksByStatus();
                case 7 -> tasksList.sortToDoList();
            }
        }
        scanner.close();
    }
}
