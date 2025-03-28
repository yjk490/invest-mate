package io.yjk.stockbot.integration.adaptor.out.koreainvestment.login;

import io.yjk.stockbot.global.HttpBeanConfig;
import io.yjk.stockbot.global.KoreaInvestmentProperties;
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
@ContextConfiguration(classes = KoreaInvestmentLoginGatewayTest.TestConfig.class)
@ActiveProfiles("dev")
class KoreaInvestmentLoginGatewayTest {

    @Autowired private KoreaInvestmentProperties properties;
    @Autowired private KoreaInvestmentLoginGateway gateway;

    @Test
    void login() {
        KoreaInvestmentLoginGatewayRequest request = new KoreaInvestmentLoginGatewayRequest(
                "client_credentials",
                properties.appKey(),
                properties.appSecret());
        KoreaInvestmentLoginGatewayResponse response = gateway.login(request);
        Assertions.assertThat(response).isNotNull();
        log.info("response: {}", response);
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
        public KoreaInvestmentLoginGateway gateway() {
            return new KoreaInvestmentLoginGateway.KoreaInvestmentLoginHttpGateway(restClient());
        }
    }

}