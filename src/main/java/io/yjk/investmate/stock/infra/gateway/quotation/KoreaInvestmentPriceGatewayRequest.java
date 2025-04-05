package io.yjk.investmate.stock.infra.gateway.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.yjk.investmate.stock.infra.gateway.KoreaInvestmentGatewayRequest;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class KoreaInvestmentPriceGatewayRequest implements KoreaInvestmentGatewayRequest {
    @JsonProperty("AUTH")
    private String auth;
    @JsonProperty("EXCD")
    private String excd;
    @JsonProperty("SYMB")
    private String symb;
    @JsonProperty("GUBN")
    private String gubn;
    @JsonProperty("BYMD")
    private String bymd;
    @JsonProperty("MODP")
    private String modp;
    @JsonProperty("KEYB")
    private String keyb;
}
