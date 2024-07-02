package com.example.QuoraAPI.repositories;

import com.example.QuoraAPI.dto.QuestionDTO;
import com.example.QuoraAPI.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {

    Optional<Question> findByTopic(String topic);

//    @Query("SELECT new com.example.QuoraAPI.dto.QuestionDTO(q.id, q.createdAt, q.updatedAt, q.topic, q.body, q.user.id, " +
//            "q.answers.stream().map(a -> a.id).collect(Collectors.toList()), " +
//            "q.topicTags.stream().map(t -> t.topic).collect(Collectors.toList())) " +
//            "FROM Question q LEFT JOIN q.topicTags t WHERE q.topic = :topic AND t.topic = :topicTag")
//    List<QuestionDTO> findAllByTopicAndTopicTag(@Param("topic") String topic, @Param("topicTag") String topicTag);
//
//    @Query("SELECT new com.example.QuoraAPI.dto.QuestionDTO(q.id, q.createdAt, q.updatedAt, q.topic, q.body, q.user.id, " +
//            "q.answers.stream().map(a -> a.id).collect(Collectors.toList()), " +
//            "q.topicTags.stream().map(t -> t.topic).collect(Collectors.toList())) " +
//            "FROM Question q LEFT JOIN q.topicTags t WHERE q.topic = :topicName")
//    List<QuestionDTO> findAllByTopicName(@Param("topicName") String topicName);
//
//    @Query("SELECT new com.example.QuoraAPI.dto.QuestionDTO(q.id, q.createdAt, q.updatedAt, q.topic, q.body, q.user.id, " +
//            "q.answers.stream().map(a -> a.id).collect(Collectors.toList()), " +
//            "q.topicTags.stream().map(t -> t.topic).collect(Collectors.toList())) " +
//            "FROM Question q LEFT JOIN q.topicTags t WHERE t.topic = :topicTag")
//    List<QuestionDTO> findAllByTopicTag(@Param("topicTag") String topicTag);
}
