package org.opengroup.osdu.unitservice.v2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.opengroup.osdu.unitservice.v2.api.ScaleOffset;
import org.opengroup.osdu.unitservice.v2.helper.Utility;

/**
 * Container class for the y = scale*(x-offset) parameterization.
 */
public class ScaleOffsetImpl implements ScaleOffset {
    @Expose @SerializedName("scale")
    private double scale;

    @Expose @SerializedName("offset")
    private double offset;

    /*
    Constructor for an deserialization instance.
    */
    private ScaleOffsetImpl() {
    }

    public ScaleOffsetImpl(double scale, double offset) {
        this.scale = scale;
        this.offset = offset;
    }

    /**
     * Gets the unit scale factor as in y = scale*(x-offset)
     * @return unit scale factor
     */
	@JsonProperty("scale")
    public double getScale() { return this.scale; }

    /**
     * Gets the offset in the y = scale*(x-offset) parameterization.
     * @return offset coefficient
     */
	@JsonProperty("offset")
    public double getOffset() { return this.offset; }

    /**
     * Checks whether the instance is valid.
     * @return true if neither scale nor offset is @see Double.NaN and scale is none-zero; otherwise, false.
     */
    @JsonIgnore
    public boolean isValid() {
        return !(Double.isNaN(scale) || Double.isNaN(offset) || Utility.isZeroValue(scale));
    }

    @Override
    public int hashCode() {
        int hash = Double.valueOf(scale).hashCode();
        hash = hash * 17 + Double.valueOf(offset).hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof ScaleOffsetImpl))
            return false;

        ScaleOffsetImpl other = (ScaleOffsetImpl) obj;
        return this == other || this.hashCode() == other.hashCode();
    }
}
