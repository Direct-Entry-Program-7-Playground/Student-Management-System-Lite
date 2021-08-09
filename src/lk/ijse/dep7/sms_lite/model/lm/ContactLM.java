/*
 *
 *  * Copyright (c) 2021 Manoj Randeni. All rights reserved.
 *  * Licensed under the Apache license. See License.txt in the project root for license information
 *
 */

package lk.ijse.dep7.sms_lite.model.lm;

public class ContactLM {

    private String contact;
    private int providerID;
    private String providerDesc;

    public ContactLM() {
    }

    public ContactLM(String contact, int providerID, String providerDesc) {
        this.setContact(contact);
        this.setProviderID(providerID);
        this.setProviderDesc(providerDesc);
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getProviderID() {
        return providerID;
    }

    public void setProviderID(int providerID) {
        this.providerID = providerID;
    }

    public String getProviderDesc() {
        return providerDesc;
    }

    public void setProviderDesc(String providerDesc) {
        this.providerDesc = providerDesc;
    }
}
