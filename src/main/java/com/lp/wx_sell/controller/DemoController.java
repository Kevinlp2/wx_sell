package com.lp.wx_sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liu
 * @date 2019/4/15 14:30
 */
@RestController
@Slf4j
public class DemoController {

    @GetMapping("/hello")
    public String hello(){
        log.info( "info->{}","123456" );
        return "hello spring";
    }
}
