import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmployeeManagementSystem ems = new EmployeeManagementSystem();
            ems.setVisible(true);
        });
    }
}
