import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileHandler {
    private static final String FILE_NAME = "employees.txt";

    public static List<Employee> readEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String position = parts[2];
                double salary = Double.parseDouble(parts[3]);
                employees.add(new Employee(id, name, position, salary));
            }
        } catch (IOException e) {
            System.out.println("Error reading employee file: " + e.getMessage());
        }
        return employees;
    }

    public static void writeEmployees(List<Employee> employees) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee employee : employees) {
                bw.write(employee.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing employee file: " + e.getMessage());
        }
    }
}
