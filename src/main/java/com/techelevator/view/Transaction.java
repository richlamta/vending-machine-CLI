package com.techelevator.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Transaction {
    private double balance = 0;

    public void setBalance(String moneyReceived) {
        try {
            int money = Integer.parseInt(moneyReceived);
            //Added if money added is not $1/$5/$10
            if (money == 1 || money == 5 || money == 10) {
                balance += money;
                logTransaction("FEED MONEY", money);
            } else {
                System.out.println("PLEASE ENTER A VALID BILL");
            }
        } catch (Exception ex) {
            System.out.println("PLEASE ENTER A VALID BILL");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void displayBalance() {
        System.out.println("Current Money Provided: $" + balance);
    }

    public void getChange() {
        double changeDue = balance;
        //counters for coins
        int quarter = 0;
        int dime = 0;
        int nickel = 0;
        System.out.println("CHANGE: $" +balance);
        if (balance / 25 > 0) {
            quarter = (int) (balance*100 / 25);
            balance = (int) (balance*100 % 25);
        }
        if (balance / 10 > 0) {
            dime = (int) (balance / 10);
            balance = (int) (balance % 10);
        }
        if (balance / 5 > 0) {
            nickel = (int) (balance / 5);
            balance = (int) (balance % 5);
        }
        logTransaction("GIVE CHANGE", changeDue);
        System.out.println("Here is your change: " + quarter + " quarters , " + dime + " dimes, " + nickel + " nickels");
    }


    public void dispenseItem(String code, double balance) {
        String upperCode = code.toUpperCase();
        Map<String, Item> inventory = Inventory.getInventoryMap();
        if (inventory.containsKey(upperCode)) {
            Item currentItem = inventory.get(upperCode);
            if (currentItem.getItemInventory() > 0) {
                if (balance >= currentItem.getPrice()) {
                    this.balance -= currentItem.getPrice();
                    currentItem.setItemInventory();
                    logTransaction(currentItem.getName(), currentItem.getPrice());
                    System.out.println(currentItem);
                    currentItem.getMessage();
                } else {
                    System.out.println("PLEASE INSERT MORE MONEY");
                }
            } else {
                System.out.println("OUT OF STOCK");
            }
        } else {
            System.out.println("INVALID CODE, PLEASE TRY AGAIN");
        }
    }

    public void logTransaction(String action, double dollar) {

        File logFile = new File("log.txt");

        try(PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            writer.println(formatter.format(date) + " " + action + ": $" + dollar + " $" + balance);

        } catch (Exception ex) {
            System.out.println("Problem logging Transaction");
        }

    }
}