package com.example.crash.service;

import com.example.crash.exception.user.UserAlreadyExistsException;
import com.example.crash.model.entity.UserEntity;
import com.example.crash.model.user.User;
import com.example.crash.model.user.UserAuthenticationResponse;
import com.example.crash.model.user.UserLoginRequestBody;
import com.example.crash.model.user.UserSignUpRequestBody;
import com.example.crash.repository.UserEntityRepository;
import com.example.crash.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return getUserEntityByUsername(username);
    }

    public User signUp(UserSignUpRequestBody userSignUpRequestBody) {
        userEntityRepository.findByUsername(userSignUpRequestBody.getUsername())
                .ifPresent(user -> {throw new UserAlreadyExistsException();
                });

        UserEntity userEntity = userEntityRepository
                .save(UserEntity.of(
                        userSignUpRequestBody.getUsername(),
                        passwordEncoder.encode(userSignUpRequestBody.getPassword()),
                        userSignUpRequestBody.getName(),
                        userSignUpRequestBody.getEmail()));

        return User.from(userEntity);
    }

    public UserAuthenticationResponse login(UserLoginRequestBody userLoginRequestBody) {
        UserEntity userEntity = getUserEntityByUsername(userLoginRequestBody.getUsername());

        if (passwordEncoder.matches(userLoginRequestBody.getPassword(), userEntity.getPassword())) {
            String accessToken = jwtService.generateAccessToken(userEntity);
            return new UserAuthenticationResponse(accessToken);
        } else {
            throw new UserNotFoundException();
        }
    }

    public UserEntity getUserEntityByUsername(String username) {
        return userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }
}
