package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
	@Modifying
	//어떤 쿼리가 작동할지 알려줘야함 Dto와 순서가 맞아야함
	@Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3,  now())", nativeQuery = true)
	int mSave(int userId, int boardId, String content);//JDBC가 쿼리문을 실행 후 업데이트 된 행의 개수를 리턴해주기때문에 int를 씀
}
