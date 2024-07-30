import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmployeeManagementSystem extends JFrame {
    private List<Employee> employees;
    private List<User> users;
    private JTextField idField, nameField, positionField, salaryField;
    private JTextField usernameField, userPasswordField;
    private JCheckBox isAdminCheckBox;
    private JTextArea displayArea;

    public EmployeeManagementSystem() {
        employees = EmployeeFileHandler.readEmployees();
        users = UserFileHandler.readUsers();
        setTitle("Employee Management System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        showRegistrationScreen(); // Show registration screen first
    }

    private Employee findEmployeeByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                for (Employee employee : employees) {
                    if (employee.getName().equals(user.getEmployeeName())) {
                        return employee;
                    }
                }
            }
        }
        return null;
    }

    private void showRegistrationScreen() {
        JFrame registrationFrame = new JFrame("User Registration");
        registrationFrame.setSize(300, 200);
        registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrationFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        JTextField regUsernameField = new JTextField();
        JPasswordField regPasswordField = new JPasswordField();
        JTextField regEmployeeNameField = new JTextField();
        JCheckBox regIsAdminCheckBox = new JCheckBox();

        panel.add(new JLabel("Username:"));
        panel.add(regUsernameField);
        panel.add(new JLabel("Password:"));
        panel.add(regPasswordField);
        panel.add(new JLabel("Employee Name:"));
        panel.add(regEmployeeNameField);
        panel.add(new JLabel("Is Admin:"));
        panel.add(regIsAdminCheckBox);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = regUsernameField.getText();
                String password = new String(regPasswordField.getPassword());
                String employeeName = regEmployeeNameField.getText();
                boolean isAdmin = regIsAdminCheckBox.isSelected();

                User user = new User(username, password, isAdmin, employeeName);
                users.add(user);
                UserFileHandler.writeUsers(users);
                JOptionPane.showMessageDialog(registrationFrame, "User registered successfully!");
                registrationFrame.dispose();
                showLoginScreen();
            }
        });

        panel.add(registerButton);
        registrationFrame.add(panel);
        registrationFrame.setVisible(true);
    }

    private void showLoginScreen() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(300, 150);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField loginUsernameField = new JTextField();
        JPasswordField loginPasswordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(new JLabel("Username:"));
        panel.add(loginUsernameField);
        panel.add(new JLabel("Password:"));
        panel.add(loginPasswordField);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginUsernameField.getText();
                String password = new String(loginPasswordField.getPassword());

                User user = authenticateUser(username, password);
                if (user != null) {
                    JOptionPane.showMessageDialog(loginFrame, "Login successful!");
                    if (user.isAdmin()) {
                        initializeAdminUI();
                    } else {
                        Employee employee = findEmployeeByUsername(username);
                        if (employee != null) {
                            new UserDashboard(username, employee.getName(), employee.getPosition(), employee.getSalary()).setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(loginFrame, "Employee not found!");
                        }
                    }
                    loginFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid credentials!");
                }
            }
        });

        loginFrame.add(panel);
        loginFrame.setVisible(true);
    }

    private User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void initializeAdminUI() {
        getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(10, 2));

        // Employee fields
        panel.add(new JLabel("Employee ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Employee Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Position:"));
        positionField = new JTextField();
        panel.add(positionField);

        panel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        panel.add(salaryField);

        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
        panel.add(addEmployeeButton);

        // User fields
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        userPasswordField = new JTextField();
        panel.add(userPasswordField);

        panel.add(new JLabel("Is Admin:"));
        isAdminCheckBox = new JCheckBox();
        panel.add(isAdminCheckBox);

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });
        panel.add(addUserButton);

        JButton viewUsersButton = new JButton("View Users");
        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewUsers();
            }
        });
        panel.add(viewUsersButton);

        add(panel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void addEmployee() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String position = positionField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            Employee employee = new Employee(id, name, position, salary);
            employees.add(employee);
            EmployeeFileHandler.writeEmployees(employees);
            JOptionPane.showMessageDialog(this, "Employee added successfully!");
            clearEmployeeFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage());
        }
    }

    private void addUser() {
        String username = usernameField.getText();
        String password = userPasswordField.getText();
        boolean isAdmin = isAdminCheckBox.isSelected();
        String employeeName = nameField.getText();

        User user = new User(username, password, isAdmin, employeeName);
        users.add(user);
        UserFileHandler.writeUsers(users);
        JOptionPane.showMessageDialog(this, "User added successfully!");
        clearUserFields();
    }

    private void viewUsers() {
        StringBuilder userDisplay = new StringBuilder();
        for (User user : users) {
            userDisplay.append(user.getUsername())
                       .append(" (Admin: ").append(user.isAdmin())
                       .append(")\n");
        }
        displayArea.setText(userDisplay.toString());
    }

    private void clearEmployeeFields() {
        idField.setText("");
        nameField.setText("");
        positionField.setText("");
        salaryField.setText("");
    }

    private void clearUserFields() {
        usernameField.setText("");
        userPasswordField.setText("");
        isAdminCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeManagementSystem().setVisible(true);
            }
        });
    }
}
