package function;

import javax.swing.*;
import java.util.ArrayList;

public class StudentList {
    ArrayList<Student> list;

    public StudentList(){
        list = new ArrayList<>();
    }

    public int checkExists(Student stu) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(stu.getId())) {
                return i;
            }
        }
        return -1;
    }

    public boolean checkExists(String id){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public void addStudent(String id, String name, float grade, ImageIcon img, String address, String note){
        if(checkExists(new Student(id, name, grade, img, address, note)) != -1){
            throw new IllegalArgumentException("Học sinh đã tồn tại");
        }
        list.add(new Student(id, name, grade, img, address, note));
    }

    public void editStudent(String id, String name, float grade, ImageIcon img, String address, String note, String idBefore){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId().equals(idBefore)){
                list.get(i).setId(id);
                list.get(i).setName(name);
                list.get(i).setGrade(grade);
                list.get(i).setImg(img);
                list.get(i).setAddress(address);
                list.get(i).setNote(note);
            }
        }
    }
    public void deleteStudent(String id) {
        if(checkExists(id) != true){
            throw new IllegalArgumentException("Học sinh đã tồn tại");
        }
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

    public void sortByGrade(boolean increase){
        for(int i = 0; i < list.size() - 1; i++){
            for(int j = i + 1; j < list.size(); j++){
                if(increase){
                    if(list.get(i).getGrade() > list.get(j).getGrade()){
                        Student temp = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, temp);
                    }
                }
                else{
                    if(list.get(i).getGrade() < list.get(j).getGrade()){
                        Student temp = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, temp);
                    }
                }
            }
        }
    }

    public int getSize(){
        return list.size();
    }

    public Student getStudent(int index){
        return list.get(index);
    }

    public void printList(){
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getId() + " " + list.get(i).getName() + " " + list.get(i).getGrade() + " " + list.get(i).getAddress() + " " + list.get(i).getNote() + " " + list.get(i).getImg());
        }
    }

    public void clearStudentList(){
        list.clear();
    }
}
