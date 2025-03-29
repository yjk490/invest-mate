package io.yjk.investmate.global;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

public interface KoreaInvestmentProperties {
    String baseUrl();
    String appKey();
    String appSecret();

    @Configuration
    @PropertySource("classpath:application-dev.properties")
    @Profile("dev")
    class KoreaInvestmentDevProperties implements KoreaInvestmentProperties {

        @Value("${koreaInvestment.baseUrl}") private String baseUrl;
        @Value("${koreaInvestment.appKey}") private String appKey;
        @Value("${koreaInvestment.appSecret}") private String appSecret;

        @Override
        public String baseUrl() {
            return baseUrl;
        }

        @Override
        public String appKey() {
            return appKey;
        }

        @Override
        public String appSecret() {
            return appSecret;
        }
    }

    @Configuration
    @PropertySource("classpath:application-prod.properties")
    @Profile("prod")
    class KoreaInvestmentProdProperties implements KoreaInvestmentProperties {

        @Value("${koreaInvestment.baseUrl}") private String baseUrl;
        @Value("${koreaInvestment.appKey}") private String appKey;
        @Value("${koreaInvestment.appSecret}") private String appSecret;

        @Override
        public String baseUrl() {
            return baseUrl;
        }

        @Override
        public String appKey() {
            return appKey;
        }

        @Override
        public String appSecret() {
            return appSecret;
        }
    }
}
