package com.example.toolstore;

public class Items {
    private String itemName;
    private String itemMaker;
    private String itemSize;
    private String itemCategory;
    private int itemAmount;
    private int itemPrice;
    private boolean isChecked;

    public Items() {
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
    isChecked = checked;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
}
