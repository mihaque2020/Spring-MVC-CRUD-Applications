/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.unittesting.arrays;

import static com.tsg.unittesting.arrays.ArrayExerciseA.maxOfArray;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Minul
 */
public class ArrayExerciseATest {
    
    //Given an array of ints, find and return the maximum value.
    public ArrayExerciseATest() {
        
    }
    
    /* Test Plan
    **
    ** maxOfArray( {1}  ) ->  1
    ** maxOfArray( {3, 4, 5}  ) ->  5
    ** maxOfArray( {-9000, -700, -50, -3}  ) ->  -3
    ** maxOfArray( {0, 0, 0,}) -> 0
    ** maxOfArray( {-1, 2, -3, 4} ) -> 4
    **
    */
    
    @Test
    public void singleArray() {
        
        // Arrange
        int[] array = {1};
        
        // Act
        int result = maxOfArray(array);
        
        // Assert
        assertEquals(1, result, "The single element shoudl be the max");
    }
    
}
