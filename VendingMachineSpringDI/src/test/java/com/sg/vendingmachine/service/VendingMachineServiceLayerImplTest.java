/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Minul
 */
public class VendingMachineServiceLayerImplTest {
    
    VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerImplTest() { 
        
    ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    service = 
        ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }

    @Test
    public void getItem() throws Exception {
        // ARRANGE
        Item testClone = new Item("Snickers");
        testClone.setItemPrice(new BigDecimal("2.75"));
        testClone.setItemStock(10);
        
        // ACT
        Item shouldBeSnickers = service.getItem("Snickers");
       
        // Assert
        assertNotNull(shouldBeSnickers, "Getting Snickers should not be null");
        assertEquals(testClone, shouldBeSnickers,
                                    "Item stored under Snickers should be Snickers.");
        
    }
    
    @Test
    public void isVendable() throws Exception {
        // ARRANGE
        BigDecimal userMoney = new BigDecimal("3.00");
        BigDecimal itemPrice = new BigDecimal("2.75");
        Item testClone = new Item("Snickers");
        testClone.setItemPrice(new BigDecimal("2.75"));
        testClone.setItemStock(10);
        
        // ACT
        boolean expected = service.isVendable(userMoney, itemPrice, testClone);
        
        // Assert
        assertTrue(expected, "Item should be vendable");
        
    }
    // Dont need to test updateItemStock, getAllItems bc they directly call 
    // DAO and already tested them
    
    // CalculateChange and isSufficient is simple arithmetic/comparisons - unecessary tests
}
