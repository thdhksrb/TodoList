package com.multicampus.myproject.paging;

import lombok.Data;

@Data
public class Criteria {

    private int currentPage;    // 현재 페이지
    private int recordsPerPage; // 페이지당 출력할 개수

    private String keyword;
    private String types;
    private boolean finished;
    private String from;
    private String to;
    private int startNum;

    public Criteria(){
        this.currentPage = 1;
        this.recordsPerPage = 5;
        this.startNum = (currentPage-1)*recordsPerPage;

    }


    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        this.startNum = (this.currentPage - 1) * this.recordsPerPage;

    }




}
