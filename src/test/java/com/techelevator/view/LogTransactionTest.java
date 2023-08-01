package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

public class LogTransactionTest {
    Transaction transaction;

    @Before
    public void setup() {
        transaction = new Transaction();
    }

    @Test
    public void log_transaction_functionality_test() {
        boolean result = false;
        boolean expected = true;
        transaction.logTransaction("TEST", 5.00);
        File file = new File("C:\\Users\\Student\\workspace\\nlr-14-module-1-capstone-team-3\\log.txt");

        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine.contains("TEST")) {
                    result = true;
                }
            }
        } catch (Exception ex) {
            System.out.println("log_transaction_functionality_test failed");
        }

        Assert.assertEquals(result, expected);
    }
}
