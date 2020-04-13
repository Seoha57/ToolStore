package com.example.toolstore;

public class CheckoutList_admin {
    private String userID, itemName, itemMaker, itemSize, checkoutState, checkoutDate;
    private int itemAmount, itemPrice;
    private boolean isChecked;

    CheckoutList_admin() {}

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

    public String getCheckoutState() {
        return checkoutState;
    }

    public void setCheckoutState(String checkoutState) {
        this.checkoutState = checkoutState;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
