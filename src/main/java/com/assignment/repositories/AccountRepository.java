package com.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    @Query(value="SELECT count(acc) FROM Account acc where acc.username = :username")
    public int existsByUsername(@Param("username") String username);
    
    @Query(value="SELECT count(acc) FROM Account acc where acc.email = :email")
    public int existsByEmail(@Param("email") String email);
    
    @Query(value = "SELECT acc FROM Account acc where acc.id = :id")
    public Account findByAccId(@Param("id") int id);
    
    @Query(value="SELECT acc FROM Account acc where acc.username = :username")
    public Account findByUsername(@Param("username") String username);
    
    @Query(value="SELECT acc FROM Account acc where acc.email = :email")
    public Account findByEmail(@Param("email") String email);
    
    @Query(value="SELECT acc FROM Account acc where acc.token = :token")
    public Account findByToken(@Param("token") String token);
    
    @Query(value="SELECT acc FROM Account acc where acc.verifyCode = :verifyCode")
    public Account findByVerifyCode(@Param("verifyCode") String verifyCode);
    
}
