package view;

import function.StudentList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class MainFrame {
    private JTable table;
    private JLabel imageLabel = new JLabel();

    private JTextField idField, nameField, gradeField, addressField, noteField;
    private String[] columnNames = {"Mã học sinh", "Họ tên", "Điểm", "Địa chỉ", "Ghi chú", "Ảnh"};
    private DefaultTableModel tableModel;

    private JButton addButton, editButton, deleteButton, saveButton, addImageFile;

    private StudentList stuList = new StudentList();
    private ImageIcon imageFile = null;
    
    private JPanel cards;
    public MainFrame(){
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4,1));
        
        String comboBoxItems[] = {"Thêm học sinh", "Cập nhật học sinh", "Xóa học sinh"};
        JComboBox comboBox = new JComboBox(comboBoxItems);

        comboBox.setEditable(false);
        comboBox.addItemListener( e -> {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, (String)e.getItem());
        });

        mainPanel.add(comboBox);

        // Card panel
        cards = new JPanel(new CardLayout());
        cards.add(addStudentPanel(), "Thêm học sinh");
        cards.add(addStudentPanel(), "Cập nhật học sinh");
        cards.add(addStudentPanel(), "Xóa học sinh");

        mainPanel.add(cards);

        JLabel label = new JLabel("DANH SÁCH HỌC SINH", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 24)); // Set font chữ lớn
        mainPanel.add(label);
        // Table panel
        mainPanel.add(showDataPanel());


        // Action event
        // add image button
//        addImageFile.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.setDialogTitle("Chọn ảnh");
//                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//                int result = fileChooser.showOpenDialog(null);
//                if (result == JFileChooser.APPROVE_OPTION) {
//                    File selectedFile = fileChooser.getSelectedFile();
//                    imageFile = new ImageIcon(selectedFile.getPath());
//                    Image img = imageFile.getImage();
//                    Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//                    imageFile = new ImageIcon(newImg);
//                    imageLabel.setIcon(imageFile);
//                    addImageFile.setText("Đổi ảnh");
//                    // add path below
//                    JLabel path = new JLabel(selectedFile.getPath());
//                    path.setBounds(100, 100, 200, 20);
//                    inputPanel().add(path);
//                }
//            }
//        });
//
//        // add button
//        addButton.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String id = idField.getText();
//                String name = nameField.getText();
//                float grade = 0;
//                try{
//                    Float.parseFloat(gradeField.getText());
//                }
//                catch (NumberFormatException ex){
//                    JOptionPane.showMessageDialog(null, "Điểm phải là số");
//                }
//                String address = addressField.getText();
//                String note = noteField.getText();
//                if(id.equals("") || name.equals("") || gradeField.getText().equals("") || address.equals("") || note.equals("") || imageFile == null)
//                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
//                else
//                    try{
//                        stuList.addStudent(id, name, grade, imageFile, address, note);
//                        tableModel.addRow(new Object[]{id, name, grade, address, note, imageFile});
//                    }
//                    catch (Exception ex){
//                        JOptionPane.showMessageDialog(null, "Mã học sinh đã tồn tại");
//                    }
//            }
//        });



        // Frame
        JFrame frame = new JFrame("Quản lý học sinh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel inputPanel(){
        idField = new JTextField(8);
        nameField = new JTextField(20);
        gradeField = new JTextField(5);
        addressField = new JTextField(20);
        noteField = new JTextField(20);

        addImageFile = new JButton("Thêm ảnh");

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Mã học sinh:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(idField, gbc);
        gbc.gridx = 2;
        inputPanel.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx = 3;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Điểm:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(gradeField, gbc);
        gbc.gridx = 2;
        inputPanel.add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx = 3;
        inputPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Ghi chú:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(noteField, gbc);
        gbc.gridx = 2;
        inputPanel.add(new JLabel("Ảnh:"), gbc);
        gbc.gridx = 3;
        inputPanel.add(addImageFile, gbc);

        addButton = new JButton("Thêm");
        inputPanel.add(addButton);
        // set center
        inputPanel.setBounds(0, 0, 700, 130);


        return inputPanel;
    }

    private JPanel addStudentPanel(){
        JPanel addStudentPanel = new JPanel();
        addStudentPanel.setLayout(null);
        addStudentPanel.add(inputPanel());
        return addStudentPanel;
    }

    private JPanel editStudentPanel(){
        JPanel editStudentPanel = new JPanel();
        editStudentPanel.setLayout(null);
        editStudentPanel.add(inputPanel());
        return editStudentPanel;
    }
    private JScrollPane showDataPanel() {
        // Table name
        // Table
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) {
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

        return scrollPane;
    }

}
