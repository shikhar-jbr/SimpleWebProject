package com.broadway.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class UploadController {

    @GetMapping("/upload")
    public String upload(){



        return "uploadForm";

    }

    @PostMapping("/upload")
    public String saveImage(@RequestParam("file") MultipartFile image, Model model, HttpSession session) throws IOException {

        //        check session/valid user

        if(session.getAttribute("user")==null){
            return "loginForm";
        }


        if(!image.isEmpty()){
           FileOutputStream fout= new FileOutputStream("src/main/resources/static/images"+image.getOriginalFilename());

           fout.write(image.getBytes());
           fout.close();

           model.addAttribute("msg","upload success");

           return "uploadForm";
       }else{
           model.addAttribute("msg","upload unsuccessful");
           return "uploadForm";
       }



    }

}
