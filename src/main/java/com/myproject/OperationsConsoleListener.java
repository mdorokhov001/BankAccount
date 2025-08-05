package com.myproject;

import com.myproject.operations.OperationCommand;
import com.myproject.operations.OperationType;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Scanner;
@Service
public class OperationsConsoleListener  {

    private final Scanner scanner;
    private final Map<OperationType, OperationCommand> operationCommandMap;

    public OperationsConsoleListener(Scanner scanner, Map<OperationType, OperationCommand> operationCommandMap){
        this.scanner = scanner;
        this.operationCommandMap = operationCommandMap;
    }

    public void start(){
        System.out.println("App is started.");
    }

    public void startListen(){
        while (true) {
            System.out.println("\nPlease enter one of operation type:");
            OperationType operationType = listenNextOperation();
            processOperation(operationType);
        }
    }

    private OperationType listenNextOperation(){
        String inputOperation = "";
        printAllOperations();
        while(true){
            try {
                inputOperation = scanner.nextLine();
                return OperationType.valueOf(inputOperation);
            } catch (IllegalArgumentException e){
                System.out.printf("Cannot find command %s, please, try again: \n", inputOperation);
            }
        }

    }

    private void printAllOperations(){
        operationCommandMap.keySet().forEach(System.out::println);
        System.out.println();
    }

    private void processOperation(OperationType operationType){
        try {
            OperationCommand operationCommand = operationCommandMap.get(operationType);
            operationCommand.execute();
        } catch (Exception e){
            System.out.printf("Error executing command %s: ", e.getMessage());
        }
    }

    public void end(){
        System.out.println("App is ended.");
    }
}
