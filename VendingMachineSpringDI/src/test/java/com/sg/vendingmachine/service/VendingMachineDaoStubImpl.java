/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Minul
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    
    public Item onlyItem;
    
    public VendingMachineDaoStubImpl() {
        onlyItem = new Item("Snickers");
        onlyItem.setItemPrice(new BigDecimal("2.75"));
        onlyItem.setItemStock(10);
    }
    
    public VendingMachineDaoStubImpl(Item testItem) {
        this.onlyItem = testItem;
    }
    
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        if (itemName.equalsIgnoreCase(onlyItem.getItemName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public Item addItem(String itemName, Item item) throws VendingMachinePersistenceException {
        if (itemName.equalsIgnoreCase(onlyItem.getItemName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void updateItemStock(String itemName) throws VendingMachinePersistenceException {
        onlyItem.setItemStock(onlyItem.getItemStock()-1);
        return;
    }

    @Override
    public boolean isInStock(String itemName) throws VendingMachinePersistenceException {
        if (onlyItem.getItemStock() != 0) {
            return true;
        } else {
            return false;
        }
    }
    
}
