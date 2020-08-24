/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.enums;

import java.util.Scanner;
import static com.sg.enums.MathOperators.*;
/**
 *
 * @author Minul
 */
public class MathApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the operation: ");
        MathOperators operator = MathOperators.valueOf(sc.nextLine().toUpperCase());
        System.out.println("Enter operand1: ");
        int operand1 = Integer.parseInt(sc.nextLine());
        System.out.println("Enter operand2: ");
        int operand2 = Integer.parseInt(sc.nextLine());
        
        int result = calculate(operator, operand1, operand2);
        
        System.out.println("Result: " + result);
        
    }

    public static int calculate(MathOperators operator, int operand1, int operand2) {
        switch (operator) {
            case PLUS:
                return operand1 + operand2;
            case MINUS:
                return operand1 - operand2;
            case MULTIPLY:
                return operand1 * operand2;
            case DIVIDE:
                return operand1 / operand2;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
