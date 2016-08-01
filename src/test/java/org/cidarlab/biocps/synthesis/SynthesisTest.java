/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.synthesis;

import hyness.stl.LinearPredicateLeaf;
import hyness.stl.RelOperation;
import hyness.stl.grammar.flat.STLflat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import junit.framework.Test;
import junit.framework.TestCase;
import org.cidarlab.biocps.dom.Module;
import org.cidarlab.biocps.dom.Signal;

/**
 *
 * @author prash
 */
public class SynthesisTest extends TestCase {
    
    public SynthesisTest(String testName) {
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

    public static Set<Module> createLibrary(){
        Set<Module> library = new HashSet<Module>();
        
        Module m1 = new Module("m1");
        Module m2 = new Module("m2");
        Module m3 = new Module("m3");
        Module m4 = new Module("m4");
        
        
        Signal s1in = new Signal("in1");
        Signal s1out = new Signal("out1");
        Signal s2in = new Signal("in2");
        Signal s2out = new Signal("out2");
        Signal s3in = new Signal("in3");
        Signal s3out = new Signal("out3");
        Signal s4in = new Signal("in4");
        Signal s4out = new Signal("out4");
        
        m1.getInputs().add(s1in);
        m2.getInputs().add(s2in);
        m3.getInputs().add(s3in);
        m4.getInputs().add(s4in);
        
        m1.getOutputs().add(s1out);
        m2.getOutputs().add(s2out);
        m3.getOutputs().add(s3out);
        m4.getOutputs().add(s4out);
        
        LinearPredicateLeaf lp1 = new LinearPredicateLeaf(RelOperation.GE,"x1",5.5);
        LinearPredicateLeaf lp2 = new LinearPredicateLeaf(RelOperation.GE,"x2",5.6);
        LinearPredicateLeaf lp3 = new LinearPredicateLeaf(RelOperation.GE,"x3",5.7);
        LinearPredicateLeaf lp4 = new LinearPredicateLeaf(RelOperation.GE,"x4",5.8);
        
        STLflat stlf1 = new STLflat(lp1);
        STLflat stlf2 = new STLflat(lp2);
        STLflat stlf3 = new STLflat(lp3);
        STLflat stlf4 = new STLflat(lp4);
        
        m1.setStlflat(stlf1);
        m2.setStlflat(stlf2);
        m3.setStlflat(stlf3);
        m4.setStlflat(stlf4);
        
        library.add(m1);
        library.add(m2);
        library.add(m3);
        //library.add(m4);
        
        
        return library;
    }
    
    
    public void testSynthesis(){
        
        System.out.println("Synthesis Test :: ");
        Map<Integer,Module> circuits = new HashMap<>();
        for(Module module:SynthesisTest.createLibrary()){
            circuits.put(module.hashCode(), module);
        }
        Synthesis.synthesize(circuits);
        
        
        for(Module circuit:circuits.values()){
            System.out.println(circuit);
        }
        
    }
    
}
