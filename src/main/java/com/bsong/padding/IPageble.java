package com.bsong.padding;

import com.bsong.sort.Sorter;

public interface IPageble {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();
}
