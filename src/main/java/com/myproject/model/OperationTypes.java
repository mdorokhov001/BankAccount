package com.myproject.model;

import java.util.Arrays;
import java.util.List;

public enum OperationTypes {
    USER_CREATE ("USER_CREATE"),
    SHOW_ALL_USERS ("SHOW_ALL_USERS"),
    ACCOUNT_CREATE ("ACCOUNT_CREATE"),
    ACCOUNT_CLOSE ("ACCOUNT_CLOSE"),
    ACCOUNT_DEPOSIT ("ACCOUNT_DEPOSIT"),
    ACCOUNT_TRANSFER ("ACCOUNT_TRANSFER"),
    ACCOUNT_WITHDRAW ("ACCOUNT_WITHDRAW"),
    UNKNOWN("");

    private final String operation;

    OperationTypes(String oparation){
        this.operation = oparation;
    }

    public String getOperation(){
        return operation;
    }

    public static OperationTypes fromString(String str){
        for (OperationTypes opt : OperationTypes.values()) {
            if (opt.operation.equalsIgnoreCase(str)) {
                return opt;
            }
        }
        return UNKNOWN;
    }

    public static List<String> getAllOperations() {
        return Arrays.stream(values())
                .filter(cmd -> cmd != UNKNOWN)
                .map(OperationTypes::getOperation)
                .toList();
    }
}
