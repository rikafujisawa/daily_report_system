package com.techacademy.service;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techacademy.repository.AuthenticationRepository;

@Service
public class AuthenticationService {

	private final AuthenticationRepository repository;

	@Autowired
	public AuthenticationService(AuthenticationRepository repository) {
		this.repository = repository;
	}

	// 全件を検索して返す
	public List<Authentication> getAuthenticationList() {
		// リポジトリのfindAllメソッドを呼び出す
		return repository.findAll();
	}
}