package org.opengroup.osdu.unitservice.v2.model.extended;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.opengroup.osdu.unitservice.v2.helper.Utility;

public class UnitSystemEssenceImpl {

    @Expose @SerializedName("ancestry")
    private String ancestry;

    /**
     * Empty constructor is needed for deserialization
     */
    private UnitSystemEssenceImpl() {}

    public UnitSystemEssenceImpl(String ancestry) { this.ancestry = ancestry; }

	/**
	 * get the from unit essence
	 * @return the from unit essence
	 */
	@JsonProperty("ancestry")
	public String getAncestry() {
		return ancestry;
	}

    public String toJsonString() {
        return Utility.toJsonString(this);
    }

    @Override
    public int hashCode() {
        return ancestry == null? 0 : ancestry.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof UnitSystemEssenceImpl))
            return false;

        UnitSystemEssenceImpl other = (UnitSystemEssenceImpl) obj;
        return this == other || this.hashCode() == other.hashCode();
    }

}
