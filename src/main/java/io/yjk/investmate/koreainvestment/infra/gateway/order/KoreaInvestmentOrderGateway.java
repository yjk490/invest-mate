package io.yjk.investmate.koreainvestment.infra.gateway.order;

import io.yjk.investmate.koreainvestment.infra.gateway.KoreaInvestmentGatewayClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KoreaInvestmentOrderGateway {

    private final KoreaInvestmentGatewayClient apiClient;

    private static final String PATH = "/uapi/overseas-stock/v1/trading/order";
    private final String buyTrId;
    private final String sellTrId;

    public KoreaInvestmentOrderGateway(
            KoreaInvestmentGatewayClient koreaInvestmentGatewayClient,
            @Value("${koreainvestment.trId.buy}") String buyTrId,
            @Value("${koreainvestment.trId.sell}") String sellTrId) {
        this.apiClient = koreaInvestmentGatewayClient;
        this.buyTrId = buyTrId;
        this.sellTrId = sellTrId;
    }

    public KoreaInvestmentOrderGatewayResponse buy(KoreaInvestmentOrderGatewayRequest request) {
        return apiClient.post(PATH, buyTrId, request, KoreaInvestmentOrderGatewayResponse.class);
    }

    public KoreaInvestmentOrderGatewayResponse sell(KoreaInvestmentOrderGatewayRequest request) {
        return apiClient.post(PATH, sellTrId, request, KoreaInvestmentOrderGatewayResponse.class);
    }
}
