package view;
import function.Student;
import function.StudentList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class MenuToolbarPanel {
    private JPanel menuToolbar;
    private JTextField idField, nameField, gradeField, addressField, noteField;
    private ImageIcon imageFile = null;
    private JLabel imageLabel = new JLabel();
    private DefaultTableModel tableModel = StudentTablePanel.getTableModel();
    private StudentList stuList = new StudentList();
    public MenuToolbarPanel(){
        menuToolbar = new JPanel();
        menuToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JMenuBar toolbar = new JMenuBar();
        toolbar.setBackground(Color.WHITE);
        toolbar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JMenu functionMenu = new JMenu("Chức năng");
        JMenuItem addMenuItem = new JMenuItem("Thêm");
        JMenuItem editMenuItem = new JMenuItem("Sửa");
        JMenuItem deleteMenuItem = new JMenuItem("Xóa");
        functionMenu.add(addMenuItem);
        functionMenu.addSeparator();
        functionMenu.add(editMenuItem);
        functionMenu.addSeparator();
        functionMenu.add(deleteMenuItem);
        toolbar.add(functionMenu);

        //Handle open button action.
        //Create a window input dialog when click addMenuItem
        addMenuItem.addActionListener(e -> {
            JFrame frame = new JFrame("Thêm học sinh");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(inputPanel(), BorderLayout.CENTER);
            frame.setSize(700, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });


        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Import File");
        JMenuItem saveMenuItem = new JMenuItem("Export File");
        fileMenu.add(openMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(saveMenuItem);
        toolbar.add(fileMenu);
        toolbar.add(Box.createHorizontalStrut(10));

        JMenu viewMenu = new JMenu("Xem danh sách học sinh");
        JMenuItem idSort1 = new JMenuItem("Sắp xếp theo mã học sinh tăng dần");
        JMenuItem idSort2 = new JMenuItem("Sắp xếp theo mã học sinh giảm dần");
        JMenuItem gradeSort1 = new JMenuItem("Sắp xếp theo điểm tăng dần");
        JMenuItem gradeSort2 = new JMenuItem("Sắp xếp theo điểm giảm dần");
        viewMenu.add(idSort1);
        viewMenu.addSeparator();
        viewMenu.add(idSort2);
        viewMenu.addSeparator();
        viewMenu.add(gradeSort1);
        viewMenu.addSeparator();
        viewMenu.add(gradeSort2);
        toolbar.add(viewMenu);
        menuToolbar.add(toolbar);
    }

    public static JPanel getMenuToolbar(){
        return new MenuToolbarPanel().menuToolbar;
    }

    private JPanel inputPanel(){
        idField = new JTextField(8);
        nameField = new JTextField(20);
        gradeField = new JTextField(5);
        addressField = new JTextField(20);
        noteField = new JTextField(20);

        JButton addImageFile = new JButton("Thêm ảnh");

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idPanel.add(new JLabel("Mã học sinh: "));
        idPanel.add(idField);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(idPanel, gbc);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Tên học sinh: "));
        namePanel.add(nameField);
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(namePanel, gbc);

        JPanel gradePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gradePanel.add(new JLabel("Điểm: "));
        gradePanel.add(gradeField);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(gradePanel, gbc);

        JPanel addressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addressPanel.add(new JLabel("Địa chỉ: "));
        addressPanel.add(addressField);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(addressPanel, gbc);

        JPanel notePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notePanel.add(new JLabel("Ghi chú: "));
        notePanel.add(noteField);
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(notePanel, gbc);

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        imagePanel.add(new JLabel("Ảnh: "));
        imagePanel.add(addImageFile);
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(imagePanel, gbc);

        JPanel submitButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton submit = new JButton("Thêm");
        submitButton.add(submit);
        gbc.gridy = 3;
        inputPanel.add(submitButton, gbc);

        //Handle
        addImageFile.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn ảnh");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imageFile = new ImageIcon(selectedFile.getPath());
                    Image img = imageFile.getImage();
                    Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    imageFile = new ImageIcon(newImg);
                    imageLabel.setIcon(imageFile);
                    addImageFile.setText("Đổi ảnh");
                    // add path below
                    JLabel path = new JLabel(selectedFile.getPath());
                    path.setBounds(100, 100, 200, 20);
                    inputPanel().add(path);
                }
            }
        });

        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                System.out.println(id);
                String name = nameField.getText();
                float grade = 0;
                try {
                    grade = Float.parseFloat(gradeField.getText());
                    System.out.println(grade);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Điểm phải là số");
                }
                String address = addressField.getText();
                String note = noteField.getText();
                if (id.equals("") || name.equals("") || address.equals("") || note.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                } else {
                    stuList.addStudent(id, name, grade, imageFile, note, address);
                    tableModel.addRow(new Object[]{id, name, grade, imageFile, note, address});
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                }
            }
        });
        return inputPanel;
    }
}
