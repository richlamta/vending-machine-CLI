package com.techelevator.view;

public class Item {

    private String code;
    private String name;
    private double price;
    private String category;
    private int itemInventory;


    public Item(String inputCode,String name, String price, String category) {
                this.code = inputCode;
                this.name = name;
                //this.price = Double.parseDouble(price);
                this.price = Double.parseDouble(price);
                this.category = category;
                this.itemInventory = 5;
    }



    //Getters and Setters

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory() {
        if (itemInventory > 0) {
            itemInventory -=1;
        }
    }
    public void getMessage() {
        if (category.equals("Chip")) {
            System.out.println("Crunch Crunch, Yum!");
        }
        if (category.equals("Candy")) {
            System.out.println("Munch Munch, Yum!");
        }
        if (category.equals("Drink")) {
            System.out.println("Glug Glug, Yum!");
        }
        if (category.equals("Gum")) {
            System.out.println("Chew Chew, Yum!");
        }
    }


    @Override
    public String toString() {
        return code + " | " + name + " | Price: $" + price + " | Quantity: " + itemInventory;
    }

}
