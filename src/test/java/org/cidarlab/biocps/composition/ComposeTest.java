/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.composition;

import junit.framework.TestCase;
import org.cidarlab.biocps.dom.Module;

/**
 *
 * @author prash
 */
public class ComposeTest extends TestCase {
    
    public ComposeTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of concatenation method, of class Compose.
     */
    public void testConcatenation() {
        System.out.println("concatenation");
        Module m1 = null;
        Module m2 = null;
        Module expResult = null;
        Module result = Compose.concatenation(m1, m2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parallel method, of class Compose.
     */
    public void testParallel() {
        System.out.println("parallel");
        Module m1 = null;
        Module m2 = null;
        Module expResult = null;
        Module result = Compose.parallel(m1, m2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
