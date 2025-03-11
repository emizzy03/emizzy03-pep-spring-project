package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("messages")
public class SocialMediaController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) {
        Account loginAccount = accountService.login(account.getUsername(), account.getPassword());

        if (loginAccount == null) {
            return ResponseEntity.status(401).body(loginAccount);
        }
        return ResponseEntity.status(200).body(loginAccount);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ResponseEntity<Message> create_message(@RequestBody Message message) {
        Message creatMessage = messageService.createMessage(message);
        if (creatMessage == null) {
            return ResponseEntity.status(400).body(creatMessage);
        }
        return ResponseEntity.status(200).body(creatMessage);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @RequestMapping(value = "/{message_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMessageById(@PathVariable("message_id") int id) {
        return ResponseEntity.status(200).body(messageService.findMessageById(id));
        
    }


    @RequestMapping(value = "/{message_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String>deleteMessage(@PathVariable("message_id") int id){
         int num = messageService.delMessage(id);
         String word = String.valueOf(num);
        if(messageService.delMessage(id) == 0){
        return ResponseEntity.status(200).body("");
    }
    return ResponseEntity.status(200).body(word);
    }
    

     @RequestMapping(value = "/{message_id}", method = RequestMethod.PATCH)
     public ResponseEntity<String> updatedMessageById(@PathVariable("message_id") int id, @RequestBody String message) {
       int c =messageService.UpdateMessage(id, message);
       String word = String.valueOf(c);

       if(c == 0){
        return ResponseEntity.status(200).body("");

       }
       return ResponseEntity.status(200).body(word);
     }

    @RequestMapping(value = "/{account_id}", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getMessageByAccountId(@PathVariable("account_id") int accountId) {
        List<Message> message = messageService.getAllMessages(accountId);
       return ResponseEntity.status(200).body(message);
        
    }
}
