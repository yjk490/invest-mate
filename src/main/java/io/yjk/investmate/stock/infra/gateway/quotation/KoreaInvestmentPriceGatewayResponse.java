package io.yjk.investmate.stock.infra.gateway.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.yjk.investmate.stock.infra.gateway.KoreaInvestmentGatewayResponse;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@ToString
@Getter
@Accessors(fluent = true)
public class KoreaInvestmentPriceGatewayResponse implements KoreaInvestmentGatewayResponse {
    @JsonProperty("rt_cd")
    private String rtCd;
    @JsonProperty("msg_cd")
    private String msgCd;
    @JsonProperty("msg1")
    private String msg1;
    @JsonProperty("output1")
    private PriceMetaDetail output1;
    @JsonProperty("output2")
    private List<PriceDetail> output2;

    @ToString
    @Getter
    @Accessors(fluent = true)
    private static class PriceMetaDetail {
        @JsonProperty("rsym")
        private String rsym;
        @JsonProperty("zdiv")
        private String zdiv;
        @JsonProperty("nrec")
        private String nrec;
    }

    @ToString
    @Getter
    @Accessors(fluent = true)
    private static class PriceDetail {
        @JsonProperty("-xymd")
        private String xymd;
        @JsonProperty("clos")
        private String clos;
        @JsonProperty("sign")
        private String sign;
        @JsonProperty("diff")
        private String diff;
        @JsonProperty("rate")
        private String rate;
        @JsonProperty("open")
        private String open;
        @JsonProperty("high")
        private String high;
        @JsonProperty("low")
        private String low;
        @JsonProperty("tvol")
        private String tvol;
        @JsonProperty("tamt")
        private String tamt;
        @JsonProperty("pbid")
        private String pbid;
        @JsonProperty("vbid")
        private String vbid;
        @JsonProperty("pask")
        private String pask;
        @JsonProperty("vask")
        private String vask;
    }
}
