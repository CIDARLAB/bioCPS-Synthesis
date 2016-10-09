/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.dom;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author prash
 */
public class Arc {
    
    @Getter
    private Feature regulator;
    
    @Getter
    private Feature regulatee;
    
    @Getter
    private ArcRole role;
    
    @Getter
    @Setter
    private List<SmallMolecule> smallMolecules;
    
    public Arc(Feature _regulator, Feature _regulatee, ArcRole _role){
        smallMolecules = new ArrayList<SmallMolecule>();
        this.regulatee = _regulatee;
        this.regulator = _regulator;
        this.role = _role;
    }
    
    public enum ArcRole{
        REPRESSION,
        ACTIVATION
    }
    
}
