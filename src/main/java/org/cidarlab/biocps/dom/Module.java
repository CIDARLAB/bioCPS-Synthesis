/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.dom;

import hyness.stl.grammar.flat.STLflat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;



/**
 *
 * @author prash
 */
public class Module {
    
    @Getter
    private String name;
    
    @Getter
    @Setter
    private List<Signal> inputs;
    
    @Getter
    @Setter
    private List<Signal> outputs;
    
    @Getter
    @Setter
    private STLflat stlflat;
    
    @Getter
    @Setter
    private Operation op;
    
    @Getter
    @Setter
    private Module left;
    
    @Getter
    @Setter
    private Module right;
    
    public Module(String _name){
        name = _name;
        op = Operation.nop;
        inputs = new ArrayList<Signal>();
        outputs = new ArrayList<Signal>();
    }
    
    public Module(String _name, Operation _op, Module _left, Module _right){
        name = _name;
        op = _op;
        left = _left;
        right = _right;
        inputs = new ArrayList<Signal>();
        outputs = new ArrayList<Signal>();
    }
    
    @Override
    public boolean equals(Object m){
        
        if(!(m instanceof Module)){
            return false;
        }
        
        Module module = (Module)m;
        
        if(module.getName().equals(this.name)){
            return true;
        }
        
        if(module.op.equals(Operation.parallel) && this.op.equals(Operation.parallel)){
            if(module.left.name.equals(this.right.name) && module.right.name.equals(this.left.name)){
                return true;
            }
        }
        
        return false;
        //return module.getName().equals(this.name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        
        if(this.op.equals(Operation.nop)){
            hash = 67 * hash + Objects.hashCode(this.name);
        }
        
        if(this.op.equals(Operation.compose)){
            hash = 67 * hash + Objects.hashCode(this.toString());
        }
        
        if(this.op.equals(Operation.parallel)){
            String concatName = "";
            //concatName += this.getParallelModules();
            for(String moduleName : this.getParallelModules()){
                concatName += moduleName;
            }
            concatName += Operation.parallel.toString();
            hash = 67 * hash + Objects.hashCode(concatName);
        }
        return hash;
    }
    
    public Set<String> getParallelModules(){
        if(this.op.equals(Operation.nop)){
            Set<String> moduleNameSet = new HashSet<String>();
            moduleNameSet.add(this.name);
            return moduleNameSet;
        }
        if(this.op.equals(Operation.compose)){
            Set<String> moduleNameSet = new HashSet<String>();
            moduleNameSet.add(this.toString());
            return moduleNameSet;
        }
        if(this.op.equals(Operation.parallel)){
            Set<String> moduleNameSet = new HashSet<String>();
            moduleNameSet.addAll(this.left.getParallelModules());
            moduleNameSet.addAll(this.right.getParallelModules());
            return moduleNameSet;
        }
        
        return null;
    }
    
    
//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 53 * hash + Objects.hashCode(this.name);
//        hash = 53 * hash + Objects.hashCode(this.op);
//        hash = 53 * hash + Objects.hashCode(this.left);
//        hash = 53 * hash + Objects.hashCode(this.right);
//        return hash;
//    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 59 * hash + Objects.hashCode(this.name);
//        return hash;
//    }
    
    public int getInputCount(){
        return this.inputs.size();
    }
    
    public int getOutputCount(){
        return this.outputs.size();
    }
    
    public static String operationString(Operation _op){
        switch(_op){
            case compose:
                return "*";
            case parallel:
                return "#";
            default: 
                return "";
        }
    }
    
    public Set<String> getModuleNames(){
        Set<String> names = new HashSet<String>();
        if(op.equals(Operation.nop)){
            names.add(name);
        } else {
            names.addAll(this.left.getModuleNames());
            names.addAll(this.right.getModuleNames());
        }
        return names;
    }
    
    public boolean canCombine(Module m){
        
        for(String mModule:m.getModuleNames()){
            if(this.getModuleNames().contains(mModule)){
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public String toString(){        
        if(op.equals(Operation.nop)){
            return this.name;
        }
        else {
            return ("(" + this.left.toString() + Module.operationString(this.op) + this.right.toString() + ")");
        }
    }
}
