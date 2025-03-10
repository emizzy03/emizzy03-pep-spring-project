package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    List<Message> findByPostedBy (int id);

    @Modifying
    @Query("DELETE FROM Message m WHERE m.messageId = :messageId")
    int delMessage(@Param("messageId")int id);

    @Modifying
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.messageId = :messageId")
    int UpdateMessage(@Param("messageId")int id, @Param("messageText")String messageText);



}
