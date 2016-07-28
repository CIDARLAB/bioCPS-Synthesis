/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.synthesis;

import java.util.Set;
import org.cidarlab.biocps.dom.Module;

/**
 *
 * @author prash
 */
public class Synthesis {
    
    public static void synthesize(Set<Module> circuits, Set<Module> candidates){
        if(candidates.isEmpty())
            return;
        for(Module candidate:candidates){
            
        }
    }

    public static boolean isMorphic(Module m1, Module m2){
        
        return false;
    }
    
}
