package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

public class InventoryTest {
    Inventory inventory;
    Transaction transaction;
    ByteArrayOutputStream output;

    @Before
    public void setup() {
        inventory = new Inventory();
        transaction = new Transaction();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    public void test_populating_inventory() {
        //Arrange
        Map<String, Item> inventory = Inventory.getInventoryMap();

        //Act
        int expected = 16;
        int result = inventory.size();
        //Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void test_dispense_existing_item() {
        //Arrange
        String code = "B4";
        double balance = 5.00;
        Map<String, Item> inventory = Inventory.getInventoryMap();
        transaction.dispenseItem(code, balance);

        //Act
        int expected = 4;
        int result = inventory.get("B4").getItemInventory();
        //Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void test_dispense_lowercase_item() {
        //Arrange
        String code = "a1";
        double balance = 5.00;
        Map<String, Item> inventory = Inventory.getInventoryMap();
        transaction.dispenseItem(code, balance);

        //Act
        int expected = 4;
        int result = inventory.get("A1").getItemInventory();
        //Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void test_dispense_nonexisting_item() {
        //Arrange
        String code = "candy cane";
        double balance = 5.00;
        transaction.dispenseItem(code, balance);

        //Act
        String expected = "INVALID CODE, PLEASE TRY AGAIN";
        //Assert
        Assert.assertEquals(expected, output.toString().trim());
    }

    @Test
    public void test_dispense_no_money() {
        //Arrange
        String code = "B4";
        double balance = 0.00;
        transaction.dispenseItem(code, balance);

        //Act
        String expected = "PLEASE INSERT MORE MONEY";
        //Assert
        Assert.assertEquals(expected, output.toString().trim());
    }

}
