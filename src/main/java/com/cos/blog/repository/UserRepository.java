package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//Data Access Object
//자동으로 bean에 등록됨 : @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{ // User 테이블이 관리하는 repo이고, pKey는 int다
	//JPA naming 쿼리 전략
	
	//SELECT * FROM USER WHERE username = ?1 and password = ?2;
	User findByUsernameAndPassword(String username, String password);
	
	////OR
//	@Query(value = "SELECT * FROM USER WHERE username = ?1 and password = ?2", nativeQuery = true)
//	User login(String username, String password);
}
