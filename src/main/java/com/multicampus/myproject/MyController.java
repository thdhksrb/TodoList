package com.multicampus.myproject;

import com.multicampus.myproject.jdbc.IDAO;
import com.multicampus.myproject.paging.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.multicampus.myproject.paging.PageDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MyController {

    @Autowired
    private IDAO dao;
    

    @RequestMapping("/")
    public String root() throws Exception{

        return "redirect:/list";
    }



    @RequestMapping("/list")
    public String listPage(Criteria criteria,HttpServletRequest request, Model model){

        model.addAttribute("list", dao.pageList(criteria));
        model.addAttribute("paging",new PageDTO(criteria,dao.articleCount(criteria)));


        return "list";
    }

    @RequestMapping("/read")
    public String view(String tno,Model model){
        model.addAttribute("dto",dao.viewDao(tno));
        return "read";
    }

    @RequestMapping("/modify")
    public String modf(String tno,Model model){
        model.addAttribute("dto",dao.viewDao(tno));
        return "modify";
    }

    @RequestMapping(value = "/modify", method= RequestMethod.POST)
    public String modify(String tno, String title, String dueDate, String finished,Model model){
        System.out.println(finished);
        boolean finish = false;

        try{
            if(finished.equals("on")){
                finish = true;
            }
        }catch (NullPointerException ne){

        }

        System.out.println(finish);
        model.addAttribute("dto",dao.modifyDao(tno, title, dueDate, finish));
        return "redirect:/list";
    }

    @RequestMapping(value="/remove",method=RequestMethod.POST)
    public String delete(String tno) {
        dao.deleteDao(tno);
        return "redirect:/list";
    }

    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String register(String title, String dueDate, String writer) {
        Map<String,String> map = new HashMap<>();
        map.put("title",title);
        map.put("dueDate",dueDate);
        map.put("writer",writer);
        dao.writeDao(map);
        return "redirect:/list";
    }

    @RequestMapping(value="/register",method=RequestMethod.GET)
    public String registerform() {
        return "register";
    }
}




