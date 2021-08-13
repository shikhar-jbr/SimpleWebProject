package com.broadway.springdemo.controller;

import com.broadway.springdemo.model.User;
import com.broadway.springdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value="/")
    public String getLogin(){

        return "loginForm";

    }

    @PostMapping(value = "/login")
    public String userLogin(@ModelAttribute User u, Model model, HttpSession session){

        u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
//        User usr= userRepository.userLogin(u.getUsername(), u.getPassword());
        User usr= userRepository.findByUsernameAndPassword(u.getUsername(),u.getPassword());

        if(usr!=null){

            session.setAttribute("user", usr);
            session.setMaxInactiveInterval(200);
            model.addAttribute("uname",usr.getFname());
            return "home";
        }

        model.addAttribute("error","user not found");
        return "loginForm";
    }

    @GetMapping(value = "/register")
    public String getSignUp(){

        return "register";

    }

    @PostMapping(value = "/register")
    public String postRegister(@ModelAttribute User u){

        u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
        userRepository.save(u);

        return "loginForm";

    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session){

//        session kill
        session.invalidate();

        return "loginForm";

    }

    @GetMapping(value = "/home")
    public String home(){
        return "home";
    }

    @GetMapping(value = "/profile")
    public String profile(HttpSession session){

        if(session.getAttribute("user")==null){
            return "loginForm";
        }

        return "profile";

    }

}
