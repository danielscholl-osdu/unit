/**
 * Copyright 2018 Schlumberger. All Rights Reserved.
 *
 */
package org.opengroup.osdu.unitservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonSyntaxException;
import org.opengroup.osdu.unitservice.helper.Utility;
import org.opengroup.osdu.unitservice.model.UnitEssenceImpl;

/**
 *  UnitRequest is class which encapsulates the request body for a unit object.
 */
public class UnitRequest {

	private UnitEssenceImpl unitEssence;
	private String persistableReference;

	/**
	 * Constructor
	 */
    public UnitRequest()
    {
    }

	/**
	 * Constructor
	 * @param unitEssence The {@link UnitEssenceImpl} or null
	 * @param persistableReference The persistable reference string or null
	 */
    public UnitRequest(UnitEssenceImpl unitEssence, String persistableReference)
    {
		this.unitEssence = unitEssence;
		this.persistableReference = persistableReference;
	}

	/**
	 * get the unit essence
	 * @return the unit essence
	 */
	@JsonProperty("essence")
	public UnitEssenceImpl getUnitEssence() {
		if (this.unitEssence == null) {
			try {
				this.unitEssence = Utility.fromJsonString(this.persistableReference, UnitEssenceImpl.class);
				if (this.unitEssence != null && !this.unitEssence.isValid()) return null;
			} catch (JsonSyntaxException ex) {
				this.unitEssence = null;
			}
		}
		return this.unitEssence;
	}

	/**
	 * Gets the essence as JSON string aka persistable reference
	 * @return the persistable reference string
	 */
	public String getPersistableReference() { return this.persistableReference; }
}

