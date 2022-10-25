package com.swp391.ebutler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swp391.ebutler.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    
    Account findByLoginMailAndPassword(String loginMail, String password);

}
