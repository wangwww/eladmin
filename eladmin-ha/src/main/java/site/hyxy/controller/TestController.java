package site.hyxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.hyxy.annotation.AnonymousAccess;
import site.hyxy.task.AIUITask;

/**
 * @Author: 王文文
 * @Date: 2022/7/20 21:32
 */
@RestController
public class TestController {
    @Autowired
    AIUITask aiuiTask;
    
    @GetMapping("/test")
    @AnonymousAccess
    public void test(@RequestParam String location) {
        aiuiTask.getWeather(location);
    }
    
}
