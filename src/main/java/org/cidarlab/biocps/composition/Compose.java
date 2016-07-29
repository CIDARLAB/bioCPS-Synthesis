/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.composition;

import hyness.stl.ConcatenationNode;
import hyness.stl.ModuleLeaf;
import hyness.stl.ModuleNode;
import hyness.stl.Pair;
import hyness.stl.ParallelNode;
import hyness.stl.TreeNode;
import hyness.stl.grammar.flat.STLflat;

import java.util.HashMap;
import java.util.Vector;

import org.cidarlab.biocps.dom.Module;
import org.cidarlab.biocps.dom.Operation;

/**
 *
 * @author prash
 */
public class Compose {
    
    
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
    
    public static STLflat composeWithAnd(STLflat left, STLflat right) {
		int i = 1;
		HashMap<Pair<String, Boolean>,String> m1Map = new HashMap<Pair<String, Boolean>,String>();
		HashMap<Pair<String, Boolean>,String> ioMap = new HashMap<Pair<String, Boolean>,String>();
		HashMap<String,HashMap<String,Double>> limitsMap = new HashMap<String,HashMap<String,Double>>();
		Vector<String> leftPorts = new Vector<String>();
		leftPorts.add("phi1");
		for (Pair<String, Boolean> pair : left.maps.get("io").keySet()) {
			leftPorts.add(left.maps.get("io").get(pair));
			m1Map.put(new Pair<String, Boolean>(left.maps.get("io").get(pair), true), "io" + i);
			ioMap.put(new Pair<String, Boolean>("io" + i, null), "io" + i);
			HashMap<String,Double> limits = new HashMap<String,Double>();
			limits.put("min", left.limitsMap.get(left.maps.get("io").get(pair)).get("min"));
			limits.put("max", left.limitsMap.get(left.maps.get("io").get(pair)).get("max"));
			limitsMap.put("io" + i, limits);
			i++;
		}
		Vector<String> rightPorts = new Vector<String>();
		rightPorts.add("phi2");
		for (Pair<String, Boolean> pair : right.maps.get("io").keySet()) {
			rightPorts.add(right.maps.get("io").get(pair));
			m1Map.put(new Pair<String, Boolean>(right.maps.get("io").get(pair), false), "io" + i);
			ioMap.put(new Pair<String, Boolean>("io" + i, null), "io" + i);
			HashMap<String,Double> limits = new HashMap<String,Double>();
			limits.put("min", right.limitsMap.get(right.maps.get("io").get(pair)).get("min"));
			limits.put("max", right.limitsMap.get(right.maps.get("io").get(pair)).get("max"));
			limitsMap.put("io" + i, limits);
			i++;
		}
		TreeNode leftNode = new ModuleLeaf("phi1", leftPorts);
		TreeNode rightNode = new ModuleLeaf("phi2", rightPorts);
		TreeNode andNode = new ModuleNode(hyness.stl.Operation.AND, leftNode, rightNode, "m1");
		STLflat stl = new STLflat(andNode);
		stl.modules.put("phi1", left);
		stl.modules.put("phi2", right);
		stl.maps.put("m1", m1Map);
		stl.maps.put("io", ioMap);
		stl.limitsMap = limitsMap;
		return stl;
	}
    
    
}
