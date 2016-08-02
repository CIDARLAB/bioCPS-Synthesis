/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.synthesis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.cidarlab.biocps.composition.Compose;
import org.cidarlab.biocps.dom.Module;

/**
 *
 * @author prash
 */
public class Synthesis {

    public static Set<Module> synthesize(Set<Module> candidates){
        Map<Integer,Module> circuits = new HashMap<>();
        for(Module module:candidates){
            circuits.put(module.hashCode(), module);
        }
        synthesize(circuits);
        return new HashSet<>(circuits.values());
    }
    
    private static void synthesize(Map<Integer,Module> candidates){
        
        Map<Integer,Module> newCandidates = new HashMap<Integer,Module>();
        for(Module firstModule:candidates.values()){
            for(Module secondModule : candidates.values()){
                if(firstModule.canCombine(secondModule)){
                    //Concatenation
                    if(Compose.canConcatenate(firstModule, secondModule)){
                        Module concatenatedModule = Compose.concatenation(firstModule, secondModule);
                        if(!candidates.containsKey(concatenatedModule.hashCode())){
                            if(!newCandidates.containsKey(concatenatedModule.hashCode())){
                                newCandidates.put(concatenatedModule.hashCode(),concatenatedModule);
                            }
                        }
                    }
                    //Parallel
                    Module parallelModule = Compose.parallel(firstModule, secondModule);
                    if(!candidates.containsKey(parallelModule.hashCode())){
                        if(!newCandidates.containsKey(parallelModule.hashCode())){
                            newCandidates.put(parallelModule.hashCode(),parallelModule);
                        }
                    }
                }
            }
        }
        if(!newCandidates.isEmpty()){
            candidates.putAll(newCandidates);
            synthesize(candidates);
        } 
    }

    
    
    public static boolean isMorphic(Module m1, Module m2){
        
        return false;
    }
    
}
