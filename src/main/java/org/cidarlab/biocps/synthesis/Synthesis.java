/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.synthesis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.cidarlab.biocps.composition.Compose;
import org.cidarlab.biocps.dom.Module;

/**
 *
 * @author prash
 */
public class Synthesis {
    
    public static void synthesize(Set<Module> candidates){
        
        Set<Module> newCandidates = new HashSet<Module>();
        for(Module firstModule:candidates){
            for(Module secondModule : candidates){
                if(firstModule.canCombine(secondModule)){
                    
                    
                    //Concatenation
                    if(Compose.canConcatenate(firstModule, secondModule)){
                        Module concatenatedModule = Compose.concatenation(firstModule, secondModule);
                        if(!candidates.contains(concatenatedModule)){
//                            System.out.println("====== Concatenation ======");
//                            System.out.println("First Module  :: " + firstModule);
//                            System.out.println(firstModule.getModuleNames());
//                            System.out.println("Second Module :: " + secondModule);
//                            System.out.println(secondModule.getModuleNames());
                            newCandidates.add(concatenatedModule);
                        }
                    }
                    
                    //Parallel
                    Module parallelModule = Compose.parallel(firstModule, secondModule);
                    if(!candidates.contains(parallelModule)){
//                        System.out.println("====== Parallel ======");
//                        System.out.println("First Module  :: " + firstModule);
//                        System.out.println(firstModule.getModuleNames());
//                        System.out.println("Second Module :: " + secondModule);
//                        System.out.println(secondModule.getModuleNames());
                        newCandidates.add(parallelModule);
                    }
                }
            }
        }
        
        if(!newCandidates.isEmpty()){
            candidates.addAll(newCandidates);
            synthesize(candidates);
        } 
        
        
    }

    
    
    public static boolean isMorphic(Module m1, Module m2){
        
        return false;
    }
    
}
