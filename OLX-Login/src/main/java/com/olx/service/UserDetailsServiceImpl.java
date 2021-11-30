package com.olx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olx.entity.UserEntity;
import com.olx.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository loginRepository;


	@Autowired
	PasswordEncoder passwordEncoder;


	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		UserEntity userEntity = loginRepository.findByUserName(userName); // need to add the check there should be only one unique user name
		
		if(userEntity != null)
		{
		List<GrantedAuthority> grantedAuthority = new ArrayList<>();
		grantedAuthority.add(new SimpleGrantedAuthority(userEntity.getRoles()));
		org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
				userName, passwordEncoder.encode(userEntity.getPassword()), grantedAuthority);
		return user;
		}
		else {
			throw new UsernameNotFoundException(userName);
		}
	}
}
