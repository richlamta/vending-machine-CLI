package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Inventory {
    private static ArrayList<String> inventory = new ArrayList<>();
    private static Map<String, Item> inventoryMap = new TreeMap<>();

    public Inventory() {
        File vendingMachineInventory = new File("vendingmachine.csv");
        try (Scanner scanner = new Scanner(vendingMachineInventory)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                inventory.add(line);
            }
            for (String item : inventory) {
                String[] strSplit = item.split("\\|");
                String itemCode = strSplit[0];
                Item currentItem = new Item(itemCode, strSplit[1], strSplit[2], strSplit[3]);
                inventoryMap.put(itemCode, currentItem);
            }

        } catch (Exception ex) {
            System.out.println("Something went wrong fetching inventory");
        }

    }

    public static void displayInventory() {
        for (Map.Entry<String, Item> item : inventoryMap.entrySet()) {
            System.out.println(item.getValue());
        }
    }

    public static Map<String, Item> getInventoryMap() {
        return inventoryMap;
    }

    public void getSalesReport() {
        //sales report must include date/time
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH_mm_ss");
        Date date = new Date();
        File salesReportFile = new File("C:\\Users\\Student\\workspace\\nlr-14-module-1-capstone-team-3\\src\\main\\Reports\\" + formatter.format(date) + "_Sales_Report.txt");
        double totalSalesCounter = 0;

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(salesReportFile, true))) {
            for (Map.Entry<String, Item> item : inventoryMap.entrySet()) {
                Item currentString = item.getValue();
                totalSalesCounter += currentString.getPrice() * (5 - (currentString.getItemInventory()));
                writer.println(currentString.getName() + " | " + (5 - (currentString.getItemInventory())));
            }
            writer.println("**TOTAL SALES** $" + totalSalesCounter);
        } catch (FileNotFoundException e) {
            System.out.println("PROBLEM WITH SALES REPORT");
        }
        System.out.println("**TOTAL SALES** $" + totalSalesCounter);
    }
}