package org.opengroup.osdu.unitservice.v2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.opengroup.osdu.unitservice.v2.api.MapState;

/**
 * An class defines the mapping state between units or measurements.
 */
public class MapStateImpl implements MapState {
    @Expose @SerializedName("state")
    private String state;

    @Expose @SerializedName("description")
    private String description;

    @Expose @SerializedName("source")
    private String source;

    /**
     * Constructor
     */
    public MapStateImpl() {}

    /***
     * Constructor
     * @param state         state definition.
     * @param description   description of the state definition.
     * @param source        link of the state definition.
     */
    public MapStateImpl(String state, String description, String source) {
        this.state = state;
        this.description = description;
        this.source = source;
    }

    /**
     * Gets the state definition. The well-known states include:
     * <ul>
     *     <li>identical</li>
     *     <li>precision</li>
     *     <li>corrected</li>
     *     <li>conversion</li>
     *     <li>conditional</li>
     *     <li>unsupported</li>
     *     <li>different</li>
     *     <li>unresolved</li>
     * </ul>
     * @return  state definition
     */
    public String getState() { return this.state; }

    /**
     * Gets the description of the state definition.
     * @return  description of the state definition.
     */
    public String getDescription() { return this.description; }

    /**
     * Gets the source of the state definition
     * @return link of the state definition
     */
    public String getSource() { return this.source; }
}
