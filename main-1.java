import java.io.*;
import java.util.*;

public class main {
    private static final String FILE_NAME = "todo.text";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("/n --- TODO LISt APP ----");
            System.out.println("1. Lihat To-Do List");
            System.out.println("2. Add Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Update Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    viewTasks();
                    break;
                case "2":
                    addTask(scanner);
                    break;
                case "3":
                    deleteTask(scanner);
                    break;
                case "4":
                    updateTask(scanner);
                    break;
                case "5":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static List<String> loadTasks() {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            // File may not exist yet, ignore
        }
        return tasks;
    }

    private static void saveTasks(List<String> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    private static void viewTasks() {
        List<String> tasks = loadTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Your To-Do List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter new task: ");
        String task = scanner.nextLine();
        List<String> tasks = loadTasks();
        tasks.add(task);
        saveTasks(tasks);
        System.out.println("Task added.");
    }

    private static void deleteTask(Scanner scanner) {
        List<String> tasks = loadTasks();
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to delete: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasks(tasks);
            System.out.println("Task deleted.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void updateTask(Scanner scanner) {
        List<String> tasks = loadTasks();
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to update: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index >= 0 && index < tasks.size()) {
            System.out.print("Enter new task description: ");
            String newTask = scanner.nextLine();
            tasks.set(index, newTask);
            saveTasks(tasks);
            System.out.println("Task updated.");
        } else {
            System.out.println("Invalid task number.");
        }
    }
}