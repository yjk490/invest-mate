package io.yjk.stockbot.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
public class HttpBeanConfig {

    @Bean(name = "koreaInvestmentRestClient")
    public RestClient koreaInvestmentRestClient(@Value("${koreaInvestment.baseUrl}") String url) {
        return RestClient.builder()
                .baseUrl(url)
                .requestInterceptor(
                        (request, body, execution) -> {
                            log.info("[KOREA INVESTMENT REQUEST] {} {} BODY: {}", request.getMethod(), request.getURI(), new String(body));
                            return execution.execute(request, body);
                        }
                )
                .build();
    }
}
