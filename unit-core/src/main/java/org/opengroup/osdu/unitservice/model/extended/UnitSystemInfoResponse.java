package org.opengroup.osdu.unitservice.model.extended;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * A type of class to describe a block of string items
 * */
public class UnitSystemInfoResponse {
    private List<UnitSystemInfo> unitSystemInfoList;
    private int totalCount;
    private int offset;

    /**
     * Empty constructor
     */
    public UnitSystemInfoResponse() {
        this.unitSystemInfoList = new ArrayList<>();
        this.totalCount = 0;
        this.offset = 0;
    }
    /**
     * Constructor with arguments of a list of items.
     * @param items a list of {@link UnitSystemInfo}
     */
    public UnitSystemInfoResponse(List<UnitSystemInfo> items, int totalCount, int offset) {
        if(items == null)
            items = new ArrayList<>();

        this.unitSystemInfoList = items;
        this.totalCount = totalCount;
        this.offset = offset;
    }

    /**
     * Gets the number of items in the result.
     * @return number of items.
     */
    @JsonProperty("count")
    public int getCount() { return this.unitSystemInfoList.size();  }

    /**
     * Gets the list of {@link UnitSystemInfo}
     * @return the list of {@link UnitSystemInfo}
     */
    @JsonProperty("unitSystemInfoList")
    public List<UnitSystemInfo> getUnitSystemInfoList() { return this.unitSystemInfoList; }

    /**
     * Gets the total number of items in the catalog
     * @return the total number
     */
    @JsonProperty("totalCount")
    public int getTotalCount() {return this.totalCount;}

    /**
     * Gets the offset into the list as requested
     * @return the offset as requested
     */
    @JsonProperty("offset")
    public int getOffset() {return this.offset;}
}
