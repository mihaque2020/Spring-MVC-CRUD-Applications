/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Minul
 */
public class Item {
    
    private String itemName;
    private BigDecimal itemPrice;
    private int itemStock;
    
    public Item(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemStock() {
        return itemStock;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.itemName);
        hash = 79 * hash + Objects.hashCode(this.itemPrice);
        hash = 79 * hash + this.itemStock;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.itemStock != other.itemStock) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemStock=" + itemStock + '}';
    }
    
}
