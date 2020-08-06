package org.opengroup.osdu.unitservice.model;

import org.opengroup.osdu.unitservice.interfaces.CatalogLastModified;

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
