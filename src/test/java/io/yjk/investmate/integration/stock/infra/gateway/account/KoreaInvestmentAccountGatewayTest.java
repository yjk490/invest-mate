package io.yjk.investmate.integration.stock.infra.gateway.account;

import io.yjk.investmate.stock.infra.gateway.KoreaInvestmentGatewayClient;
import io.yjk.investmate.stock.infra.gateway.account.KoreaInvestmentAccountGateway;
import io.yjk.investmate.stock.infra.gateway.account.KoreaInvestmentAccountGatewayRequest;
import io.yjk.investmate.stock.infra.gateway.account.KoreaInvestmentAccountGatewayResponse;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import({KoreaInvestmentAccountGateway.class, KoreaInvestmentGatewayClient.class})
class KoreaInvestmentAccountGatewayTest {

    @Autowired
    private KoreaInvestmentAccountGateway gateway;
    @Autowired
    private KoreaInvestmentGatewayClient apiClient;

    @BeforeEach
    void before() {
        apiClient.login();
    }
    @AfterEach
    void after() {
        apiClient.logout();
    }

    @Test
    void account() {
        KoreaInvestmentAccountGatewayRequest request = new KoreaInvestmentAccountGatewayRequest(
                "64911197",
                "01",
                "NASD",
                "USD",
                "",
                ""
        );

        KoreaInvestmentAccountGatewayResponse response = gateway.execute(request);

        Assertions.assertThat(response).isNotNull();
        log.info("response: {}", response);
    }


}