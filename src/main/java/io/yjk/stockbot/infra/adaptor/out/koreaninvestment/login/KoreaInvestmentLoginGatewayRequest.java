package io.yjk.stockbot.infra.adaptor.out.koreaninvestment.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class KoreaInvestmentLoginGatewayRequest {
    @JsonProperty("grant_type")
    private String grantType;
    @JsonProperty("appkey")
    private String appKey;
    @JsonProperty("appsecret")
    private String appSecret;
}
