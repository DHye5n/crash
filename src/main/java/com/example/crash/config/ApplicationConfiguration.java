package com.example.crash.config;

import com.example.crash.model.crashsession.CrashSessionCategory;
import com.example.crash.model.crashsession.CrashSessionPostRequestBody;
import com.example.crash.model.sessionspeaker.SessionSpeaker;
import com.example.crash.model.sessionspeaker.SessionSpeakerPostRequestBody;
import com.example.crash.model.user.UserSignUpRequestBody;
import com.example.crash.service.CrashSessionService;
import com.example.crash.service.SessionSpeakerService;
import com.example.crash.service.UserService;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private static final Faker faker = new Faker();

    private final UserService userService;
    private final SessionSpeakerService sessionSpeakerService;
    private final CrashSessionService crashSessionService;
//    private final SlackService slackService;

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                createTestUsers();
                createTestSessionSpeakers(10);
//                slackService.sendSlackNotification();
            }
        };
    }

    private void createTestUsers() {
        userService.signUp(new UserSignUpRequestBody("jayce", "1234", "Dev Jayce", "jayce@crash.com"));
        userService.signUp(new UserSignUpRequestBody("jay", "1234", "Dev Jay", "jay@crash.com"));
        userService.signUp(new UserSignUpRequestBody("rose", "1234", "Dev Rose", "rose@crash.com"));
        userService.signUp(new UserSignUpRequestBody("rosa", "1234", "Dev Rosa", "rosa@crash.com"));

        userService.signUp(new UserSignUpRequestBody(faker.name().name(), "1234", faker.name().fullName(), faker.internet().emailAddress()));
        userService.signUp(new UserSignUpRequestBody(faker.name().name(), "1234", faker.name().fullName(), faker.internet().emailAddress()));
        userService.signUp(new UserSignUpRequestBody(faker.name().name(), "1234", faker.name().fullName(), faker.internet().emailAddress()));
    }

    private void createTestSessionSpeakers(int numberOfSpeakers) {
       List<SessionSpeaker> sessionSpeakers = IntStream.range(0, numberOfSpeakers)
                .mapToObj(i -> createTestSessionSpeaker())
                .collect(Collectors.toList());

       sessionSpeakers.forEach(sessionSpeaker -> {
           int numberOfSessions = new Random().nextInt(4) + 1;
           IntStream.range(0, numberOfSessions)
                   .forEach(i -> createTestCrashSession(sessionSpeaker));
       });

    }

    private SessionSpeaker createTestSessionSpeaker() {
        String name = faker.name().fullName();
        String company = faker.company().name();
        String description = faker.shakespeare().romeoAndJulietQuote();

        return sessionSpeakerService.createSessionSpeaker(
                new SessionSpeakerPostRequestBody(company, name, description));
    }

    private void createTestCrashSession(SessionSpeaker sessionSpeaker) {
        String title = faker.book().title();
        String body =
                faker.shakespeare().hamletQuote()
                + faker.shakespeare().kingRichardIIIQuote()
                + faker.shakespeare().romeoAndJulietQuote();

        crashSessionService.createCrashSession(
                new CrashSessionPostRequestBody(
                    title,
                    body,
                    getRandomCategory(),
                    ZonedDateTime.now().plusDays(new Random().nextInt(2) + 1),
                    sessionSpeaker.getSpeakerId()
                ));
    }

    private CrashSessionCategory getRandomCategory() {
        CrashSessionCategory[] categories = CrashSessionCategory.values();
        int randomIndex = new Random().nextInt(categories.length);
        return categories[randomIndex];
    }
}
