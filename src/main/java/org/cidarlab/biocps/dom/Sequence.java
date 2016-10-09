/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.biocps.dom;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author prash
 */
public abstract class Sequence {
    
    @NotNull
    @Getter
    @Setter
    @Pattern(regexp = "[ATUCGRYKMSWBDHVN]*", flags = {Pattern.Flag.CASE_INSENSITIVE})
    protected String sequence;
    
    @Getter
    @Setter
    protected Set<Annotation> annotations;
    
    @Getter
    private String name;
    
    protected Sequence(String _name, String _sequence) {
        this.name = _name;
        this.sequence = _sequence;
        annotations = new HashSet<>();
    }
    
    protected Sequence(String _name, String _sequence, Set<Annotation> _annotations) {
        this.name = _name;
        this.sequence = _sequence;
        this.annotations = _annotations;
    }

    public void addAnnotation(Annotation annotation) {
        annotations.add(annotation);
    }
    
}
