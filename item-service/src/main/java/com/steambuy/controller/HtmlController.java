package com.steambuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {

    @RequestMapping("{name}.html")
    public String toHtml(@PathVariable String name){
        return name;
    }
}
