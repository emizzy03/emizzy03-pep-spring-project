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
    //create new messages
    public Message createMessage(Message message){
        if(messageRepository.findByPostedBy(message.getPostedBy()) != null){
            return messageRepository.save(message);
        }
        return null;
    }
    //get all messages
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }
    //get message by id
    public Optional<Message> findById(int id){
        return messageRepository.findById(id);
    }
    //get number of afftected deleted rows 
    public int delMessage(int id){
        return messageRepository.delMessage(id);
    }
    //get number of afffected updated rows
    public int UpdateMessage(int id, String message){
        if(messageRepository.existsById(id) && message.length() >= 255){
            return messageRepository.UpdateMessage(id, message);
        }
      return 0;
    }
    //get all by accountid
    public List<Message>getAllMessages(int id){
        return messageRepository.findByPostedBy(id);
    }

}
