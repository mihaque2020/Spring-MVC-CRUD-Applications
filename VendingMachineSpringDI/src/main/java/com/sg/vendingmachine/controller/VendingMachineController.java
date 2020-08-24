/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.ChangeImpl.Change;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineItemValidationException;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Minul
 */
public class VendingMachineController {

    private VendingMachineServiceLayer service;
    private VendingMachineView view;
    
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        int choice = 0;
        try {
            choice = displayItemsContinue();
            switch (choice) {
                case 1:
                    Vending();
                    break;
                case 2:
                    exitMessage();
                    break;
                default:
                    unknownCommand();
            }
        } catch (VendingMachinePersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
    }

    private int displayItemsContinue() throws 
            VendingMachinePersistenceException {
        List<Item> inventory = service.getAllItems();
        return view.printInventoryGetSelection(inventory);
    }

    private void Vending() throws
            VendingMachinePersistenceException {
        try {
            BigDecimal userMoney = view.getMoney();
            String userChoice = view.getItemSelection();
            Item item = service.getItem(userChoice);
            
            if (service.isVendable(userMoney, item.getItemPrice(), item)) {
                service.updateItemStock(item.getItemName());
                view.displayVending(item.getItemName());
                Change change = service.calculateChange(userMoney, item.getItemPrice());
                view.displayChange(change);
            } else {
                Change returnedMoney = service.calculateChange(userMoney);
                view.displayChange(returnedMoney);
            }
        } catch (VendingMachinePersistenceException 
                | VendingMachineInsufficientFundsException
                | VendingMachineItemValidationException
                | VendingMachineNoItemInventoryException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
    }

    private void exitMessage() {
        view.displayExitMessage();
    }

    private void unknownCommand() {
        view.displayUnknownCommand();
    }
}
