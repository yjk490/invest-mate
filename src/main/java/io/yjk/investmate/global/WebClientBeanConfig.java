package io.yjk.investmate.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
public class WebClientBeanConfig {

    @Bean(name = "koreaInvestmentRestClient")
    public RestClient koreaInvestmentRestClient(@Value("${koreainvestment.baseUrl}") String url) {
        return RestClient.builder()
                .baseUrl(url)
                .requestInterceptor(
                        (request, body, execution) -> {
                            log.info("[KOREA INVESTMENT REQUEST] {} {} BODY: {}", request.getMethod(), request.getURI(), new String(body));
                            ClientHttpResponse response = execution.execute(request, body);
                            BufferingClientHttpResponse bufferedResponse = new BufferingClientHttpResponse(response);
                            log.info("[KOREA INVESTMENT RESPONSE] {} BODY: {}", response.getStatusCode(), bufferedResponse.getBodyAsString());
                            return bufferedResponse;
                        }
                )
                .build();
    }

    private static class BufferingClientHttpResponse implements ClientHttpResponse {

        private final ClientHttpResponse response;
        private final byte[] body;

        public BufferingClientHttpResponse(ClientHttpResponse response) throws IOException {
            this.response = response;
            this.body = response.getBody().readAllBytes();
        }

        @Override
        public InputStream getBody() throws IOException {
            return new ByteArrayInputStream(body);
        }

        @Override
        public HttpStatusCode getStatusCode() throws IOException {
            return response.getStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return response.getStatusText();
        }

        @Override
        public void close() {
            response.close();
        }

        @Override
        public HttpHeaders getHeaders() {
            return response.getHeaders();
        }

        public String getBodyAsString() {
            return new String(body, StandardCharsets.UTF_8);
        }
    }
}
