package com.example.QuoraAPI.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Topic extends BaseModel{


    @Column(nullable = false)
    private String topic;

    @ManyToMany(mappedBy = "topicTags")
    private Set<Question> questionSet;

    public Topic(String topic) {
        this.topic = topic;
        this.questionSet = new HashSet<>();  // Initialize questionSet in the constructor
    }

    @Override
    public String toString() {
        return id + " " + topic + " " + questionSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic1 = (Topic) o;
        return Objects.equals(topic, topic1.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic);
    }
}
