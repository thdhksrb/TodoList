package com.multicampus.myproject.jdbc;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IDAO {
    public List<DTO> listDao();
    public DTO viewDao(String tno);
    public int writeDao(Map<String,String> map);
    public int deleteDao(String tno);
    public int articleCount();
    public int modifyDao(String tno,String title,String duDate, boolean finished);
    public List<DTO> selectDao(Map<String,Object> map);
    public List<DTO> selectDao2(Map<String,Object> map);

}
