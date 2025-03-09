package com.example.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    //Create accounts
    public Account insertAccount(Account account) {
        if (!account.getUsername().trim().isEmpty() && account.getPassword().length() >= 4
                && accountRepository.findByUsername(account.getUsername()) == null) {
            return accountRepository.save(account);
        }
        return null;
    }
    //find accounts
    public List<Account> login(String username, String password){
       return accountRepository.findByUsernameAndPassword(username, password);
       
    }



}
