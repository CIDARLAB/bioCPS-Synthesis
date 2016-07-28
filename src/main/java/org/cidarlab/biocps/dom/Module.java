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
    }
    
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
    
    @Override
    public String toString(){
        if(op.equals(Operation.nop)){
            return this.name;
        }
        else {
            return ("(" + this.left.toString() + this.op.toString() + this.right.toString() + ")");
        }
    }
}
