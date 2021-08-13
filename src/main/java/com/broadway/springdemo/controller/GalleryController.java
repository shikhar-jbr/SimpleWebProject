package com.broadway.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class GalleryController {

    @GetMapping("/gallery")
    public String gallery(Model model, HttpSession session){

//        check session/valid user

        if(session.getAttribute("user")==null){
            return "loginForm";
        }

        String[] imageNameList= new File("src/main/resources/static/images").list();

        model.addAttribute("imgList",imageNameList);

        return "gallery";

    }

}
