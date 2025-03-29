package io.yjk.stockbot.infra.adaptor.out.koreaninvestment.access;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@ToString
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class KoreaInvestmentAccessGatewayLoginResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private String expiresIn;
    @JsonProperty("access_token_token_expired")
    private String accessTokenTokenExpired;
}
