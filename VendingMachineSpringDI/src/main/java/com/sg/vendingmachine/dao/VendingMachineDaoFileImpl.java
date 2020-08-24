/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Minul
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public final String INVENTORY_FILE;
    public static final String DELIMITER = "::";
    public static final String REPLACEMENT = "++";
    public static final int NUMBER_OF_FIELDS = 3;

    private Map<String, Item> inventory = new HashMap<>();

    public VendingMachineDaoFileImpl() {
        INVENTORY_FILE = "inventory.txt";
    }

    public VendingMachineDaoFileImpl(String inventoryTextFile) {
        INVENTORY_FILE = inventoryTextFile;
    }

    @Override
    public List<Item> getAllItems()
            throws VendingMachinePersistenceException {
        loadInventory();
        return new ArrayList<Item>(inventory.values());
    }

    @Override
    public Item getItem(String itemName)
            throws VendingMachinePersistenceException {
        return inventory.get(itemName);
    }
    
    @Override
    public Item addItem(String itemName, Item item)
            throws VendingMachinePersistenceException {
        loadInventory();
        Item prevItem = inventory.put(itemName, item);
        writeInventory();
        return prevItem;
    }
    
    @Override
    public void updateItemStock(String itemName) 
            throws VendingMachinePersistenceException{
        loadInventory();
        Item updatedItem = inventory.get(itemName);
        updatedItem.setItemStock(updatedItem.getItemStock() - 1);
        inventory.put(itemName, updatedItem);
        writeInventory();
        return;
    }
    
    @Override
    public boolean isInStock(String itemName) {
        Item checkedItem = inventory.get(itemName);
        if (checkedItem.getItemStock() != 0) {
            return true;
        }
        return false;
    }
    
    private Item unmarshallItem(String itemAsText) {
        // itemAsText expecting a line read in from our file.
        // For Example the text in the file looks like:
        // SNICKERS::2.75::10
        // Split that line on our DELIMITER (::) 
        // Results in an array of Strings, stored in itemTokens.
        // Looks LIKE:
        // _______________________________
        // |          |        |         |
        // | Snickers |  2.75  |   10    |
        // |          |        |         |
        // -------------------------------
        //  [0]         [1]      [2]       

        String[] itemTokens = itemAsText.split(DELIMITER);

        // Chekc to make sure I have expected number of items from split
        if (itemTokens.length == NUMBER_OF_FIELDS) {

            // itemName is Index 0
            String itemName = itemTokens[0];

            // We can create new Item Object using the name 
            // - all we need to satisfy constructor requirements
            Item itemFromFile = new Item(itemName);

            // Get remaining tokens and use setters to apply to current Item Object
            // Index 1 - price
            itemFromFile.setItemPrice(new BigDecimal(itemTokens[1]));

            // Index 2 -  Stock
            itemFromFile.setItemStock(Integer.parseInt(itemTokens[2]));

            // We have now created an Item, return it!
            return itemFromFile;
        } else {
            return null;
        }
    }

    private void loadInventory() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "Could not load inventory data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentItem holds the most recent Item unmarshalled
        Item currentItem;

        // Go through the inventory file line by line, decoding each line into 
        // a Item Object by calling the unmarshallItem method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Item
            currentItem = unmarshallItem(currentLine);

            // Use itemName as the map key for Item Objects
            // out currentItem into the map using itemName as the key
            if (currentItem != null) {
                inventory.put(currentItem.getItemName(), currentItem);
            }
        }
        // close scanner
        scanner.close();
    }

    private String replaceControlCharacters(String value) {
        return value.replace(DELIMITER, REPLACEMENT);
    }

    private String marshallItem(Item anItem) {
        // Need to turn a Item Object into a line of text for our file
        // Should look like this:
        // Snickers::2.75::10

        // Get out eacah property and concatenate with our DELIMITER (spacer)
        // Start with itemName, then add rest in correct order
        String itemAsText = replaceControlCharacters(anItem.getItemName()) + DELIMITER;

        // Item Price
        itemAsText += replaceControlCharacters(anItem.getItemPrice().toString()) + DELIMITER;

        // Stock
        itemAsText += replaceControlCharacters(Integer.toString(anItem.getItemStock()));

        // We have now turned an item to text, return it!
        return itemAsText;
    }

    private void writeInventory() throws VendingMachinePersistenceException {
        //We are not handling the IOException - but // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save item data.", e);
        }
        
        // Write out the Item Objects to the Inventory file
        // just use method that gets the list of items instead of collections and iterate
        String itemAsText;
        List<Item> itemList = new ArrayList(inventory.values());
        for (Item currentItem : itemList) {
            // turn a Item into a string
            itemAsText = marshallItem(currentItem);
            // write the Item Object to the files
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
    
}
