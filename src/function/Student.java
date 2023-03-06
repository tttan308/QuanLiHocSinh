package function;

import javax.swing.*;

public class Student {
    private String id;
    private String name;
    private float grade;
    private ImageIcon img;
    private String address;
    private String note;

    public Student(){
        this.id = "";
        this.name = "";
        this.grade = 0;
        this.img = null;
        this.address = "";
        this.note = "";
    }

    public Student(String _MSH, String _name, float _grade, ImageIcon _img, String _address, String _note){
        this.id = _MSH;
        this.name = _name;
        this.grade = _grade;
        this.img = _img;
        this.address = _address;
        this.note = _note;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public float getGrade(){
        return this.grade;
    }

    public ImageIcon getImg(){
        return this.img;
    }
    public String getAddress() {
        return this.address;
    }

    public String getNote() {
        return this.note;
    }

    public void setId(String _id){
        this.id = _id;
    }

    public void setName(String _name){
        this.name = _name;
    }

    public void setGrade(float _grade){
        this.grade = _grade;
    }

    public void setImg(ImageIcon _img){
        this.img = _img;
    }

    public void setAddress(String _address){
        this.address = _address;
    }

    public void setNote(String _note){
        this.note = _note;
    }
}
