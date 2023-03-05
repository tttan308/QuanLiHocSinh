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

    public String getid(){
        return this.id;
    }

    public String getname(){
        return this.name;
    }

    public float getgrade(){
        return this.grade;
    }

    public ImageIcon getimg(){
        return this.img;
    }
    public String getaddress() {
        return this.address;
    }

    public String getnote() {
        return this.note;
    }
}
