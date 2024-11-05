package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user")
    public List<User> userApi(){
        return userService.getUserApi();
    }

    // 해당 url 경로에 form양식의 데이터를 보내면 매개변수 user에 담아돈다.
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user/create")
    public String createUserPro(User user){
        userService.createUser(user);

        //post를 하고난 뒤 표시할 url
        // 리액트의 서버 포트가 3000을 사용하므로 localhost:3000
        return "redirect:http://localhost:3000/";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user/delete")
    public String userDeletePro(@RequestParam("id") Integer id){
        userService.userDelete(id);

        return "redirect:http://localhost:3000/";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user/update")
    public String updateUserPro(@RequestParam("id") Integer id,@ModelAttribute User user){
        User userTemp = userService.userDetail(id);
        userTemp.setIntroduce(user.getIntroduce());

        userService.createUser(userTemp);
        return "redirect:http://localhost:3000/";
    }
}
