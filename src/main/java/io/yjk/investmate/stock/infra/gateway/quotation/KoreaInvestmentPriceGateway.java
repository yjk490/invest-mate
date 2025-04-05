package io.yjk.investmate.stock.infra.gateway.quotation;

import io.yjk.investmate.stock.infra.gateway.KoreaInvestmentGatewayClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KoreaInvestmentPriceGateway {

    private static final String PATH = "/uapi/overseas-price/v1/quotations/dailyprice";
    private final KoreaInvestmentGatewayClient apiClient;
    private final String trId;

    public KoreaInvestmentPriceGateway(
            KoreaInvestmentGatewayClient apiClient,
            @Value("${koreainvestment.trId.price}") String trId) {
        this.apiClient = apiClient;
        this.trId = trId;
    }

    public KoreaInvestmentPriceGatewayResponse execute(KoreaInvestmentPriceGatewayRequest request) {
        return apiClient.get(PATH, trId, request, KoreaInvestmentPriceGatewayResponse.class);
    }
}
