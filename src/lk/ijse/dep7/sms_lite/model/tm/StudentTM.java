package lk.ijse.dep7.sms_lite.model.tm;

import javafx.scene.control.Button;

public class StudentTM {

    private String studentID;
    private String name;
    private String phoneNumbers;

    public StudentTM() {
    }

    public StudentTM(String studentID, String name, String[] phoneNumbers) {
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

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String[] phoneNumbers) {

        StringBuilder sb = new StringBuilder();
        int phoneNumberCount = phoneNumbers.length;
        for (int i = 0; i < phoneNumberCount; i++) {
            sb.append(phoneNumbers[i]).append((i != phoneNumberCount - 1) ? ", " : "");
        }

        this.phoneNumbers = sb.toString();
    }

}
