package io.yjk.stockbot.infra.adaptor.out.koreaninvestment.access;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class KoreaInvestmentAccessGatewayLogoutRequest {
    @JsonProperty("appkey")
    private String appKey;
    @JsonProperty("appsecret")
    private String appSecret;
    @JsonProperty("token")
    private String token;
}
