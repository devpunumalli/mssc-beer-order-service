package com.dev.brewery.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerOrderPagedList extends PageImpl {
    public BeerOrderPagedList(List content, Pageable pageable, long total) {
        super(content, pageable, total);
    }


    public BeerOrderPagedList(List<BeerOrderDto> content) {
        super(content);
    }
}
