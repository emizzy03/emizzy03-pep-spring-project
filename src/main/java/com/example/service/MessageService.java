package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
   

    // create new messages
    public Message createMessage(Message message) {
        if(messageRepository.findById(message.getPostedBy()).isPresent()){
        if (!message.getMessageText().isEmpty()
                && message.getMessageText().length() <= 255) {
            return messageRepository.save(message);
        }
    }
        return null;
    }

    // get all messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // get message by id
    public Optional<Message> findMessageById(int id) {
       return messageRepository.findById(id);
    }

    // get number of afftected deleted rows
    public int delMessage(int id) {
    int count =0;
     if(messageRepository.existsById(id) ){
        count++;
     }
     return count;
     


    }

    // get number of afffected updated rows
    public boolean UpdateMessage(int id, Message messageText) {
        Optional<Message> t = messageRepository.findById(id);
      
       if(messageText.getMessageText().isBlank() || messageText.getMessageText() == "" || messageText.getMessageText().trim().length() == 0 || messageText.getMessageText().isEmpty()){
        return false;
        
    }
    else if(t.isPresent() && !messageText.getMessageText().trim().isEmpty() && messageText != null && messageText.getMessageText().length() <= 255){
        t.get().setMessageText(messageText.getMessageText());
        messageRepository.save(t.get());
      return true;
    }
      
        return false;
    }

    //get all by accountid
     public List<Message> getAllMessages(int accountId) {
         return messageRepository.findByPostedBy(accountId);
     }

}
