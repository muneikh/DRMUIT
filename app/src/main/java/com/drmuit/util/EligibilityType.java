package com.drmuit.util;

public enum EligibilityType {
    CUSTOMER_ELIGIBLE(1), CUSTOMER_INELIGIBLE(2), TECHNICAL_FAILURE(3), INVALID_ACCOUNT(4);

    private int code;

    private EligibilityType(int c) {
        code = c;
    }

    public int getCode() {
        return code;
    }
}
