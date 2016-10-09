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
import lombok.Getter;

/**
 *
 * @author prash
 */
public class Annotation {
    
    @Getter
    private String name;
    
    @Getter
    private String symbol;
    
    @Getter
    private boolean isForwardStrand;
    
    @Getter
    private Feature feature;
    
    @Getter
    private int start, end;
    
    @Getter
    private Color forwardColor, reverseColor;
    
    public Annotation(String _name, String _symbol, boolean plusStrandTrue, Feature _feature, int _start, int _end, Color _forwardColor, Color _reverseColor){
        this.name = _name;
        this.symbol = _symbol;
        this.isForwardStrand = plusStrandTrue;
        this.feature = _feature;
        this.start = _start;
        this.end = _end;
        this.forwardColor = _forwardColor;
        this.reverseColor = _reverseColor;
    }
    
    /**
     * Reverse the orientation of the annotation (reverse complement
     * it and flip flop the start and end sites).  Called from NucSeq
     * when it's reverse complemented
     * @param nucseqLength
     */
    void invert(int nucseqLength){
        isForwardStrand = !isForwardStrand;
        int s = start;
        start = nucseqLength - end;
        end = nucseqLength - s;
    }

    /**
     * Get the approriate color for the annoation
     * @return either the forward or reverse color depending
     * on the orientation of the annotation
     */
    public Color getColor() {
        if ( isForwardStrand ) {
            return forwardColor;
        }
        return reverseColor;
    }

    /**
     * Get the forward color as an integer code
     * @return an integer of the Color
     */
    public int getForwardColorAsInt() {
        return forwardColor.getRGB();
    }
    /**
     * Get the reverse color as an integer code
     * @return an integer of the Color
     */
    public int getReverseColorAsInt() {
        return reverseColor.getRGB();
    }
}
