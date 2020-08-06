/**
 * Copyright 2018 Schlumberger. All Rights Reserved.
 */
package org.opengroup.osdu.unitservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.opengroup.osdu.unitservice.model.UnitEssenceImpl;

/**
 *  ConversionABCDRequest is class which encapsulates the request body for a abcd based converion object.
 */
public class ConversionABCDRequest {

    private UnitEssenceImpl fromUnitEssence;
    private UnitEssenceImpl toUnitEssence;
    private String fromUnitPersistableReference;
    private String toUnitPersistableReference;

    /**
     * Constructor
     */
    public ConversionABCDRequest(UnitEssenceImpl fromUnitEssence, UnitEssenceImpl toUnitEssence,
                                 String fromUnitPersistableReference, String toUnitPersistableReference) {
        this.fromUnitEssence = fromUnitEssence;
        this.toUnitEssence = toUnitEssence;
        this.fromUnitPersistableReference = fromUnitPersistableReference;
        this.toUnitPersistableReference = toUnitPersistableReference;
    }

    /**
     * Constructor
     */
    public ConversionABCDRequest() {
    }

    /**
     * get the from unit essence
     * @return the from unit essence
     */
    @JsonProperty("fromUnit")
    public UnitEssenceImpl getFromUnitEssence() {
        UnitRequest request = new UnitRequest(fromUnitEssence, fromUnitPersistableReference);
        return request.getUnitEssence();
    }

    /**
     * get the from unit essence
     * @return the from unit essence
     */
    @JsonProperty("toUnit")
    public UnitEssenceImpl getToUnitEssence() {
        UnitRequest request = new UnitRequest(toUnitEssence, toUnitPersistableReference);
        return request.getUnitEssence();
    }

    /**
     * Get the from fromUnitPersistableReference
     * @return fromUnitPersistableReference
     */
    @JsonProperty("fromUnitPersistableReference")
    public String getFromUnitPersistableReference() {
        return this.fromUnitPersistableReference;
    }

    /**
     * Get the from toUnitPersistableReference
     * @return toUnitPersistableReference
     */
    @JsonProperty("toUnitPersistableReference")
    public String getToUnitPersistableReference() {
        return this.toUnitPersistableReference;
    }

}

