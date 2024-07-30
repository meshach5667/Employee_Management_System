import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {
    private String username;
    private String employeeName;
    private String position;
    private double salary;

    public UserDashboard(String username, String employeeName, String position, double salary) {
        this.username = username;
        this.employeeName = employeeName;
        this.position = position;
        this.salary = salary;
        setTitle("User Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JTextArea userInfo = new JTextArea();
        userInfo.setText("Name: " + employeeName + "\nPosition: " + position + "\nSalary: " + salary);
        panel.add(new JScrollPane(userInfo), BorderLayout.CENTER);

        add(panel);
    }
}
