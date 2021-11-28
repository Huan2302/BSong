package com.bsong.sort;

public class Sorter {
    private String sortBy;
    private String sortname;

    public Sorter(String sortBy, String sortname) {
        this.sortBy = sortBy;
        this.sortname = sortname;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

}
