package com.techacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.repository.AuthenticationRepository;

@Service
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;

    @Autowired
    public AuthenticationService(AuthenticationRepository repository) {
        this.authenticationRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Authentication> getAuthenticationList() {
        // リポジトリのfindAllメソッドを呼び出す
        return authenticationRepository.findAll();
    }

    /**Authenticationの更新を行なう */
    @Transactional
    public Authentication setAuthentication(Authentication au) {
        return authenticationRepository.save(au);
    }


}