package com.techelevator.view;

import java.util.Scanner;

public class PurchaseMenu {
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu(System.in, System.out);
    Transaction transaction = new Transaction();
    Inventory inventory = new Inventory();
    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    public void runPurchaseMenu() {
        while(true) {
            transaction.displayBalance();
            String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
            if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                System.out.println("Input $(1), $(5), or $(10) bill please:");
                String moneyInput = scanner.nextLine();
                transaction.setBalance(moneyInput);
                //transaction.displayBalance();
            } else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

                Inventory.displayInventory();
                System.out.println("Please enter item code: ");
                String userInput = scanner.nextLine();
                transaction.dispenseItem(userInput, transaction.getBalance());

            } else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
                transaction.getChange();
                break;
            }
        }
    }

}
