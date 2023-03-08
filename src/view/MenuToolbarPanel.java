package view;
import function.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

import static view.StudentTablePanel.stuList;

public class MenuToolbarPanel {
    private JPanel menuToolbar;
    private JTextField idField, nameField, gradeField, addressField, noteField;
    JButton addImageFile, submit;
    private Student student;
    private JLabel imageLabel = new JLabel();
    private ImageIcon imageFile = null;
    private ImageIcon imageFileEdit = null;
    public MenuToolbarPanel(){
        menuToolbar = new JPanel();
        menuToolbar.setLayout(new BorderLayout());
        JMenuBar toolbar = new JMenuBar();

        toolbar.setBackground(new Color(224, 224, 224));
        toolbar.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 20));

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

        //Handle open button action
        //Create a window input dialog when click addMenuItem
        addMenuItem.addActionListener(e -> {
            JFrame frame = new JFrame("Thêm học sinh");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(inputNewStudentPanel(), BorderLayout.CENTER);
            frame.setSize(700, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        editMenuItem.addActionListener(e -> {
            JFrame frame = new JFrame("Sửa học sinh");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(editStudentPanel(), BorderLayout.CENTER);
            frame.setSize(500, 80);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        deleteMenuItem.addActionListener(e -> {
            JFrame frame = new JFrame("Xóa học sinh");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(deleteStudentPanel(), BorderLayout.CENTER);
            frame.setSize(500, 80);
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
        menuToolbar.add(toolbar, BorderLayout.NORTH);

        //Handle
        idSort1.addActionListener(e -> {
            stuList.sortById(true);
            StudentTablePanel.reloadTable();
        });

        idSort2.addActionListener(e -> {
            stuList.sortById(false);
            StudentTablePanel.reloadTable();
        });

        gradeSort1.addActionListener(e -> {
            stuList.sortByGrade(true);
            StudentTablePanel.reloadTable();
        });

        gradeSort2.addActionListener(e -> {
            stuList.sortByGrade(false);
            StudentTablePanel.reloadTable();
        });
    }

    public static JPanel getMenuToolbar(){
        return new MenuToolbarPanel().menuToolbar;
    }

    private JPanel inputNewStudentPanel(){
        idField = new JTextField(8);
        nameField = new JTextField(20);
        gradeField = new JTextField(5);
        addressField = new JTextField(20);
        noteField = new JTextField(20);

        addImageFile = new JButton("Thêm ảnh");

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
        submit = new JButton("Thêm");
        submitButton.add(submit);
        gbc.gridy = 3;
        inputPanel.add(submitButton, gbc);

        //Handle

        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                float grade = 0;
                try{
                    grade = Float.parseFloat(gradeField.getText());
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Điểm phải là số.");
                    return;
                }
                String address = addressField.getText();
                String note = noteField.getText();
                if(id.equals("") || name.equals("") || gradeField.getText().equals("") || address.equals("") || note.equals("") || imageFile.equals(null))
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                else
                    try{
                        stuList.addStudent(id, name, grade, imageFile, address, note);
                        stuList.printList();
                        StudentTablePanel.reloadTable();
                        JOptionPane.showMessageDialog(null, "Thêm thành công");
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Mã học sinh đã tồn tại");
                    }
            }
        });

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
                }
            }
        });

        return inputPanel;
    }

    private JPanel editStudentPanel(){
        JPanel editPanel = new JPanel();
        JLabel editLabel = new JLabel("Nhập mã học sinh cần sửa: ");
        JTextField editField = new JTextField(8);
        JButton edit = new JButton("Sửa");

        editPanel.add(editLabel);
        editPanel.add(editField);
        editPanel.add(edit);

        edit.addActionListener(e -> {
            String id = editField.getText();
            if(id.equals("")){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã học sinh");
            }
            else if(stuList.checkExists(id) == false){
                JOptionPane.showMessageDialog(null, "Mã học sinh không tồn tại");
            }
            else{
                Student student = stuList.getStudent(id);
                imageFileEdit = imageFile;
                JFrame editFrame = new JFrame("Sửa thông tin học sinh");
                editFrame.setSize(500, 300);
                editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                editFrame.setLocationRelativeTo(null);
                editFrame.setVisible(true);
                editFrame.setLayout(new BorderLayout());
                JPanel editInputPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                idPanel.add(new JLabel("Mã học sinh: "));
                JTextField idField = new JTextField(8);
                idField.setText(student.getId());
                idPanel.add(idField);
                gbc.gridx = 0;
                gbc.gridy = 0;
                editInputPanel.add(idPanel, gbc);

                JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                namePanel.add(new JLabel("Tên học sinh: "));
                JTextField nameField = new JTextField(8);
                nameField.setText(student.getName());
                namePanel.add(nameField);
                gbc.gridx = 1;
                gbc.gridy = 0;
                editInputPanel.add(namePanel, gbc);

                JPanel gradePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                gradePanel.add(new JLabel("Điểm: "));
                JTextField gradeField = new JTextField(8);
                gradeField.setText(Float.toString(student.getGrade()));
                gradePanel.add(gradeField);
                gbc.gridx = 0;
                gbc.gridy = 1;
                editInputPanel.add(gradePanel, gbc);

                JPanel addressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                addressPanel.add(new JLabel("Địa chỉ: "));
                JTextField addressField = new JTextField(8);
                addressField.setText(student.getAddress());
                addressPanel.add(addressField);
                gbc.gridx = 1;
                gbc.gridy = 1;
                editInputPanel.add(addressPanel, gbc);

                JPanel notePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                notePanel.add(new JLabel("Ghi chú: "));
                JTextField noteField = new JTextField(8);
                noteField.setText(student.getNote());
                notePanel.add(noteField);
                gbc.gridx = 0;
                gbc.gridy = 2;
                editInputPanel.add(notePanel, gbc);

                JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                imagePanel.add(new JLabel("Ảnh: "));
                JLabel imageLabel = new JLabel();
                imageLabel.setIcon(null);
                imagePanel.add(imageLabel);
                JButton addImageFile = new JButton("Đổi ảnh");
                imagePanel.add(addImageFile);
                gbc.gridx = 1;
                gbc.gridy = 2;
                editInputPanel.add(imagePanel, gbc);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JButton save = new JButton("Lưu");
                buttonPanel.add(save);
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 2;
                editInputPanel.add(buttonPanel, gbc);

                editFrame.add(editInputPanel, BorderLayout.CENTER);
                save.addActionListener(ae -> {
                    String id1 = idField.getText();
                    String name = nameField.getText();
                    String address = addressField.getText();
                    String note = noteField.getText();
                    float grade = 0;
                    try{
                        grade = Float.parseFloat(gradeField.getText());
                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Điểm phải là số thực.");
                        return;
                    }

                    if(id1.equals("") || name.equals("") || address.equals("") || note.equals("") || gradeField.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
                    }
                    else{
                        stuList.editStudent(id1, name, grade,imageFileEdit, address, note, id);
                        StudentTablePanel.reloadTable();
                    }

                    editFrame.dispose();
                });

                addImageFile.addActionListener(ex -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Chọn ảnh");
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        imageFileEdit = new ImageIcon(selectedFile.getPath());
                        Image img = imageFileEdit.getImage();
                        Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        imageFileEdit = new ImageIcon(newImg);
                        imageLabel.setIcon(imageFileEdit);
                        addImageFile.setText("Đổi ảnh");
                    }
                });
            }

        });
        return editPanel;
    }

    private JPanel deleteStudentPanel(){
        JPanel deletePanel = new JPanel();
        JLabel deleteLabel = new JLabel("Nhập mã học sinh cần xóa: ");
        JTextField deleteField = new JTextField(8);
        JButton delete = new JButton("Xóa");
        deletePanel.add(deleteLabel);
        deletePanel.add(deleteField);
        deletePanel.add(delete);

        delete.addActionListener(e -> {
            String id = deleteField.getText();
            if(id.equals(""))
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã học sinh");
            else
                try{
                    stuList.deleteStudent(id);
                    StudentTablePanel.reloadTable();
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Mã học sinh không tồn tại");
                }
        });
        return deletePanel;
    }
}
