package view;

import javax.swing.*;
import java.awt.*;
import static view.StudentTablePanel.stuList;
public class MainFrame {
    private JPanel menuToolbar, studentTable, titleTable;
    public MainFrame(){
        menuToolbar = MenuToolbarPanel.getMenuToolbar();

        JLabel title = new JLabel("Danh sách học sinh");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        titleTable = new JPanel();
        titleTable.setLayout(new FlowLayout());
        titleTable.add(title);

        studentTable = StudentTablePanel.getStudentTablePanel();


        JFrame frame = new JFrame("Quản lý học sinh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel appPanel = new JPanel();
        appPanel.setLayout(new BorderLayout());
        appPanel.add(menuToolbar, BorderLayout.PAGE_START);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(titleTable, BorderLayout.PAGE_START);
        tablePanel.add(studentTable, BorderLayout.CENTER);
        appPanel.add(tablePanel, BorderLayout.CENTER);

        frame.add(appPanel);
        frame.setSize(1000, 600);
        frame.setMinimumSize(new Dimension(320, 150));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
