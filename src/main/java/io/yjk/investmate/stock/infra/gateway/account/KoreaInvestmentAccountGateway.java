package io.yjk.investmate.stock.infra.gateway.account;

import io.yjk.investmate.stock.infra.gateway.KoreaInvestmentGatewayClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KoreaInvestmentAccountGateway {

    private final KoreaInvestmentGatewayClient apiClient;

    private static final String PATH = "/uapi/overseas-stock/v1/trading/inquire-balance";
    private final String transactionId;

    public KoreaInvestmentAccountGateway(
            KoreaInvestmentGatewayClient koreaInvestmentGatewayClient,
            @Value("${koreainvestment.trId.account}") String transactionId) {
        this.apiClient = koreaInvestmentGatewayClient;
        this.transactionId = transactionId;
    }

    public KoreaInvestmentAccountGatewayResponse execute(KoreaInvestmentAccountGatewayRequest request) {
        return apiClient.get(PATH, transactionId, request, KoreaInvestmentAccountGatewayResponse.class);
    }
}
