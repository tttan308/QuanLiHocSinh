package view;

import function.StudentList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame {
    private JPanel menuToolbar, studentTable;
    public MainFrame(){
        menuToolbar = MenuToolbarPanel.getMenuToolbar();
        studentTable = StudentTablePanel.getStudentTablePanel();
        JFrame frame = new JFrame("Quản lý học sinh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(menuToolbar, BorderLayout.NORTH);
        frame.add(studentTable, BorderLayout.CENTER);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
