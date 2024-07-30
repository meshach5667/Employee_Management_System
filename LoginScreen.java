// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.List;

// public class LoginScreen extends JFrame {
//     private List<User> users;
//     private JTextField usernameField;
//     private JPasswordField passwordField;

//     public LoginScreen() {
//         users = UserFileHandler.readUsers();
//         setTitle("Login Screen");
//         setSize(400, 200);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);
//         initializeUI();
//     }

//     private void initializeUI() {
//         JPanel panel = new JPanel(new GridLayout(3, 2));

//         panel.add(new JLabel("Username:"));
//         usernameField = new JTextField();
//         panel.add(usernameField);

//         panel.add(new JLabel("Password:"));
//         passwordField = new JPasswordField();
//         panel.add(passwordField);

//         JButton loginButton = new JButton("Login");
//         loginButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 loginUser();
//             }
//         });
//         panel.add(loginButton);

//         add(panel);
//     }

//     private void loginUser() {
//         String username = usernameField.getText();
//         String password = new String(passwordField.getPassword());

//         for (User user : users) {
//             if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
//                 JOptionPane.showMessageDialog(this, "Login successful!");
//                 if (user.isAdmin()) {
//                     new EmployeeManagementSystem().setVisible(true);
//                 } else {
//                     new UserDashboard(username).setVisible(true);
//                 }
//                 this.dispose();
//                 return;
//             }
//         }

//         // JOptionPane.showMessageDialog(this, "Invalid username or password!");
//     }
// }
