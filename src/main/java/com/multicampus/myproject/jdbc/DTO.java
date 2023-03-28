package com.multicampus.myproject.jdbc;

import lombok.Data;

@Data
public class DTO {
    private int tno;
    private String title;
    private String dueDate;
    private String writer;
    private boolean finished;

}
