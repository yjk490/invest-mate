package io.yjk.investmate.stock.infra.gateway.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.yjk.investmate.stock.infra.gateway.KoreaInvestmentGatewayResponse;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Getter
@Accessors(fluent = true)
public class KoreaInvestmentOrderGatewayResponse implements KoreaInvestmentGatewayResponse {
    @JsonProperty("rt_cd")
    private String rtCd;
    @JsonProperty("msg_cd")
    private String msgCd;
    @JsonProperty("msg1")
    private String msg1;
    @JsonProperty("output")
    private Detail output;

    @ToString
    @Getter
    @Accessors(fluent = true)
    private static class Detail {
        @JsonProperty("KRX_FWDG_ORD_ORGNO")
        private String krxFwdgOrdOrgno;
        @JsonProperty("ODNO")
        private String odno;
        @JsonProperty("ORD_TMD")
        private String ordTmd;
    }
}
