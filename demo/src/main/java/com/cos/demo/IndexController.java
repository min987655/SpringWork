package com.cos.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

// 어노테이션 주석을 등록해야 찾아줌
// 스프링이 프로젝트 시작할 때 어노테이션 주석을 찾아 메모리에 띄움
@Controller
public class IndexController {
   
	@Autowired
	Test t;
	
	@GetMapping("/index")
	public String home() {
		return "index";
	}
	
	
	// http://localhost:8080/
	// 디폴트 ViewResolver : @ResponseBody 안적을 시 return하는 Hello라는 파일을 찾음. = 리퀘스트디스패쳐
	// @ResponseBody 데이터를 찾아서 찾아서 돌려줌 = 프린트라이트
	@GetMapping({"","/"})
	public @ResponseBody String index() { 
		System.out.println(t.num);
		return "Hello"; // ViewResolver
	}
	
	// form으로 들어오는 모든 데이터에 적용 가능
	// x-www-form-urlencoded : key-value
	// 리퀘스트 겟 파라메터의 기능을 내부에 가지고 있음.
	// 주소로 받기 때문에 함수 이름은 중요하지 않음.
	@PostMapping("/form")
	public @ResponseBody String form(String username, String password, String email) {
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
		return "User"; // ViewResolver
	}
	
    // 오브젝트가 넘어오면 오브젝트를 받아 줌 
	@PostMapping("/form/model")
	public @ResponseBody String formModel(User user) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		return "User"; // ViewResolver
	}
	
	// 주소가 똑같지만 요청방식이 다르기때문에 찾을 수 있음
	// 파라메터로 받으면 다시 모델을 만들어서 디비에 넣어야하기 때문에 오브젝트로 받는것이 좋음
	@GetMapping("/form/model")
	public @ResponseBody String formModelGet(User user) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		return "User"; // ViewResolver
	}
	
	// json => { "key" : 값 }
	//﻿ 스프링은 기본적으로 key=value 형태를 파싱함.
	// json 타입으로 들어온 데이터는 파싱하지 못함.﻿
	// 잭슨바인더 : @RequestBody가 걸려있어야 동작. 
	//            요청과 응답시에 동작하며 json 파싱해줌. 
	//            오브젝트가 리턴될 때만 작동 함.
	// 응답시 기본적으로 ViewResolver 동작하여 파일을 찾음.
	// @RequestBody을 적용하면 오브젝트 받음
	// Post. request : 스프링은 바디 데이터를 json으로 잡고 @RequestBody로 파싱하여 오브젝트 데이터 받기
	// 디비에서 받은 데이터 응답할 때 @ResponseBody 걸어서 json으로 응답 함
	// 통신은 모두 json으로 흐름
	@PostMapping("/json/model")
	public @ResponseBody User jsonModel(@RequestBody User user) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		return user; // ViewResolver 작동 금지 => Jackson 작동
	}
	
}
