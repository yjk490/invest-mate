package io.yjk.investmate.integration.stock.infra.gateway;

import io.yjk.investmate.stock.infra.gateway.KoreaInvestmentGatewayClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(KoreaInvestmentGatewayClient.class)
class KoreaInvestmentGatewayClientTest {

    @Autowired
    private KoreaInvestmentGatewayClient koreaInvestmentGatewayClient;

    @Test
    void loginAndLogout() {
        Assertions.assertDoesNotThrow(() -> koreaInvestmentGatewayClient.login());
        Assertions.assertDoesNotThrow(() -> koreaInvestmentGatewayClient.logout());
    }
}