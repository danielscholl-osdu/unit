package org.opengroup.osdu.unitservice.v2.model;

import org.opengroup.osdu.unitservice.v2.api.CatalogLastModified;

public class CatalogLastModifiedImpl implements CatalogLastModified {
    private String lastModified;

    public CatalogLastModifiedImpl() {
    }

    public CatalogLastModifiedImpl(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModified() {
        return this.lastModified;
    }
}
