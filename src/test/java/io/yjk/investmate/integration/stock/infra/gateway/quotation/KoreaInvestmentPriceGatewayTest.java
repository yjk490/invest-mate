package io.yjk.investmate.integration.stock.infra.gateway.quotation;

import io.yjk.investmate.stock.infra.gateway.KoreaInvestmentGatewayClient;
import io.yjk.investmate.stock.infra.gateway.quotation.KoreaInvestmentPriceGateway;
import io.yjk.investmate.stock.infra.gateway.quotation.KoreaInvestmentPriceGatewayRequest;
import io.yjk.investmate.stock.infra.gateway.quotation.KoreaInvestmentPriceGatewayResponse;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@Import({KoreaInvestmentPriceGateway.class, KoreaInvestmentGatewayClient.class})
@ActiveProfiles("prod")
class KoreaInvestmentPriceGatewayTest {

    @Autowired
    private KoreaInvestmentPriceGateway gateway;
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
    void price() {
        KoreaInvestmentPriceGatewayRequest request = new KoreaInvestmentPriceGatewayRequest(
                "",
                "NAS",
                "TSLA",
                "0",
                "",
                "0",
                ""
        );

        KoreaInvestmentPriceGatewayResponse response = gateway.execute(request);

        Assertions.assertThat(response).isNotNull();
        log.info("response: {}", response);
    }
  
}