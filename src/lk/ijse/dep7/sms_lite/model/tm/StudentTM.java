/*
 *
 *  * Copyright (c) 2021 Manoj Randeni. All rights reserved.
 *  * Licensed under the Apache license. See License.txt in the project root for license information
 *
 */

package lk.ijse.dep7.sms_lite.model.tm;

import java.util.ArrayList;
import java.util.List;

public class StudentTM {

    private String studentID;
    private String name;
    private List<String> contacts = new ArrayList<>();

    public StudentTM(String studentID, String name, List<String> contacts) {
        this.setStudentID(studentID);
        this.setName(name);
        this.setContacts(contacts);
    }

    public StudentTM() {
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

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }
}
