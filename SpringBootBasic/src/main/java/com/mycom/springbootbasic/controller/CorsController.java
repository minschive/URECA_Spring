package com.mycom.springbootbasic.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@CrossOrigin("*") // 이 컨트롤러가 처리하는 모든 요청에 대해서 CORS 에 필요한 Header 를 내려 준다.
// Get, Post : 가장 일반적인 요청으로 백엔드가 이 요청을 지원하는지 여부 확인 없이 바로 요청
// Put, Delete : 이전에 사용하지 않던 요청이어서 일반적이지 않은 요청, 백엔드가 이 요청을 지원하는지 확인 (Options 요청 <= pre-flight)

//@CrossOrigin(
//        origins = "http://127.0.0.1:5500/",
//        allowCredentials = "true",
//        allowedHeaders = "*",
//        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD}
//)
public class CorsController {

    @GetMapping("/cors")
    public Map<String, String> getCors(@RequestParam("param") String param) {
        System.out.println("get : " + param);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @PostMapping("/cors")
    public Map<String, String> postCors(@RequestParam("param") String param) {
        System.out.println("post : " + param);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @PutMapping("/cors/{num}")
    public Map<String, String> putCors(@PathVariable("num") int num) {
        System.out.println("put : " + num);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @DeleteMapping("/cors/{num}")
    public Map<String, String> deleteCors(@PathVariable("num") int num) {
        System.out.println("delete : " + num);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }
}
