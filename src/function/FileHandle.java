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
            br.readLine();

            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                ImageIcon icon = null;
                try{
                    data[5] = data[5].replace("21120553@tranthaitan.ltudjava", ",");
                    data[5] = data[5].replace("21120553@tranthaitan.ltudjava.k21", "\r");
                    data[5] = data[5].replace("21120553@tranthaitan.ltudjava.k21.2023", "\n");
                    byte[] imageByte = Base64.getDecoder().decode(data[5]);
                    icon = new ImageIcon(imageByte);
                } catch (Exception e){
                    e.printStackTrace();
                }
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
        String res = "Mã số sinh viên,Tên sinh viên,Điểm trung bình,Địa chỉ,Thông tin thêm,Hình ảnh\n";
        bw.write(res);
        for(int i = 0; i < list.getSize(); i++){
            String imageDataString = "";
            try{
                Image image = list.getStudent(i).getImg().getImage();
                BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = bufferedImage.createGraphics();
                g2.drawImage(image, null, null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpeg", baos);
                byte[] imageData = baos.toByteArray();
                imageDataString = Base64.getEncoder().encodeToString(imageData);
                imageDataString = imageDataString.replace(",", "21120553@tranthaitan.ltudjava");
                imageDataString = imageDataString.replace("\n", "21120553@tranthaitan.ltudjava.k21.2023");
                imageDataString = imageDataString.replace("\r", "21120553@tranthaitan.ltudjava.k21");
            } catch (Exception e){
                e.printStackTrace();
            }
            res = list.getStudent(i).getId() + "," + list.getStudent(i).getName() + "," + list.getStudent(i).getGrade() + "," + list.getStudent(i).getAddress() + "," + list.getStudent(i).getNote() + "," + imageDataString + "\n";
            bw.write(res);
        }
        bw.close();
    }
}
