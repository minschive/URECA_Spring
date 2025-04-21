package com.mycom.springbootbasic.controller;

import com.mycom.springbootbasic.dto.CarDto;
import com.mycom.springbootbasic.dto.EmpDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Json 응답 Json 요청 처리
// 모든 응답을 Json 으로 하는 경우라면
//@Controller
//@ResponseBody
@RestController // = @Controller + @ResponseBody
public class JsonController {

    @GetMapping("/string")
    public String m1() {
        System.out.println("/string");
        String str = "안녕하세요!";
        return str;
    }

    @GetMapping("/jsonstring")
    public String m2() {
        System.out.println("/jsonstring");
        String jsonStr = "\"result\":\"success\"";
        return jsonStr;
    }

    // 백엔드에서 JSP 를 사용하지 않으므로 Client 에서 필요한 데이터는 백엔드에서 내려주고
    // Client 에서 적절한 곳 (대체적으로 storage, 쿠키) 에 저장하고, 이를 이용해서 화면 처리를 해야한다.
    @GetMapping("/map")
    public Map<String, String> m3() {
        System.out.println("/map");
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        map.put("name", "홍길동");
        map.put("role", "관리자");
        return map;
    }

    // dto
    @GetMapping("/dto")
    public CarDto m4() {
        System.out.println("/dto");
        CarDto carDto = new CarDto("소나타", 20000, "홍길동");
        return carDto;
    }

    // dto
    @GetMapping("/listdto")
    public List<CarDto> m5() {
        System.out.println("/dto");
        List<CarDto> list = new ArrayList<>();
        list.add(new CarDto("소나타", 20000, "홍길동"));
        list.add(new CarDto("그랜저", 30000, "이길동"));
        list.add(new CarDto("아반떼", 10000, "삼길동"));
        return list;
    }

    // LocalDateTime 객체 json 리턴
    // spring default json converter : jackson
    // jackson vs gson

    // localdatetime
    @GetMapping("/localdatetime")
    public LocalDateTime m6() {
        return LocalDateTime.now();
    }

    // http request 에 json parameter 처리
    @PostMapping("/emp")
    public Map<String, String> m7(@RequestBody EmpDto empDto) {
        System.out.println("/emp");
        System.out.println(empDto);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @PostMapping("/emplist")
    public Map<String, String> m8(@RequestBody List<EmpDto> empList) {
        System.out.println("/emplist");
        empList.forEach( empDto -> System.out.println(empDto) );
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

}
