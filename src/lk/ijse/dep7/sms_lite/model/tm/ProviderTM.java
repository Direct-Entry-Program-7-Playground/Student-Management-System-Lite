/*
 *
 *  * Copyright (c) 2021 Manoj Randeni. All rights reserved.
 *  * Licensed under the Apache license. See License.txt in the project root for license information
 *
 */

package lk.ijse.dep7.sms_lite.model.tm;

import java.util.ArrayList;
import java.util.List;

public class ProviderTM {
    private int providerId;
    private String name;
    private List<String> operatorCodes = new ArrayList<>();

    public ProviderTM(int providerId, String name, List<String> operatorCodes) {
        this.setProviderId(providerId);
        this.setName(name);
        this.setOperatorCodes(operatorCodes);
    }

    public ProviderTM() {
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOperatorCodes() {
        return operatorCodes;
    }

    public void setOperatorCodes(List<String> operatorCodes) {
        this.operatorCodes = operatorCodes;
    }
}
