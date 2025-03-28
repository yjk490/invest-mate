package io.yjk.stockbot.infra.adaptor.out.koreaninvestment.login;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

public interface KoreaInvestmentLoginGateway {

    KoreaInvestmentLoginGatewayResponse login(KoreaInvestmentLoginGatewayRequest request);

    @Component
    class KoreaInvestmentLoginHttpGateway implements KoreaInvestmentLoginGateway {

        private static final String LOGIN_URL = "/oauth2/tokenP";
        private final RestClient restClient;

        public KoreaInvestmentLoginHttpGateway(@Qualifier("koreaInvestmentRestClient") RestClient restClient) {
            this.restClient = restClient;
        }

        @Override
        public KoreaInvestmentLoginGatewayResponse login(KoreaInvestmentLoginGatewayRequest request) {
            return restClient.post()
                    .uri(LOGIN_URL)
                    .body(request)
                    .retrieve()
                    .toEntity(KoreaInvestmentLoginGatewayResponse.class).getBody();
        }
    }
}
