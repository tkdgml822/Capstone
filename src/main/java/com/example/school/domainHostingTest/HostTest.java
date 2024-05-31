package com.example.school.domainHostingTest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HostTest {


    @GetMapping("/")
    @ResponseBody
    public String Test() {
        return "테스트";
    }

}
