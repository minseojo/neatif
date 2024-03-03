package com.minseojo.neatif.controller;

import com.minseojo.neatif.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
/**
 * 추후 컨트롤러를 추가 하면서 AsideController 사라질 예정
 */
public class AsideController {

    private final ItemService itemService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @GetMapping("/notice")
    public String notice() {
        return "index";
    }

    @GetMapping("/review")
    public String review() {
        return "index";
    }


}
