/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.ChangeImpl.Change;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Minul
 */
public class VendingMachineView {
    
    private UserIO io;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public int printInventoryGetSelection(List<Item> inventory) {
        for (Item item : inventory) {
            if (item.getItemStock() == 0) {
                String inventoryInfo = String.format(
                        "%s ................................ Out of Stock",
                        item.getItemName());
                io.print(inventoryInfo);
            } else {
                String inventoryInfo = String.format(
                    "%s " + "................................ $%s",
                        item.getItemName(), item.getItemPrice().toString());
                io.print(inventoryInfo);
            }
        }
        return io.readInt("1. To ENTER money. \n2. To EXIT", 1, 2);
    }
    
    public void displayChange(Change change) {
        io.print("Your " + change);
    }
    
    public BigDecimal getMoney() {
       return io.readBigDecimal("Enter money: ", BigDecimal.ZERO, BigDecimal.TEN);
    }
    
    public String getItemSelection() {
        return io.readString("What item would you like to purchase? ");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void displayExitMessage() {
        io.print("Exiting....");
    }
    
    public void displayUnknownCommand(){
        io.print("*Unknown Command*");
    }
    
    public void displayVending(String itemName) {
        io.print("Vending " + itemName + "...");
    }
}
