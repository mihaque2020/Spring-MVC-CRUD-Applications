/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.ChangeImpl.Change;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Minul
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private final VendingMachineDao dao;
    private final VendingMachineAuditDao auditDao;
    // field for userMoney
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Item getItem(String itemName)
            throws VendingMachinePersistenceException,
            VendingMachineItemValidationException {
        if (dao.getItem(itemName) != null) {
            return dao.getItem(itemName);
        } else {
            throw new VendingMachineItemValidationException("Item not in vending machine...");
        }
    }

    @Override
    public List<Item> getAllItems()
            throws VendingMachinePersistenceException {
        return dao.getAllItems();
    } 

    @Override
    public void updateItemStock(String itemName)
            throws VendingMachinePersistenceException {
        dao.updateItemStock(itemName);
        // Write to audit log
        auditDao.writeAuditEntry(itemName + " Dispensed.");
    }

    @Override
    public boolean isVendable(BigDecimal userMoney, BigDecimal itemPrice, Item item)
            throws VendingMachinePersistenceException,
            VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException {
        
        boolean sufficientFunds = false;
        sufficientFunds = isSufficientFunds(userMoney, itemPrice);
        boolean inStock = dao.isInStock(item.getItemName());
        
        if (sufficientFunds && inStock) {
            return true;
           
        } else {
            
            throw new VendingMachineNoItemInventoryException(
                    "Item out of Stock");
        }
    }
    
    @Override
    public Change calculateChange(BigDecimal userMoney, BigDecimal itemPrice) {
        BigDecimal bigTotalCents = userMoney.subtract(itemPrice).multiply(new BigDecimal("100"));
        int totalCents = bigTotalCents.intValue();
        Change change = new Change(totalCents);
        return change;
    }
    
    @Override
     public Change calculateChange(BigDecimal userMoney) {
        userMoney = userMoney.multiply(new BigDecimal("100"));
        int totalCents = Integer.parseInt(userMoney.toString());
        Change change = new Change(totalCents);
        return change;
     }
    
    private boolean isSufficientFunds(BigDecimal userMoney, BigDecimal itemPrice)
            throws VendingMachineInsufficientFundsException {
        if (userMoney.compareTo(itemPrice) >= 0) {
            return true;
        } else {
            throw new VendingMachineInsufficientFundsException(
                    "Insufficient Funds...");
        }
    }
}
