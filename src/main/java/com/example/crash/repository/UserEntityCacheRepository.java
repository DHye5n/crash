package com.example.crash.repository;

import com.example.crash.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserEntityCacheRepository {

    private final RedisTemplate<String, UserEntity> userEntityRedisTemplate;

    public void setUserEntityCache(UserEntity userEntity) {

        String redisKey = getRedisKey(userEntity.getUsername());

        userEntityRedisTemplate.opsForValue().set(redisKey, userEntity);
    }

    public Optional<UserEntity> getUserEntityCache(String username) {

        String redisKey = getRedisKey(username);

        UserEntity userEntity = userEntityRedisTemplate.opsForValue().get(redisKey);

        return Optional.ofNullable(userEntity);
    }

    private String getRedisKey(String username) {
        return "user:" + username;
    }
}
