package io.yjk.stockbot.unit.infra.adaptor.out.koreaninvestment.login;

import io.yjk.stockbot.infra.adaptor.out.koreaninvestment.login.KoreaInvestmentLoginGateway;
import io.yjk.stockbot.infra.adaptor.out.koreaninvestment.login.KoreaInvestmentLoginGatewayRequest;
import io.yjk.stockbot.infra.adaptor.out.koreaninvestment.login.KoreaInvestmentLoginGatewayResponse;
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
class KoreaInvestmentLoginGatewayTest {

    private final RestClient restClient = mock(RestClient.class);
    private final RestClient.RequestBodySpec requestBodySpec = mock(RestClient.RequestBodySpec.class);
    private final RestClient.RequestBodyUriSpec requestBodyUriSpec = mock(RestClient.RequestBodyUriSpec.class);
    private final RestClient.ResponseSpec responseSpec = mock(RestClient.ResponseSpec.class);
    private final KoreaInvestmentLoginGateway gateway = new KoreaInvestmentLoginGateway.KoreaInvestmentLoginHttpGateway(restClient);

    @Test
    void login() {
        KoreaInvestmentLoginGatewayRequest request = new KoreaInvestmentLoginGatewayRequest(
                "client_credentials",
                "fake_app_key",
                "fake_app_secret");

        KoreaInvestmentLoginGatewayResponse expectedResponse = new KoreaInvestmentLoginGatewayResponse(
                "fake_access_token",
                "bearer",
                "86400",
                "2099-12-31 23:59:59");

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/oauth2/tokenP")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(KoreaInvestmentLoginGatewayRequest.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(KoreaInvestmentLoginGatewayResponse.class)).thenReturn(ResponseEntity.ok(expectedResponse));

        KoreaInvestmentLoginGatewayResponse actualResponse = gateway.login(request);

        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);

        verify(restClient).post();
        verify(requestBodyUriSpec).uri("/oauth2/tokenP");
        verify(requestBodySpec).body(request);
        verify(requestBodySpec).retrieve();
        verify(responseSpec).toEntity(KoreaInvestmentLoginGatewayResponse.class);
    }
}