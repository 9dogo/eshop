package eshop.model;

public class Person {

    protected String name;
    protected String mail;
    protected String tel;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, String mail, String tel) {
        this.name = name;
        this.mail = mail;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
}