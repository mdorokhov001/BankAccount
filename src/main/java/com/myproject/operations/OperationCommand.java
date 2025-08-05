package com.myproject.operations;

public interface OperationCommand {
    void execute();
    OperationType getOperationType();
}
