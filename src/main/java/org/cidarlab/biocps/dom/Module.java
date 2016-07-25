/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.dom;

import hyness.stl.grammar.flat.STLflat;
import java.util.ArrayList;
import java.util.List;
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
    private List<String> outputs;
    
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
        outputs = new ArrayList<String>();
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
