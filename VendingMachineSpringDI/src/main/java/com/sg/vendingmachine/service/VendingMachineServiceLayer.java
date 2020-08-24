/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.ChangeImpl.Change;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Minul
 */
public interface VendingMachineServiceLayer {

    List<Item> getAllItems() throws
            VendingMachinePersistenceException;

    Item getItem(String itemName) throws
            VendingMachinePersistenceException,
            VendingMachineItemValidationException;

    void updateItemStock(String itemName) throws
            VendingMachinePersistenceException;

    public boolean isVendable(BigDecimal userMoney, BigDecimal itemPrice, Item item)
            throws VendingMachinePersistenceException, 
            VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException;
    
    public Change calculateChange(BigDecimal userMoney, BigDecimal itemPrice);
    
    public Change calculateChange(BigDecimal userMoney);
    
    // Method to store userMoney
    
}
