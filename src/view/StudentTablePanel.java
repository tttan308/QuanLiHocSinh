package view;

import function.StudentList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentTablePanel{
    private JPanel studentTable;
    private JTable table;
    private String[] columnNames = {"Mã học sinh", "Họ tên", "Điểm", "Địa chỉ", "Ghi chú", "Ảnh"};
    public static DefaultTableModel tableModel;
    public static StudentList stuList = new StudentList();

    public StudentTablePanel(){
        studentTable = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 5){
                    return ImageIcon.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(150);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 200, 880, 200);
        studentTable.add(scrollPane, BorderLayout.CENTER);
    }

    public static JPanel getStudentTablePanel() {
        return new StudentTablePanel().studentTable;
    }

    public static void reloadTable(){
        tableModel.setRowCount(0);
        for(int i = 0; i < stuList.getSize(); i++){
            tableModel.addRow(new Object[]{stuList.getStudent(i).getId(), stuList.getStudent(i).getName(), stuList.getStudent(i).getGrade(), stuList.getStudent(i).getAddress(), stuList.getStudent(i).getNote(), stuList.getStudent(i).getImg()});
        }
    }
}
