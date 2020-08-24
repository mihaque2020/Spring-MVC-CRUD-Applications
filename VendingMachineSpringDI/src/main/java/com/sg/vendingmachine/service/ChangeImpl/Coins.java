/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service.ChangeImpl;

/**
 *
 * @author Minul
 */
public enum Coins {
    
    QUARTERS(25),
    DIMES(10),
    NICKLES(5),
    PENNIES(1);

    private final int value;

    Coins(int value) {
        this.value = value;
    }
    
    public int value() {
        return value;
    }
}
