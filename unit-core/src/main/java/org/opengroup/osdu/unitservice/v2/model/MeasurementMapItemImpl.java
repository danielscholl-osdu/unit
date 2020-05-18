package org.opengroup.osdu.unitservice.v2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.opengroup.osdu.unitservice.v2.api.MeasurementMapItem;

import java.util.Map;
import java.util.logging.Logger;

/**
 * A class defines the mapping state between two {@link MeasurementImpl}s
 */
public class MeasurementMapItemImpl implements MeasurementMapItem {
    private static final Logger log = Logger.getLogger( MeasurementMapItemImpl.class.getName() );

    @Expose @SerializedName("fromMeasurementID")
    private String fromMeasurementId;

    @Expose @SerializedName("toMeasurementID")
    private String toMeasurementId;

    @Expose @SerializedName("state")
    private String state;

    @Expose @SerializedName("note")
    private String note;

    private MeasurementImpl fromMeasurement;

    private MeasurementImpl toMeasurement;

    private String fromNamespace;

    private String toNamespace;

    /**
     * Constructor
     */
    public MeasurementMapItemImpl() {}

    /**
     * Constructor
     * @param fromMeasurement from measurement
     * @param toMeasurement to measurement
     * @param state state
     * @param note note or remark
     */
    public MeasurementMapItemImpl(MeasurementImpl fromMeasurement, MeasurementImpl toMeasurement, String state, String note) {
        this.fromMeasurement = fromMeasurement;
        this.toMeasurement = toMeasurement;
        this.state = state;
        this.note = note;
    }

    /**
     * Gets the from{@link MeasurementImpl}.
     * @return  fromMeasurement
     */
    public MeasurementImpl getFromMeasurement() { return this.fromMeasurement; }

    /**
     * Gets the to{@link MeasurementImpl}.
     * @return  toMeasurement
     */
    public MeasurementImpl getToMeasurement() { return this.toMeasurement; }

    /**
     * Gets the 'from' namespace
     *
     * @return The 'from' namespace
     */
    public String getFromNamespace() {
        return this.fromNamespace;
    }

    /**
     * Gets the 'to' namespace
     *
     * @return The 'to' namespace
     */
    public String getToNamespace() {
        return this.toNamespace;
    }

    /**
     * Gets the state definition
     * @return  state definition
     */
    public String getState() { return this.state; }

    /**
     * Gets the note of the mapping
     * @return  note of the mapping
     */
    public String getNote() { return this.note; }

    /**
     * Post deserialization to resolve the measurements based on their Ids
     * @param idMeasurements
     */
    void postDeserialization(Map<String, MeasurementImpl> idMeasurements, String fromNamespace, String toNamespace) {
        if(!idMeasurements.containsKey(fromMeasurementId))
            throw new IllegalArgumentException("Measurement id '" + fromMeasurementId + "' does not have measurement definition associated.");
        if(!idMeasurements.containsKey(toMeasurementId))
            throw new IllegalArgumentException("Measurement id '" + toMeasurementId + "' does not have measurement definition associated.");

        fromMeasurement = idMeasurements.get(fromMeasurementId);
        toMeasurement = idMeasurements.get(toMeasurementId);
        this.fromNamespace = fromNamespace;
        this.toNamespace = toNamespace;
    }
}
