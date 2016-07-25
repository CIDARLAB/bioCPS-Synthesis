/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.dom;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author prash
 */
public class Signal {
    
    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private SignalType type;
    
    public enum SignalType{
        input,
        output,
        connector
    }
    
}
