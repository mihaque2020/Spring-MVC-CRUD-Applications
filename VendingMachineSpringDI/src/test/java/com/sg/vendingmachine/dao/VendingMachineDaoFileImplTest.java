/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Minul
 */
public class VendingMachineDaoFileImplTest {

    VendingMachineDao testDao;

    public VendingMachineDaoFileImplTest() {
    }

    // Before each test, we create a new, blank testroster.txt file using the File Writer
    // Then use that as our file name when instantiatign our testDAo
    @BeforeEach
    public void setUp() throws IOException {
        String testFile = "testinventory.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);
    }

    @Test
    public void testAddGetItem() throws Exception {
        // ARRANGE - Create our method test inputs
        String itemName = "Snickers";
        Item item = new Item(itemName);
        item.setItemPrice(new BigDecimal("2.75"));
        item.setItemStock(10);

        // ACT - add/get the item from the DAO
        testDao.addItem(itemName, item);
        Item retrievedItem = testDao.getItem(itemName);

        // ASSERT - Check that the data is equal
        assertEquals(item.getItemName(),
                retrievedItem.getItemName(),
                "Checking Item Name.");
        assertEquals(item.getItemPrice(),
                retrievedItem.getItemPrice(),
                "Checking Item Price.");
        assertEquals(item.getItemStock(),
                retrievedItem.getItemStock(),
                "Checking Item Stock.");
    }

    @Test
    public void testGetAllItems() throws Exception {

        // Arrange - Create two Item Objects and write to testfile
        Item firstItem = new Item("Snickers");
        firstItem.setItemPrice(new BigDecimal("2.75"));
        firstItem.setItemStock(10);

        Item secondItem = new Item("Kit-Kat");
        secondItem.setItemPrice(new BigDecimal("3.00"));
        secondItem.setItemStock(12);

        // ACT - add/get all items from the DAO
        testDao.addItem(firstItem.getItemName(), firstItem);
        testDao.addItem(secondItem.getItemName(), secondItem);

        List<Item> allItems = testDao.getAllItems();

        // Check in general
        assertNotNull(allItems, "The list of items must not be null");
        assertEquals(2, allItems.size(), "Inventory should have two items");

        // Check specifics
        assertTrue(testDao.getAllItems().contains(firstItem),
                "The inventory shoudl include Snickers");
        assertTrue(testDao.getAllItems().contains(secondItem),
                "The inventory should include Kit-Kat");
        // Need to include hashcose/equals/toString methods/additions to Item Class      
    }

    // New Code
    @Test
    public void testUpdateStockCheckOutofStock() throws Exception {
        // Arrange
        String itemName = "Snickers";
        Item item = new Item(itemName);
        item.setItemPrice(new BigDecimal("2.75"));
        item.setItemStock(1);

        // Act
        testDao.addItem(itemName, item);
        testDao.updateItemStock(itemName);
        
        boolean inStock = testDao.isInStock(itemName);
        
        // Assert
        assertFalse(inStock, "Snickers should not be in stock.");
    }
}
