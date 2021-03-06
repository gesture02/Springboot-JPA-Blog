package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량 데이터
	private String content; // 섬머노트 라이브러리 사용 <html>태그가 섞여서 디자인됨
	
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER)	//Many : Board, One : User : 한명의 유저가 여러개의 게시글 작성 가능
	@JoinColumn(name = "userId")
	private User user; // DB는 오브젝트를 저장x jpa는 가능 ->  jpa가 매핑해줌
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)// mappedBy : 연관관계의 주인이 아님(fk아님) DB에 컬럼 만들지 마라
	//board를 select 할때 join문을 통해 값을 얻기위해 필요한 것 (board는 reply클래스에있는 필드 이름이다.)
	// fetchtype은 기본 lazy이지만 글을 볼때 user, board, reply가 모두 필요하기 때문에 eager로 바꿔야한다.
	// One : Board, Many : reply
	//외부키 필요없음
	@JsonIgnoreProperties({"board"}) // reply안에서 board getter 호출을 막는다 (무한참조 방지)
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
}
