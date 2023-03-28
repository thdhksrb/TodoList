package com.multicampus.myproject;

import com.multicampus.myproject.jdbc.DTO;
import com.multicampus.myproject.jdbc.IDAO;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Controller
public class MyController {

    @Autowired
    private IDAO dao;

    @RequestMapping("/")
    public String root() throws Exception{

        return "redirect:/list";
    }



    @RequestMapping("/list")
    public String listPage(HttpServletRequest request, Model model){


        String sFinished = request.getParameter("finished");
        String sTypes[] = request.getParameterValues("types");
        String sKeyword = request.getParameter("keyword");
        String sFrom = request.getParameter("from");
        String sTo = request.getParameter("to");

        System.out.println("finished: "+ sFinished);

//        for(int i=0;i<sTypes.length;i++){
//            System.out.println(sTypes[i]);
//        }
        System.out.println("keyword: "+ sKeyword);
        System.out.println("from: "+ sFrom);
        System.out.println("to: "+ sTo);

        Map<String,Object> map = new HashMap<>();

        if((sKeyword!=null) && (sTypes[0]!=null) &&
                (sFrom!=null) && (sTo!=null)){
            System.out.println("searching");

            if(sFinished==null){
                map.put("finished",false);
            }else{
                map.put("finished",true);
            }

            map.put("keyword",sKeyword);
            map.put("from",sFrom);
            map.put("to",sTo);

            if(sTypes.length==2){
                model.addAttribute("list",dao.selectDao2(map));
            }else{

                if(sTypes.equals("t")){
                    map.put("types","title");
                }else if(sTypes.equals("w")){
                    map.put("types","writer");
                }
                model.addAttribute("list",dao.selectDao(map));
            }



        }else{

            model.addAttribute("list",dao.listDao());
        }


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




