package function;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

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
                data[5] = data[5].replace("21120553@tranthaitan.ltudjava", ",");
                byte[] imageByte = Base64.getDecoder().decode(data[5]);
                ImageIcon icon = new ImageIcon(imageByte);
                list.addStudent(data[0], data[1], Float.parseFloat(data[2]), icon, data[3], data[4]);
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
        bw.write(res);
        for(int i = 0; i < list.getSize(); i++){
            Image image = list.getStudent(i).getImg().getImage();
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bufferedImage.createGraphics();
            g2.drawImage(image, null, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //get format file
            String format = list.getStudent(i).getImg().getDescription().split("\\.")[1];
            System.out.println("format: " + format + "");
            ImageIO.write(bufferedImage, format, baos);
            byte[] imageData = baos.toByteArray();
            String imageDataString = Base64.getEncoder().encodeToString(imageData);
            imageDataString = imageDataString.replace(",", "21120553@tranthaitan.ltudjava");
            res = list.getStudent(i).getId() + "," + list.getStudent(i).getName() + "," + list.getStudent(i).getGrade() + "," + list.getStudent(i).getAddress() + "," + list.getStudent(i).getNote() + "," + imageDataString + "\n";
            bw.write(res);
        }
        bw.close();
    }
}
