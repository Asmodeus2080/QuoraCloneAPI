package com.example.QuoraAPI.repositories;

import com.example.QuoraAPI.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {
    public Optional<Topic> findByTopic(String topic);
}
