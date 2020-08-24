/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.enums;

import static com.sg.enums.Weekdays.*;
import java.util.Scanner;

/**
 *
 * @author Minul
 */
public class HowManyDaysUntilFriday {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a day of the week: ");
        Weekdays day = Weekdays.valueOf(sc.nextLine().toUpperCase());

        switch (day) {
            case SATURDAY:
                System.out.println("6 Days until Friday");
                break;
            case SUNDAY:
                System.out.println("5 Days until Friday");
                break;
            case MONDAY:
                System.out.println("4 Days until Friday");
                break;
            case TUESDAY:
                System.out.println("3 Days until Friday");
                break;
            case WEDNESDAY:
                System.out.println("2 Days until Friday");
                break;
            case THURSDAY:
                System.out.println("1 Days until Friday");
                break;
            case FRIDAY:
                System.out.println("IT IS FRIDAY!");
                break;
            default:
                throw new UnsupportedOperationException();
        }   

    }
}
