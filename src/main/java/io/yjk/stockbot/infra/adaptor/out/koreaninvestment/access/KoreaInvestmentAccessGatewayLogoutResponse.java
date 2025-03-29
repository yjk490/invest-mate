package io.yjk.stockbot.infra.adaptor.out.koreaninvestment.access;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@ToString
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class KoreaInvestmentAccessGatewayLogoutResponse {
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
}
