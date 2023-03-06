package function;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class StudentList {
    ArrayList<Student> list;

    public StudentList(){
        list = new ArrayList<>();
    }

    private int checkExists(Student stu) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(stu.getId())) {
                return i;
            }
        }
        return -1;
    }

    public void addStudent(Student stu) {
        if (checkExists(stu) == -1) {
            list.add(stu);
        }
        else {
            System.out.println("Student already exists");
        }
    }

    public void addStudent(String id, String name, float grade, ImageIcon img, String address, String note){
        if(checkExists(new Student(id, name, grade, img, address, note)) != -1){
            throw new IllegalArgumentException("Student already exists");
        }
        list.add(new Student(id, name, grade, img, address, note));
    }

    public void editStudent(String id, String name, float grade, ImageIcon img, String address, String note){
        int index = checkExists(new Student(id, name, grade, img, address, note));
        if(index != -1){
            list.get(index).setName(name);
            list.get(index).setGrade(grade);
            list.get(index).setImg(img);
            list.get(index).setAddress(address);
            list.get(index).setNote(note);
        }
        else{
            throw new IllegalArgumentException("Student does not exist");
        }
    }
    public void deleteStudent(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                list.remove(i);
            }
        }
    }

    public Student getStudent(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                return list.get(i);
            }
        }
        return null;
    }

    public void sortById(boolean increase){
        for(int i = 0; i < list.size() - 1; i++){
            for(int j = i + 1; j < list.size(); j++){
                if(increase){
                    if(list.get(i).getId().compareTo(list.get(j).getId()) > 0){
                        Student temp = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, temp);
                    }
                }
                else{
                    if(list.get(i).getId().compareTo(list.get(j).getId()) < 0){
                        Student temp = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, temp);
                    }
                }
            }
        }
    }


    public Student[] getStudentList() {
        Student[] stuList = new Student[list.size()];
        for (int i = 0; i < list.size(); i++) {
            stuList[i] = list.get(i);
        }
        return stuList;
    }

}
