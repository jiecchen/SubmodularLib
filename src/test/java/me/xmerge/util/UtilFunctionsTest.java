package me.xmerge.util;


import junit.framework.TestCase;

import java.util.LinkedList;



/**
 * Tests for UtilFunctions.*
 */
public class UtilFunctionsTest extends TestCase{
    public void testDeepClone() {
        LinkedList<Double> arrA = new LinkedList<>();
        arrA.add(10.);
        LinkedList<Double> arrB = (LinkedList<Double>) UtilFunctions.deepClone(arrA);
        arrB.add(100.);
        assertTrue(arrB.size() == arrA.size() + 1);
    }

}