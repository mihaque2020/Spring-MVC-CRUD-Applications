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
public class Change {
    
    private int quarters;
    private int dimes;
    private int nickles;
    private int pennies;
    
    public Change(int value) {
        while (value > 0) {
            if (value >= Coins.QUARTERS.value()) {
                quarters++;
                value -= Coins.QUARTERS.value();
            } else if (value >= Coins.DIMES.value()) {
                dimes++;
                value -= Coins.DIMES.value();
            } else if (value >= Coins.NICKLES.value()) {
                nickles++;
                value -= Coins.NICKLES.value();
            } else {
                pennies = value;
                value = 0;
            }
        }
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickles() {
        return nickles;
    }

    public int getPennies() {
        return pennies;
    }

    @Override
    public String toString() {
        return "Change{" + "quarters=" + quarters + ", dimes=" + dimes + ", nickles=" + nickles + ", pennies=" + pennies + '}';
    }
    
}
