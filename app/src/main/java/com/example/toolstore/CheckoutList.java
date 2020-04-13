package com.example.toolstore;

public class CheckoutList {
    private String itemName;
    private String itemMaker;
    private String itemSize;
    private int itemAmount;
    private int itemPrice;
    private String checkoutState;
    private boolean isChecked;

    public CheckoutList() {
    }

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

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getCheckoutState() {
        return checkoutState;
    }

    public void setCheckoutState(String checkoutState) {
        this.checkoutState = checkoutState;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
