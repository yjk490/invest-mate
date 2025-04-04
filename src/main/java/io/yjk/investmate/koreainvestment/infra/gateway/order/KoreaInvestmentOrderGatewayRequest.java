package io.yjk.investmate.koreainvestment.infra.gateway.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.yjk.investmate.koreainvestment.infra.gateway.KoreaInvestmentGatewayRequest;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class KoreaInvestmentOrderGatewayRequest implements KoreaInvestmentGatewayRequest {
    @JsonProperty("CANO")
    private String cano;
    @JsonProperty("ACNT_PRDT_CD")
    private String acntPrdtCd;
    @JsonProperty("OVRS_EXCG_CD")
    private String ovrsExcgCd;
    @JsonProperty("PDNO")
    private String pdno;
    @JsonProperty("ORD_QTY")
    private String ordQty;
    @JsonProperty("OVRS_ORD_UNPR")
    private String ovrsOrdUnpr;
    @JsonProperty("ORD_SVR_DVSN_CD")
    private String ordSvrDvsnCd; // default 0
    @JsonProperty("ORD_DVSN")
    private String ordDvsn;
}
