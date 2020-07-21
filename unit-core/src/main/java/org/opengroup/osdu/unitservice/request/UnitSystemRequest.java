/**
 * Copyright 2018 Schlumberger. All Rights Reserved.
 *
 */
package org.opengroup.osdu.unitservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.opengroup.osdu.unitservice.helper.Utility;
import org.opengroup.osdu.unitservice.model.extended.UnitSystemEssenceImpl;

/**
 *  UnitSystemRequest is class which encapsulates the request body for a unit system object.
 */
public class UnitSystemRequest {

	private UnitSystemEssenceImpl unitSystemEssence;

	@JsonProperty("persistableReference")
	private String persistableReference;

	/**
	 * Constructor
	 */
    public UnitSystemRequest()
    {
    }

    public UnitSystemRequest(UnitSystemEssenceImpl unitSystemEssence) {
    	this.unitSystemEssence = unitSystemEssence;
    	this.persistableReference = null;
	}
	/**
	 * Constructor
	 */
    public UnitSystemRequest(UnitSystemEssenceImpl unitSystemEssence, String persistableReference)
    {
		this.unitSystemEssence = unitSystemEssence;
		this.persistableReference = persistableReference;
	}

	/**
	 * get the UnitSystem essence
	 * @return the UnitSystem essence
	 */
	@JsonProperty("essence")
	public UnitSystemEssenceImpl getUnitSystemEssence() {
		if (unitSystemEssence == null && persistableReference != null) {
			this.unitSystemEssence = Utility.fromJsonString(this.persistableReference, UnitSystemEssenceImpl.class);
		}
		return unitSystemEssence;
	}
}

