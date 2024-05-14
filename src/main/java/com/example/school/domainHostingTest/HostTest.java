package com.example.school.domainHostingTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HostTest {
    @GetMapping("/")
    @ResponseBody
    public String Test() {
        return "Test";
    }

}
