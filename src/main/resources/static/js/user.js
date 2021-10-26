let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{	//function(){}안쓰는 이유 : this를 바인딩 하기위해서
			this.save();
		});
	},
	
	save: function(){
		//alert('user의 save 함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		//console.log(data);
		// ajax 호출 시 default가 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바오브젝트로 변환해줌
		$.ajax({
			//object
			type : "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
			dataType: "json" //요청을 서버로 하고, 응답이 왔을 때 기본적으로 string인데 생긴게 json이라면 => javascript object로 변경해줌
		}).done(function(res){
			//req가 성공이면
			alert("회원가입이 완료되었습니다");
			location.href = "/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
			//req가 실패이면
		});	//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청하기
		
	}
}
index.init();