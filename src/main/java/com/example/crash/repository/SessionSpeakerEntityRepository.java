package com.example.crash.repository;

import com.example.crash.model.entity.SessionSpeakerEntity;
import com.example.crash.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionSpeakerEntityRepository extends JpaRepository<SessionSpeakerEntity, Long> {


}
