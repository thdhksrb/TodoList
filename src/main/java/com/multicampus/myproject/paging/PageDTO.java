package com.multicampus.myproject.paging;

import lombok.Data;

@Data
public class PageDTO {

    private int startPage, endPage;
    private boolean prev,next;

    private int total;
    private Criteria criteria;

    public PageDTO(Criteria criteria, int total){
        this.criteria = criteria;
        this.total = total;

        this.endPage = (int)Math.ceil(criteria.getCurrentPage()/10.0) *10;

        this.startPage = endPage-9;

        this.prev = this.startPage > 1 ;

        int realEnd = (int)(Math.ceil((total*1.0) / criteria.getRecordsPerPage()));

        this.endPage = realEnd <= endPage ? realEnd : endPage;

        this.next = this.endPage < realEnd;


    }
}
