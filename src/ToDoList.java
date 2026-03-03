import java.sql.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ToDoList {

    private ArrayList<Task> tasks = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Connection con = ConnectionToDatabase.getConnection();


    public void loadDatabaseTasks(Connection con){

        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT title,description,priority,isChecked,dueDate FROM tasks");
            while(rs.next()){
                Task task = new Task();
                String title = rs.getString("title");
                String description = rs.getString("description");
                String dueDate = rs.getString("dueDate");
                String priority = rs.getString("priority");
                boolean isChecked = rs.getBoolean("isChecked");

                task.setTitle(title);
                task.setDescription(description);
                task.setDueDate(dueDate);
                task.setPriority(priority);
                task.setIsChecked(isChecked);

                this.tasks.add(task);
            }
        }
        catch (SQLException e){
            System.err.println("Error while trying to load tasks from database");
        }
    }
    public void saveTaskIntoDatabase(Connection con,Task task){
        String title = task.getTitle();
        String description = task.getDescription();
        String priority = String.valueOf(task.getPriority());
        boolean isChecked = task.getIsChecked();
        String dueDate = "";

        if (task.getDueDate() != null){
            dueDate = task.getDueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        }
        else {
            dueDate = String.valueOf(task.getDueDate());
        }
        String sql ="""
                        INSERT INTO tasks(title,description,priority,isChecked,dueDate)
                        VALUES (?, ?, ?, ?,?)
                    """;
        try{
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, priority);
            stmt.setBoolean(4,isChecked);

            stmt.setString(5,dueDate);

            int row = stmt.executeUpdate();
            if(row>0){
                System.out.println("Task has been saved successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error in creating statement");
        }

    }

    public void updateTaskInDatabase(Connection con,Task task){
        String titleOfTask = task.getTitle();
        String description = task.getDescription();
        String priority = String.valueOf(task.getPriority());
        boolean isChecked = task.getIsChecked();
        String dueDate = "";

        if (task.getDueDate() != null){
            dueDate = task.getDueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        }
        else {
            dueDate = String.valueOf(task.getDueDate());
        }
        String sql ="""
                        UPDATE tasks SET title = ?, description = ?, priority = ?, isChecked = ?, dueDate = ?
                        WHERE tasks.title = ? ;
                    """;
        try{
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, titleOfTask);
            stmt.setString(2, description);
            stmt.setString(3, priority);
            stmt.setBoolean(4,isChecked);
            stmt.setString(5,dueDate);
            stmt.setString(6,titleOfTask);

            int row = stmt.executeUpdate();
            if(row>0){
                System.out.println("Task has been updated successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error in creating statement");
        }

    }

    public void deleteTaskFromDatabase(Connection con,Task task){

        String sql = "DELETE FROM tasks WHERE title = ?;";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, task.getTitle());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Task deleted from database.");
            }
        }
        catch (SQLException e){
            System.err.println("Error in deleting statement: " + e.getMessage());

        }

    }



    public void addTask() {

        Task task = new Task();

        System.out.print("\nEnter task's title:");
        task.setTitle(scanner.nextLine());
        System.out.print("Enter task's description:");
        task.setDescription(scanner.nextLine());
        System.out.print("Enter task's due date (format: dd-mm-yyyy HH:mm):");
        task.setDueDate(scanner.nextLine());
        System.out.print("Enter task's priority(High, Medium, Low):");
        task.setPriority(scanner.nextLine().toUpperCase());

        tasks.add(task);
        saveTaskIntoDatabase(con,task);
        System.out.println("Task was added with success");

    }

    public void updateTask() {

        for (Task task : tasks) {
            System.out.println("Task ID: " + tasks.indexOf(task) + "\n" + task.toString());
        }

        System.out.println("Enter index of task you want to update: ");
        int i = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Set new title:");
        tasks.get(i).setTitle(scanner.nextLine());

        System.out.print("Set new description:");
        tasks.get(i).setDescription(scanner.nextLine());

        System.out.print("Set new due date (format: dd-mm-yyyy HH:mm):");
        tasks.get(i).setDueDate(scanner.nextLine());

        System.out.print("Set new priority (High, Medium, Low):");
        tasks.get(i).setPriority(scanner.nextLine());

        updateTaskInDatabase(con,tasks.get(i));


    }

    public void deleteTask() {
        for (Task task : tasks) {
            System.out.println("Task ID: " + tasks.indexOf(task) + task.toString());
        }

        System.out.print("Enter index of task you want to delete: ");
        int i = scanner.nextInt();

        deleteTaskFromDatabase(con,tasks.get(i));
        tasks.remove(i);


        System.out.println("Task was deleted with success");
    }

    public void showAllTasks() {
        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void checkTask() {
        for (Task task : tasks) {
            System.out.println("Task ID: " + tasks.indexOf(task) + task.toString());
        }

        System.out.print("Enter index of task you want to check: ");
        int i = scanner.nextInt();
        tasks.get(i).markTask();
        updateTaskInDatabase(con,tasks.get(i));
        System.out.println("Task was checked");
    }

    public void filterTasksByStatus() {

        int choice;
        System.out.println("1.View checked");
        System.out.println("2.View unchecked");
        System.out.print("Enter choice:");
        choice = scanner.nextInt();

        if (choice == 1) {
            for (Task task : tasks) {
                if (task.isChecked) {
                    System.out.println(task);
                }
            }
        } else {
            for (Task task : tasks) {
                if (!task.isChecked) {
                    System.out.println(task);
                }
            }
        }
    }

    public void sortToDoList() {
        int choice;
        System.out.println("1.Sort by priority");
        System.out.println("2.Sort by due date");
        System.out.println("3.Sort by title");
        System.out.print("Enter choice:");
        choice = scanner.nextInt();

        if (choice == 1) {
            tasks.sort(Comparator.comparing(
                    task -> task.getPriority().getLevelOfPriority()));
        }
        else if(choice==2){
            tasks.sort(Comparator.comparing(
                    Task::getDueDate,
                    Comparator.nullsLast(Comparator.naturalOrder())
            ));
        }
        else if(choice==3){
            tasks.sort(Comparator.comparing(
                    Task::getTitle));
        }

    }

}