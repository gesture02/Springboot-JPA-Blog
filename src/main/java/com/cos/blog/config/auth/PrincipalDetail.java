package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다. 그때 저장되는게 아래꺼임
public class PrincipalDetail implements UserDetails{
	
	private User user;	//객체를 품고있음 // 컴포지션

	public PrincipalDetail(User user) {
		this.user = user;
	}
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	//계정이 만료되지 않았는지 리턴한다 (true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	//계정이 잠겼는지를 리턴한다 (true:안잠김)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	//비밀번호가 만료되지 않았는지 리턴한다 (true:만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	//계정 활성화가 되어있는지 아닌지 (true:활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	//계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 지금은 하나만씀)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				return "ROLE_"+user.getRole();	// 앞에 꼭 ROLE_ 넣어야함 (규칙)
//			}
//		});
		
		//파라미터가 Gra- 인걸 알고 그안에 함수가 하나뿐이니 람다식으로 가능ㄹ
		collectors.add(() -> {
			return "ROLE_"+user.getRole();
		});
		
		return collectors;
	}
	
}
