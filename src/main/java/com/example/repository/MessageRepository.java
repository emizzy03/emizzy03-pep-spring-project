package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
    List<Message> findByAccountId (int id);

    @Modifying
    @Query("DELETE from message WHERE messageId = :id")
    int delMessage(@Param("id")int id);

    @Modifying
    @Query("UPDATE message SET messageText = :text WHERE messageId = :id")
    int UpdateMessage(@Param("id")Long id, @Param("text")String messageText);



}
