package com.example.toolstore;

public class ItemsInCart {
    private String itemName;
    private String itemMaker;
    private String itemSize;
    private int amount;
    private int totalPrice;
    private boolean isChecked;

    public ItemsInCart() {}


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemMaker() {
        return itemMaker;
    }

    public void setItemMaker(String itemMaker) {
        this.itemMaker = itemMaker;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
