/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.composition;

import hyness.stl.ConcatenationNode;
import hyness.stl.ParallelNode;
import hyness.stl.grammar.flat.STLflat;
import org.cidarlab.biocps.dom.Module;
import org.cidarlab.biocps.dom.Operation;

/**
 *
 * @author prash
 */
public class Compose {
    
    public static boolean canConcatenate(Module m1, Module m2){
        
        if(m1.getOutputCount() != m2.getInputCount()){
            return false;
        }
        
        return true;
    }
    
    public static Module concatenation(Module m1, Module m2){
        String concatenatedModuleName = m1.getName() + Module.operationString(Operation.compose) + m2.getName();
        Module concatenation = new Module(concatenatedModuleName,Operation.compose,m1,m2);
        
        concatenation.getInputs().addAll(m1.getInputs());
        concatenation.getOutputs().addAll(m2.getOutputs());
        ConcatenationNode concatenationNode = new ConcatenationNode(m1.getStlflat().module,m2.getStlflat().module);
        STLflat flatConcatenation = new STLflat(concatenationNode);
        concatenation.setStlflat(flatConcatenation);
        return concatenation;
    }
    
    public static Module parallel(Module m1, Module m2){
        String parallelModuleName = m1.getName() + Module.operationString(Operation.parallel) + m2.getName();
        
        Module parallel = new Module(parallelModuleName, Operation.parallel,m1,m2);
        parallel.getInputs().addAll(m1.getInputs());
        parallel.getInputs().addAll(m2.getInputs());
        
        parallel.getOutputs().addAll(m1.getOutputs());
        parallel.getOutputs().addAll(m2.getOutputs());
        
        ParallelNode parallelNode = new ParallelNode(m1.getStlflat().module,m2.getStlflat().module);
        STLflat flatParallel = new STLflat(parallelNode);
        parallel.setStlflat(flatParallel);
        
        return parallel;
    }
    
    
}
