package me.xmerge.util;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Test for IntegerGenerator
 */
public class IntegersGeneratorTest extends TestCase {
    public void testRange() {
        ArrayList<Integer> tmp = IntegersGenerator.range(1, 5);
        // System.out.println(tmp);
    }
}