package com.multicampus.myproject.jdbc;

import com.multicampus.myproject.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IDAO {
    public DTO viewDao(String tno);
    public int writeDao(Map<String,String> map);
    public int deleteDao(String tno);
    public int modifyDao(String tno,String title,String duDate, boolean finished);
    public List<DTO> pageList(Criteria criteria);
    public int articleCount(Criteria criteria);

}
