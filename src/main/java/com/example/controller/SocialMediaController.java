package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use
 * the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations.
 * You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */
@Controller
public class SocialMediaController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Account> create_account(@RequestBody Account account) {
        List<Account> findUsername = accountRepository.findByUsername(account.getUsername().trim());
        if (!findUsername.isEmpty()) {
            return ResponseEntity.status(409).body(account);
        }
        Account createAccount = accountService.insertAccount(account);
        if (createAccount == null) {
            return ResponseEntity.status(400).body(account);
        }

        return ResponseEntity.status(200).body(createAccount);

    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) {
        Account loginAccount = accountService.login(account.getUsername(), account.getPassword());

        if (loginAccount == null) {
            return ResponseEntity.status(401).body(loginAccount);
        }
        return ResponseEntity.status(200).body(loginAccount);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> create_message(@RequestBody Message message) {
        Message creatMessage = messageService.createMessage(message);
        if (creatMessage == null) {
            return ResponseEntity.status(400).body(creatMessage);
        }
        return ResponseEntity.status(200).body(creatMessage);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<?> getMessageById(@PathVariable("message_id") int id) {
        if(messageService.findMessageById(id).isEmpty()){
            return ResponseEntity.status(200).body("");
        }
        return ResponseEntity.status(200).body(messageService.findMessageById(id));
        
    }


    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<?>deleteMessage(@PathVariable("message_id") int id){
         int num = messageService.delMessage(id);
        if(num == 0){
        return ResponseEntity.status(200).body("");
    }
    return ResponseEntity.status(200).body(num);
    }
    

     @PatchMapping("/messages/{message_id}")
     public ResponseEntity<?> updatedMessageById(@PathVariable("message_id") int id, @RequestBody Message message) {
       boolean c =messageService.UpdateMessage(id, message);

       if(c== true){
        return ResponseEntity.status(200).body(1);

       }
       return ResponseEntity.status(400).body("");
     }

     
     @GetMapping("/accounts/{account_id}/messages")
     public ResponseEntity<List<Message>> getMessageByAccountId(@PathVariable("account_id") int accountId) {
         List<Message> message = messageService.getAllMessages(accountId);
        return ResponseEntity.status(200).body(message);
        
     }
}
