/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.dom;

import lombok.Getter;

/**
 *
 * @author prash
 */
public class SmallMolecule {
    
    
    @Getter
    private String name;
    
    @Getter
    private SmallMoleculeRole role;
    
    public SmallMolecule(String _name, SmallMoleculeRole _role){
        this.name = _name;
        this.role = _role;
    }
    
    public enum SmallMoleculeRole{
        REPRESSION,
        ACTIVATION
    }
    
}
