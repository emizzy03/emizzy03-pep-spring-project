package com.example.service;

import java.util.List;
import java.util.Optional;

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


        if(account.getUsername().trim().isEmpty() && account.getPassword().length() < 4){
            return null;
        }
        return accountRepository.save(account);
    }
    //find accounts
    public Account login(String username, String password){
    List<Account> loginAccount = accountRepository.findByUsername(username);
        for(Account num : loginAccount){
            if(num.getUsername().equals(username) & num.getPassword().equals(password)){
                return num;
              }
        }
     

      return null;
    }



}
