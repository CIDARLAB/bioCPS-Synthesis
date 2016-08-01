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
    
    public static void synthesize(Map<Integer,Module> candidates){
        
        Map<Integer,Module> newCandidates = new HashMap<Integer,Module>();
        for(Module firstModule:candidates.values()){
            for(Module secondModule : candidates.values()){
                if(firstModule.canCombine(secondModule)){
                    
                    
                    //Concatenation
                    if(Compose.canConcatenate(firstModule, secondModule)){
                        Module concatenatedModule = Compose.concatenation(firstModule, secondModule);
                        if(!candidates.containsKey(concatenatedModule.hashCode())){
//                            System.out.println("====== Concatenation ======");
//                            System.out.println("First Module  :: " + firstModule);
//                            System.out.println(firstModule.getModuleNames());
//                            System.out.println("Second Module :: " + secondModule);
//                            System.out.println(secondModule.getModuleNames());
                            if(!newCandidates.containsKey(concatenatedModule.hashCode())){
                                newCandidates.put(concatenatedModule.hashCode(),concatenatedModule);
                            }
                        }
                    }
                    
                    //Parallel
                    Module parallelModule = Compose.parallel(firstModule, secondModule);
                    if(!candidates.containsKey(parallelModule.hashCode())){
//                        System.out.println("====== Parallel ======");
//                        System.out.println("First Module  :: " + firstModule);
//                        System.out.println(firstModule.getModuleNames());
//                        System.out.println("Second Module :: " + secondModule);
//                        System.out.println(secondModule.getModuleNames());
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
