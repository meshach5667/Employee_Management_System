import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileHandler {
    private static final String FILE_PATH = "users.txt";

    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String username = parts[0];
                    String password = parts[1];
                    boolean isAdmin = Boolean.parseBoolean(parts[2]);
                    String employeeName = parts[3]; // Ensure this part is read correctly
                    users.add(new User(username, password, isAdmin, employeeName));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void writeUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword() + "," +
                             user.isAdmin() + "," + user.getEmployeeName());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
