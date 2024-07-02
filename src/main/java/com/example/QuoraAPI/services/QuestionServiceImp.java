package com.example.QuoraAPI.services;

import com.example.QuoraAPI.dto.*;
import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.models.Topic;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.AnswerRepository;
import com.example.QuoraAPI.repositories.QuestionRepository;
import com.example.QuoraAPI.repositories.TopicRepository;
import com.example.QuoraAPI.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.FetchNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImp implements QuestionService{

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    private final TopicRepository topicRepository;

    private final AnswerRepository answerRepository;
    public QuestionServiceImp(QuestionRepository questionRepository, UserRepository userRepository, TopicRepository topicRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.answerRepository = answerRepository;
    }
    @Override
    public CreateQuestionResponseDto createQuestion(CreateQuestionDto questionInfo) {
        try {
            Optional<Question> existingQuestion = questionRepository.findByTopic(questionInfo.getTitle());
            if (existingQuestion.isPresent()) {
                throw new IllegalArgumentException("Question with the same title already exists.");
            }
            User user = userRepository.findById(questionInfo.getUserId()).orElseThrow(() -> new EntityNotFoundException("user not found"));
            Set<Topic> topics = new HashSet<>();

            for (String topic : questionInfo.getTopics()) {
                Optional<Topic> currentTopic = topicRepository.findByTopic(topic);
                Topic tempTopic;
                if (currentTopic.isPresent()) {
                    tempTopic = currentTopic.get();
                } else {
                    Topic singleTopic = new Topic(topic);
                    tempTopic = topicRepository.save(singleTopic);
                }
                topics.add(tempTopic);
            }
            Question question = Question.builder()
                    .topic(questionInfo.getTitle())
                    .user(user)
                    .body(questionInfo.getBody())
                    .topicTags(topics)
                    .build();
            Question savedQuestion = questionRepository.save(question);
            for (Topic topic : topics) {
                topic.getQuestionSet().add(savedQuestion);
                topicRepository.save(topic);
            }
            return CreateQuestionResponseDto.builder()
                    .topics(topics.stream().map(Topic::getTopic).collect(Collectors.toList()))
                    .title(savedQuestion.getTopic())
                    .body(savedQuestion.getBody())
                    .userId(savedQuestion.getUser().getId())
                    .build();
        }
        catch (Exception e) {
            if(e.getClass() == EntityNotFoundException.class){
                throw new FetchNotFoundException(e.getMessage(), questionInfo.getUserId());
            }
            if(e.getClass() == IllegalArgumentException.class) {
                throw new FetchNotFoundException(e.getMessage(), questionInfo.getUserId());
            }
            throw new FetchNotFoundException("Unable to fetch, PLease try again later!", questionInfo.getUserId());
        }

    }

    @Override
    @Transactional
    public List<QuestionDTO> searchQuestions(String topic) {

        try {
           Optional<Question> optinalQuestion = questionRepository.findByTopic(topic);
            if(optinalQuestion.isEmpty()) {
                throw new EntityNotFoundException("Question not found");
            }
            Question question = optinalQuestion.get();
            List<String> answerIds = question.getAnswers().stream()
                    .map(Answer::getAnswer)
                    .collect(Collectors.toList());

            List<String> topicNames = question.getTopicTags().stream()
                    .map(Topic::getTopic)
                    .collect(Collectors.toList());
            return List.of(QuestionDTO.builder()
                    .id(question.getId())
                    .topic(question.getTopic())
                    .body(question.getBody())
                    .userId(question.getUser().getId())
                    .answer(answerIds)
                    .topicTags(topicNames)
                    .build());
        }
        catch (Exception e) {
            throw new FetchNotFoundException("Unable to fetch, PLease try again later!", topic);
        }

    }

    @Override
    public CreateAnswerResponseDto createAnswer(String questionId, CreateAnswerDto answerDetails) {
        try {
            Optional<Question> optionalQuestion = questionRepository.findById(questionId);
            if(optionalQuestion.isEmpty()) {
                throw new EntityNotFoundException("Question not found");
            }
            Optional<User> optionalUser = userRepository.findById(answerDetails.getUserId());
            if(optionalUser.isEmpty()) {
                throw new EntityNotFoundException("User not found");
            }
            Question question = optionalQuestion.get();
            User user = optionalUser.get();
            Answer answer = Answer.builder()
                    .user(user)
                    .answer(answerDetails.getText())
                    .questionId(question)
                    .build();
            Answer response = answerRepository.save(answer);
            question.getAnswers().add(answer);
            user.getAnswers().add(answer);
            questionRepository.save(question);
            userRepository.save(user);
            return  CreateAnswerResponseDto.builder()
                    .userId(response.getUser().getId())
                    .id(response.getId())
                    .answer(response.getAnswer())
                    .questionId(answer.getQuestionId().getId())
                    .build();
        }
        catch (Exception e) {
            if(e.getClass() == EntityNotFoundException.class){
                throw new FetchNotFoundException(e.getMessage(), answerDetails.getUserId());
            }
            throw new FetchNotFoundException("Unable to create, PLease try again later!", answerDetails.getUserId());
        }

    }

}
