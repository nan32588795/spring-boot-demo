package co.jp.amazawa.training.demo.controller;

import co.jp.amazawa.training.demo.auth.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello {

    @GetMapping
    public String hello(@RequestAttribute("user") User user) {
        return "hello " + user.getName();
    }

}
