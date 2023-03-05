package function;

import javax.swing.*;
import java.util.ArrayList;

public class StudentList {
    ArrayList<Student> list;

    public StudentList(){
        list = new ArrayList<>();
    }

    private int checkExists(Student stu) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getid().equals(stu.getid())) {
                return i;
            }
        }
        return -1;
    }

    public void addStu(Student stu) {
        if (checkExists(stu) == -1) {
            list.add(stu);
        }
        else {
            System.out.println("Student already exists");
        }
    }

    public void addStudent(String id, String name, float grade, ImageIcon img, String address, String note){
        Student nlist = new Student(id, name, grade, img, address, note);
        addStu(nlist);
    }

    public void removeStudent(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getid().equals(id)) {
                list.remove(i);
            }
        }
    }
}
