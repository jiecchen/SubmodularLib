package me.xmerge.util;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Test for SetCoverDataGenerator
 */
public class SetCoverDataGeneratorTest extends TestCase {

    public void setUp() {
    }

    public void test() {
        ArrayList<Integer> groundSet = IntegersGenerator.range(10);
        SetCoverDataGenerator<Integer> scGenerator = new SetCoverDataGenerator<>();
        ArrayList<HashSet<Integer>> sets = scGenerator.generate(5, groundSet);

    }
}