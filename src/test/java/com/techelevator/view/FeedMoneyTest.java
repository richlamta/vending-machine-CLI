package com.techelevator.view;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

public class FeedMoneyTest  {
Transaction transaction;
    ByteArrayOutputStream output;
    @Before
        public void setup () {
        transaction = new Transaction();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }
    @Test
    public void test_feed_money_5() {
        transaction.setBalance("5");
        int expected = 5;
        Assert.assertEquals((int)expected, (int)transaction.getBalance());
    }

    @Test
    public void test_feed_money_non_valid_bill() {
        transaction.setBalance("3");
        String expected = "PLEASE ENTER A VALID BILL";
        Assert.assertEquals(expected, output.toString().trim());
    }
    @Test
    public void test_feed_money_non_number() {
        transaction.setBalance("a");
        String expected = "PLEASE ENTER A VALID BILL";
        Assert.assertEquals(expected, output.toString().trim());
    }

    @Test
    public void test_feed_money_20_bill() {
        //Arrange
        String money = "20";
        transaction.setBalance(money);
        //Act
        String expected = "PLEASE ENTER A VALID BILL";
        //Assert
        Assert.assertEquals(expected, output.toString().trim());
    }
    @After
    public void ending() {
        transaction.getChange();
    }

}
