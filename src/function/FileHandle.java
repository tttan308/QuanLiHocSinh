package function;

import javax.swing.*;
import java.io.*;

public class FileHandle {
    public static StudentList importCSV(String fileName){
        StudentList list = new StudentList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
            //Đọc bỏ dòng đầu tiên
            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                //CASTING string to imageicon
                ImageIcon img = new ImageIcon(data[5]);
                list.addStudent(data[0], data[1], Float.parseFloat(data[2]), img, data[3], data[4]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void exportCSV(String path, StudentList list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));
        String res = "Mã số sinh viên,Tên sinh viên,Điểm trung bình,Địa chỉ,Thông tin thêm,Hình ảnh \n";
        for(int i = 0; i < list.getSize(); i++) {
            res += list.getStudent(i).getId() + "," + list.getStudent(i).getName() + "," + list.getStudent(i).getGrade() + "," + list.getStudent(i).getAddress() + "," + list.getStudent(i).getNote() + "," + list.getStudent(i).getImg() + "\n";
        }
        bw.write(res);
        bw.close();
    }
}
