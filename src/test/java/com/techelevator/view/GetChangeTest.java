package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

public class GetChangeTest {
    Transaction transaction;
    Inventory inventory;
    ByteArrayOutputStream output;
    @Before
    public void setup () {
        inventory = new Inventory();
        transaction = new Transaction();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    public void get_change_test() {
        transaction.setBalance("5");
        //Map<String, Item> inventory = Inventory.getInventoryMap();
        transaction.dispenseItem("B4", 5.00);

        transaction.getChange();
        String result = "B4 | Crunchie | Price: $1.75 | Quantity: 4" + System.lineSeparator() +
                "Munch Munch, Yum!" + System.lineSeparator() +
                "CHANGE: $3.25" + System.lineSeparator() +
                "Here is your change: 13 quarters , 0 dimes, 0 nickels";

        Assert.assertEquals(result.trim(), output.toString().trim());
    }

    @Test
    public void get_change_no_balance_test() {

        transaction.getChange();
        String result =
                "CHANGE: $0.0" + System.lineSeparator() +
                "Here is your change: 0 quarters , 0 dimes, 0 nickels";

        Assert.assertEquals(result.trim(), output.toString().trim());
    }
}
