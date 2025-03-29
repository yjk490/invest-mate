package io.yjk.stockbot.unit.infra.adaptor.out.koreaninvestment.access;

import io.yjk.stockbot.infra.adaptor.out.koreaninvestment.access.KoreaInvestmentAccessGateway;
import io.yjk.stockbot.infra.adaptor.out.koreaninvestment.access.KoreaInvestmentAccessGatewayLoginRequest;
import io.yjk.stockbot.infra.adaptor.out.koreaninvestment.access.KoreaInvestmentAccessGatewayLoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class KoreaInvestmentAccessGatewayTest {

    private final RestClient restClient = mock(RestClient.class);
    private final RestClient.RequestBodySpec requestBodySpec = mock(RestClient.RequestBodySpec.class);
    private final RestClient.RequestBodyUriSpec requestBodyUriSpec = mock(RestClient.RequestBodyUriSpec.class);
    private final RestClient.ResponseSpec responseSpec = mock(RestClient.ResponseSpec.class);
    private final KoreaInvestmentAccessGateway gateway = new KoreaInvestmentAccessGateway.KoreaInvestmentAccessHttpGateway(restClient);

    @Test
    void login() {
        KoreaInvestmentAccessGatewayLoginRequest request = new KoreaInvestmentAccessGatewayLoginRequest(
                "client_credentials",
                "fake_app_key",
                "fake_app_secret");

        KoreaInvestmentAccessGatewayLoginResponse expectedResponse = new KoreaInvestmentAccessGatewayLoginResponse(
                "fake_access_token",
                "bearer",
                "86400",
                "2099-12-31 23:59:59");

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/oauth2/tokenP")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(KoreaInvestmentAccessGatewayLoginRequest.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(KoreaInvestmentAccessGatewayLoginResponse.class)).thenReturn(ResponseEntity.ok(expectedResponse));

        KoreaInvestmentAccessGatewayLoginResponse actualResponse = gateway.login(request);

        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);

        verify(restClient).post();
        verify(requestBodyUriSpec).uri("/oauth2/tokenP");
        verify(requestBodySpec).body(request);
        verify(requestBodySpec).retrieve();
        verify(responseSpec).toEntity(KoreaInvestmentAccessGatewayLoginResponse.class);
    }
}