/*
 Copyright (c) 2009 The Regents of the University of California.
 All rights reserved.
 Permission is hereby granted, without written agreement and without
 license or royalty fees, to use, copy, modify, and distribute this
 software and its documentation for any purpose, provided that the above
 copyright notice and the following two paragraphs appear in all copies
 of this software.

 IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY
 FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
 ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
 SUCH DAMAGE.

 THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
 PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
 CALIFORNIA HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
 ENHANCEMENTS, OR MODIFICATIONS..
 */
package org.cidarlab.biocps.dom;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author prash
 */
//From CLOTHO:
//unique name requirement
//sequence should be unique
//start/stop codons get clipped
//sequence cannot be degenerate
//cds-like sequences might be cds
//cds features must have valid cds sequence

public class Feature {
    
    @Getter
    private String name;
    
    @Setter
    @Getter
    private Sequence sequence;

    @Setter
    private Color forwardColor, reverseColor;
    
    @Setter
    @Getter
    private String genbankId, swissProtId, PDBId;

    @Getter
    private short riskGroup;
    
    @Setter
    @Getter
    private FeatureRole role;
    
    @Setter
    @Getter
    private List<Arc> arcs;
    
    public void addArc(Arc _arc){
        this.arcs.add(_arc);
    }
    
    public Feature(String _name) {
        this.name = _name;
        this.forwardColor = new Color(0);
        this.reverseColor = new Color(0);
        this.arcs = new ArrayList<>();
    }
    
    
    public Feature(String _name, Sequence _seq, FeatureRole _role) {
        this.name = _name;
        this.sequence = _seq;
        this.role = _role;
        this.forwardColor = new Color(0);
        this.reverseColor = new Color(0);
        this.arcs = new ArrayList<>();
    }
    
    /**
     * Get the preferred forward color for this Feature. If no forward color was
     * set, a default color will be returned.
     *
     * @return an AWT Color object. It won't be null;
     */
    public Color getForwardColor() {
        if (forwardColor == null) {
            return new Color(125, 225, 235);
        }
        return forwardColor;
    }

    /**
     * Get the preferred reverse color for this Feature. If no reverse color was
     * set, a default color will be returned.
     *
     * @return an AWT Color object. It won't be null;
     */
    public Color getReverseColor() {
        if (reverseColor == null) {
            return new Color(125, 225, 235);
        }
        return reverseColor;
    }
    
    
    /**
     * Set the forward and reverse preferred colors for this feature to some
     * random medium-bright color.
     */
    public void setRandomColors() {
        int[][] intVal = new int[2][3];
        for (int j = 0; j < 3; j++) {
            double doubVal = Math.floor(Math.random() * 155 + 100);
            intVal[0][j] = (int) doubVal;
            intVal[1][j] = 255 - intVal[0][j];
        }
        forwardColor = new Color(intVal[0][0], intVal[0][1], intVal[0][2]);
        reverseColor = new Color(intVal[1][0], intVal[1][1], intVal[1][2]);
    }

    public final void setRiskGroup(short newrg) {
        if (newrg > riskGroup && newrg <= 5) {
            //addUndo("_riskGroup", _featDatum._riskGroup, newrg);
            riskGroup = newrg;
            // setChanged(org.clothocore.api.dnd.RefreshEvent.Condition.RISK_GROUP_CHANGED);
        }
        //todo: throw appropriate invalid operation exception
    }
    
    @Override
    public String toString(){
        String _string = "";
        _string += this.getName() +"::";
        _string += this.role;
        return _string;
        
    }
    
    public static enum FeatureRole {

        PROMOTER,
        PROMOTER_REPRESSIBLE,
        PROMOTER_INDUCIBLE,
        PROMOTER_CONSTITUTIVE,
        RBS,
        CDS,
        CDS_REPRESSOR,
        CDS_ACTIVATOR,
        CDS_REPRESSIBLE_REPRESSOR,
        CDS_ACTIVATIBLE_ACTIVATOR,
        CDS_LINKER,
        CDS_TAG,
        CDS_RESISTANCE,
        CDS_FLUORESCENT,
        CDS_FLUORESCENT_FUSION,
        TERMINATOR,
        ORIGIN,
        VECTOR,
        TESTING,
        MARKER,
        WILDCARD;
    }
}
