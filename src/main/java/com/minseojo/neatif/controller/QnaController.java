package com.minseojo.neatif.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class QnaController {
    @GetMapping("/qna")
    public String qna() {
        return "qna";
    }

    @GetMapping("/qna/{id}")
    public String qnaRead(@PathVariable("id") Long id) {
        return "qna_read";
    }
}
