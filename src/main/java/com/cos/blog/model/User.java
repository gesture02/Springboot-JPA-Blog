package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User클래스가 MySQL에 테이블이 생성된다.
// @DynamicInsert //insert할때 null인 필드는 알아서 제거함
public class User {
	
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 프로젝트에 연결된 DB의 넘버링 전략을 따라간다. 오라클이면 시퀀스, MySQL이면 auto_increment
	private int id; //시퀀스, auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username; //아이디
	
	@Column(nullable = false, length = 100) // 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)	// DB는 RoleType이라는게 없으니까
	private RoleType role; //Enum을 쓰는게 좋다. //admin, user, manager라는 권한을 주기 위함
	//Enum을 쓰면 3개중에 하나로 들어갈 수 있도록 도메인을 정할 수 있다.
	
	@CreationTimestamp // 시간이 자동으로 입력된다.
	private Timestamp createDate;
}
