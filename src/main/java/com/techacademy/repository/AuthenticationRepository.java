package com.techacademy.repository;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AuthenticationRepository extends JpaRepository<Authentication, String> {

}





