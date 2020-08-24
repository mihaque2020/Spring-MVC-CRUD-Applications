/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author Minul
 */
public interface VendingMachineDao {

    /**
     * Returns a List of all items in the vending machine.
     *
     * @return List of all items in the vending machine.
     */
    List<Item> getAllItems() throws VendingMachinePersistenceException;

    /**
     * Returns the Item object associated with the given itemName. Returns null
     * if no such item exists
     *
     * @param itemName of the item to retrieve
     * @return the Item object associated with the given itemNAme null if no
     * such item exists
     */
    Item getItem(String itemName) throws VendingMachinePersistenceException;

    /**
     * adds given Item to the inventory, associated by its name
     *
     * @param itemName id with which student is to be associated
     * @param item item to be added to the inventory
     * @return the Item Object Associated with item name
     */
    Item addItem(String itemName, Item item) throws VendingMachinePersistenceException;
    
    
    /**
     * Decrements the stock of vended item
     * @param itemName
     * @return Item with updated stock
     * @throws VendingMachinePersistenceException 
     */
    void updateItemStock(String itemName) throws VendingMachinePersistenceException;
    
    /**
     * Checks to see if the item is in stock, returns true if yes
     * @param itemName
     * @return
     * @throws VendingMachinePersistenceException 
     */
    boolean isInStock(String itemName) throws VendingMachinePersistenceException;
}
