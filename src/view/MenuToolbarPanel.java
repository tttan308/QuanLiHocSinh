package view;
import function.FileHandle;
import function.Student;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static view.StudentTablePanel.stuList;

public class MenuToolbarPanel {
    private JPanel menuToolbar;
    private JTextField idField, nameField, gradeField, addressField, noteField;
    JButton addImageFile, submit;
    private JLabel imageLabel = new JLabel();
    private ImageIcon imageFile = null;
    private ImageIcon imageFileEdit = null;
    public MenuToolbarPanel(){
        menuToolbar = new JPanel();
        menuToolbar.setLayout(new BorderLayout());

        JMenuBar toolbar = new JMenuBar();
        toolbar.setBackground(new Color(0xffffff));
        toolbar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));

        JMenu functionMenu = new JMenu("Chức năng");
        JMenuItem addMenuItem = new JMenuItem("Thêm học sinh");
        JMenuItem editMenuItem = new JMenuItem("Sửa thông tin học sinh");
        JMenuItem deleteMenuItem = new JMenuItem("Xóa học sinh");
        functionMenu.add(addMenuItem);
        functionMenu.addSeparator();
        functionMenu.add(editMenuItem);
        functionMenu.addSeparator();
        functionMenu.add(deleteMenuItem);
        toolbar.add(functionMenu);

        addMenuItem.addActionListener(e -> {
            JFrame frame = new JFrame("Thêm học sinh");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new FlowLayout());
            frame.add(inputNewStudentPanel());
            frame.setSize(700, 200);
            frame.setMinimumSize(new Dimension(700, 220));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        editMenuItem.addActionListener(e -> {
            JFrame frame = new JFrame("Sửa học sinh");
            frame.setLayout(new FlowLayout());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(editStudentPanel());
            frame.setSize(450, 80);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        deleteMenuItem.addActionListener(e -> {
            JFrame frame = new JFrame("Xóa học sinh");
            frame.setLayout(new FlowLayout());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(deleteStudentPanel());
            frame.setSize(450, 80);
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
        //Handle
        openMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn file");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
            fileChooser.setFileFilter(filter);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int userSelection = fileChooser.showOpenDialog(null);
            if(userSelection == JFileChooser.APPROVE_OPTION){
                File fileToOpen = fileChooser.getSelectedFile();
                stuList.clearStudentList();
                try {
                    FileHandle.importCSV(fileToOpen.getAbsolutePath());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "File bị lỗi");
                    throw new RuntimeException(ex);
                }
                stuList = FileHandle.importCSV(fileToOpen.getAbsolutePath());
                StudentTablePanel.reloadTable();
            }

        });
        saveMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Đặt tên file");
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try {
                    FileHandle.exportCSV(fileToSave.getAbsolutePath() + ".csv", stuList);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "File bị lỗi");
                    throw new RuntimeException(ex);
                }
            }
        });

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
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
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
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(null);
        imagePanel.add(imageLabel);
        JButton addImageFile = new JButton("Thêm ảnh");
        imagePanel.add(addImageFile);
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(imagePanel, gbc);

        panel.add(inputPanel, BorderLayout.PAGE_START);

        JPanel submitPanel = new JPanel(new FlowLayout());
        submit = new JButton("Thêm");
        submitPanel.add(submit);
        panel.add(submitPanel, BorderLayout.CENTER);

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
                if(grade < 0 || grade > 10){
                    JOptionPane.showMessageDialog(null, "Điểm phải nằm trong khoảng 0 - 10");
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
                        JOptionPane.showMessageDialog(null, "Thêm học sinh thành công");
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
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
                fileChooser.setFileFilter(filter);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon imageBeside = new ImageIcon(selectedFile.getPath());
                    imageFile = new ImageIcon(selectedFile.getPath());
                    Image img1 = imageBeside.getImage();
                    Image img = imageFile.getImage();
                    Image newImg = img.getScaledInstance(30, 40, Image.SCALE_SMOOTH);
                    Image newImg1 = img1.getScaledInstance(90, 120, Image.SCALE_SMOOTH);
                    imageFile = new ImageIcon(newImg1);
                    imageBeside = new ImageIcon(newImg);
                    addImageFile.setText("Đổi ảnh");
                    imageLabel.setIcon(imageBeside);
                }
            }
        });

        return panel;
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
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                JFrame editFrame = new JFrame("Sửa thông tin học sinh");
                editFrame.setSize(500, 220);
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
                panel.add(editInputPanel, BorderLayout.PAGE_START);

                JPanel buttonPanel = new JPanel(new FlowLayout());
                JButton save = new JButton("Lưu");
                buttonPanel.add(save);
                panel.add(buttonPanel, BorderLayout.CENTER);

                editFrame.add(panel);
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

                    if(grade < 0 || grade > 10){
                        JOptionPane.showMessageDialog(null, "Điểm phải nằm trong khoảng 0 - 10.");
                        return;
                    }

                    if(stuList.checkExists(id1) && !id1.equals(id)){
                        JOptionPane.showMessageDialog(null, "Mã học sinh đã tồn tại.");
                        return;
                    }

                    if(id1.equals("") || name.equals("") || address.equals("") || note.equals("") || gradeField.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
                    }
                    else{
                        stuList.editStudent(id1, name, grade,imageFileEdit, address, note, id);
                        StudentTablePanel.reloadTable();
                        JOptionPane.showMessageDialog(null, "Sửa thông tin học sinh thành công");
                    }

                    editFrame.dispose();
                });

                addImageFile.addActionListener(ex -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Chọn ảnh");
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
                    fileChooser.setFileFilter(filter);
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        ImageIcon iconBesideButton = new ImageIcon(selectedFile.getPath());
                        imageFileEdit = new ImageIcon(selectedFile.getPath());
                        Image img = iconBesideButton.getImage();
                        Image img2 = img.getScaledInstance(90, 120, Image.SCALE_SMOOTH);
                        imageFileEdit = new ImageIcon(img2);
                        Image newImg = img.getScaledInstance(30, 40, Image.SCALE_SMOOTH);
                        iconBesideButton = new ImageIcon(newImg);
                        addImageFile.setText("Đổi ảnh");
                        imageLabel.setIcon(iconBesideButton);
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
