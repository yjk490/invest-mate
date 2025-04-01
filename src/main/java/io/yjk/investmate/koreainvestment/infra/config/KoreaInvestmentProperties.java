package io.yjk.investmate.koreainvestment.infra.config;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@ToString
@Component
@Getter
@Accessors(fluent = true)
public class KoreaInvestmentProperties {

    private final String baseUrl;
    private final String appKey;
    private final String appSecret;

    public KoreaInvestmentProperties(
            @Value("${koreainvestment.baseUrl}") String baseUrl,
            @Value("${koreainvestment.appKey}") String appKey,
            @Value("${koreainvestment.appSecret}") String appSecret) {
        this.baseUrl = baseUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
    }
}
