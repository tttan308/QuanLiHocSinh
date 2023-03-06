package view;

import javax.swing.*;
import java.awt.*;
import static view.StudentTablePanel.stuList;
public class MainFrame {
    private JPanel menuToolbar, studentTable, titleTable;
    public MainFrame(){
        menuToolbar = MenuToolbarPanel.getMenuToolbar();

        JLabel title = new JLabel("Danh sách học sinh");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        titleTable = new JPanel();
        titleTable.add(title);
        titleTable.setPreferredSize(new Dimension(1000, 50));

        studentTable = StudentTablePanel.getStudentTablePanel();

        JFrame frame = new JFrame("Quản lý học sinh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(menuToolbar);
        frame.add(titleTable);
        frame.add(studentTable);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
