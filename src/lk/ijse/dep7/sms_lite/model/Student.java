package lk.ijse.dep7.sms_lite.model;

public class Student {

    private String studentID;
    private String name;
    private String[] phoneNumbers;

    public Student() {
    }

    public Student(String studentID, String name, String[] phoneNumbers) {
        this.setStudentID(studentID);
        this.setName(name);
        this.setPhoneNumbers(phoneNumbers);
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
