package io.yjk.investmate.koreainvestment.infra.gateway;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.yjk.investmate.koreainvestment.infra.config.KoreaInvestmentProperties;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
public class KoreaInvestmentGatewayClient {

    private final KoreaInvestmentProperties properties;
    private final RestClient restClient;
    private final AtomicReference<AccessToken> accessTokenStore;

    private static final String GRANT_TYPE = "client_credentials";
    private static final String LOGIN_PATH = "/oauth2/tokenP";
    private static final String LOGOUT_PATH = "/oauth2/revokeP";

    public KoreaInvestmentGatewayClient(
            KoreaInvestmentProperties properties,
            @Qualifier("koreaInvestmentRestClient") RestClient restClient) {
        this.properties = properties;
        this.restClient = restClient;
        this.accessTokenStore = new AtomicReference<>();
    }

    public <T extends KoreaInvestmentGatewayResponse> T call(
            String path,
            String transactionId,
            KoreaInvestmentGatewayRequest request,
            Class<T> responseType) {


        return restClient.post()
                .uri(path)
                .headers(httpHeaders -> httpHeaders.addAll(createHeaders(transactionId)))
                .body(request)
                .retrieve()
                .toEntity(responseType).getBody();
    }

    public void login() {
        KoreaInvestmentAccessGatewayLoginRequest request = new KoreaInvestmentAccessGatewayLoginRequest(
                GRANT_TYPE,
                properties.appKey(),
                properties.appSecret());

        KoreaInvestmentAccessGatewayLoginResponse response = restClient.post()
                .uri(LOGIN_PATH)
                .body(request)
                .retrieve()
                .toEntity(KoreaInvestmentAccessGatewayLoginResponse.class).getBody();

        accessTokenStore.set(new AccessToken(response.accessToken(), LocalDateTime.parse(response.accessTokenTokenExpired(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
    }

    public void logout() {
        KoreaInvestmentAccessGatewayLogoutRequest request = new KoreaInvestmentAccessGatewayLogoutRequest(
                properties.appKey(),
                properties.appSecret(),
                accessTokenStore.get().tokenValue());

        KoreaInvestmentAccessGatewayLogoutResponse response = restClient.post()
                .uri(LOGOUT_PATH)
                .body(request)
                .retrieve()
                .toEntity(KoreaInvestmentAccessGatewayLogoutResponse.class).getBody();

        accessTokenStore.set(null);
    }

    private HttpHeaders createHeaders(String transactionId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessTokenStore.get().tokenValue());
        headers.set("appkey", properties.appKey());
        headers.set("appsecret", properties.appSecret());
        headers.set("tr_id", transactionId);
        return headers;
    }



    @ToString
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class KoreaInvestmentAccessGatewayLoginRequest {
        @JsonProperty("grant_type")
        private String grantType;
        @JsonProperty("appkey")
        private String appKey;
        @JsonProperty("appsecret")
        private String appSecret;
    }

    @ToString
    @Getter
    @Accessors(fluent = true)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class KoreaInvestmentAccessGatewayLoginResponse {
        @JsonProperty("access_token")
        private String accessToken;
        @JsonProperty("token_type")
        private String tokenType;
        @JsonProperty("expires_in")
        private String expiresIn;
        @JsonProperty("access_token_token_expired")
        private String accessTokenTokenExpired;
    }

    @ToString
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class KoreaInvestmentAccessGatewayLogoutRequest {
        @JsonProperty("appkey")
        private String appKey;
        @JsonProperty("appsecret")
        private String appSecret;
        @JsonProperty("token")
        private String token;
    }

    @ToString
    @Getter
    @Accessors(fluent = true)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class KoreaInvestmentAccessGatewayLogoutResponse {
        @JsonProperty("code")
        private String code;
        @JsonProperty("message")
        private String message;
    }

    @Getter
    @Accessors(fluent = true)
    @RequiredArgsConstructor
    private static class AccessToken {
        private final String tokenValue;
        private final LocalDateTime expiryTime;
    }
}
