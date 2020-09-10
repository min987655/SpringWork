package com.cos.demo;

import lombok.Data;

// 여러번 new 해야 하기 때문에 스프링컨텍스트(싱글톤)에 등록하지 않음.
// 사용시마다 new 해야 함
@Data
public class User {
	private String username;
	private String password;
	private String email;
}