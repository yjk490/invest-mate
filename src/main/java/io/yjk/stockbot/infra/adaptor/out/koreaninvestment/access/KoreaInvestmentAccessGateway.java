package io.yjk.stockbot.infra.adaptor.out.koreaninvestment.access;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

public interface KoreaInvestmentAccessGateway {

    KoreaInvestmentAccessGatewayLoginResponse login(KoreaInvestmentAccessGatewayLoginRequest request);
    KoreaInvestmentAccessGatewayLogoutResponse logout(KoreaInvestmentAccessGatewayLogoutRequest request);

    @Component
    class KoreaInvestmentAccessHttpGateway implements KoreaInvestmentAccessGateway {

        private static final String LOGIN_URL = "/oauth2/tokenP";
        private static final String LOGOUT_URL = "/oauth2/revokeP";
        private final RestClient restClient;

        public KoreaInvestmentAccessHttpGateway(@Qualifier("koreaInvestmentRestClient") RestClient restClient) {
            this.restClient = restClient;
        }

        @Override
        public KoreaInvestmentAccessGatewayLoginResponse login(KoreaInvestmentAccessGatewayLoginRequest request) {
            return restClient.post()
                    .uri(LOGIN_URL)
                    .body(request)
                    .retrieve()
                    .toEntity(KoreaInvestmentAccessGatewayLoginResponse.class).getBody();
        }

        @Override
        public KoreaInvestmentAccessGatewayLogoutResponse logout(KoreaInvestmentAccessGatewayLogoutRequest request) {
            return restClient.post()
                    .uri(LOGOUT_URL)
                    .body(request)
                    .retrieve()
                    .toEntity(KoreaInvestmentAccessGatewayLogoutResponse.class).getBody();
        }
    }
}
