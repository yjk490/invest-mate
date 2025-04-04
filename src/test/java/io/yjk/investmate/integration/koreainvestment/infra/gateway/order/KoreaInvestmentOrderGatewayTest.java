package io.yjk.investmate.integration.koreainvestment.infra.gateway.order;

import io.yjk.investmate.stock.infra.gateway.KoreaInvestmentGatewayClient;
import io.yjk.investmate.stock.infra.gateway.order.KoreaInvestmentOrderGateway;
import io.yjk.investmate.stock.infra.gateway.order.KoreaInvestmentOrderGatewayRequest;
import io.yjk.investmate.stock.infra.gateway.order.KoreaInvestmentOrderGatewayResponse;
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
@Import({KoreaInvestmentOrderGateway.class, KoreaInvestmentGatewayClient.class})
public class KoreaInvestmentOrderGatewayTest {

    @Autowired
    private KoreaInvestmentOrderGateway gateway;
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
    void buy() {
        KoreaInvestmentOrderGatewayRequest request = new KoreaInvestmentOrderGatewayRequest(
                "64911197",
                "01",
                "NASD",
                "AAPL",
                "1",
                "0",
                "0",
                "00"
        );

        KoreaInvestmentOrderGatewayResponse response = gateway.buy(request);

        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void sell() {
        KoreaInvestmentOrderGatewayRequest request = new KoreaInvestmentOrderGatewayRequest(
                "64911197",
                "01",
                "NASD",
                "AAPL",
                "1",
                "0",
                "0",
                "00"
        );

        KoreaInvestmentOrderGatewayResponse response = gateway.sell(request);

        Assertions.assertThat(response).isNotNull();
    }
}
