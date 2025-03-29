package io.yjk.stockbot.intergration.infra.adaptor.out.koreaninvestment.access;

import io.yjk.stockbot.global.HttpBeanConfig;
import io.yjk.stockbot.global.KoreaInvestmentProperties;
import io.yjk.stockbot.infra.adaptor.out.koreaninvestment.access.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestClient;

@Slf4j
@SpringBootTest
@ContextConfiguration(classes = KoreaInvestmentAccessGatewayTest.TestConfig.class)
@ActiveProfiles("dev")
class KoreaInvestmentAccessGatewayTest {

    @Autowired private KoreaInvestmentProperties properties;
    @Autowired private KoreaInvestmentAccessGateway gateway;

    @Test
    void loginAndLogout() {
        KoreaInvestmentAccessGatewayLoginRequest loginRequest = new KoreaInvestmentAccessGatewayLoginRequest(
                "client_credentials",
                properties.appKey(),
                properties.appSecret());
        KoreaInvestmentAccessGatewayLoginResponse loginResponse = gateway.login(loginRequest);

        Assertions.assertThat(loginResponse).isNotNull();
        Assertions.assertThat(loginResponse.accessToken()).isNotNull();
        log.info("loginResponse: {}", loginResponse);

        KoreaInvestmentAccessGatewayLogoutRequest logoutRequest = new KoreaInvestmentAccessGatewayLogoutRequest(
                properties.appKey(),
                properties.appSecret(),
                loginResponse.accessToken()
        );
        KoreaInvestmentAccessGatewayLogoutResponse logoutResponse = gateway.logout(logoutRequest);

        Assertions.assertThat(logoutResponse).isNotNull();
        Assertions.assertThat(logoutResponse.code()).isEqualTo("200");
        log.info("logoutResponse: {}", logoutResponse);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public KoreaInvestmentProperties properties() {
            return new KoreaInvestmentProperties.KoreaInvestmentDevProperties();
        }

        @Bean
        public RestClient restClient() {
            return new HttpBeanConfig().koreaInvestmentRestClient(properties().baseUrl());
        }

        @Bean
        public KoreaInvestmentAccessGateway gateway() {
            return new KoreaInvestmentAccessGateway.KoreaInvestmentAccessHttpGateway(restClient());
        }
    }

}