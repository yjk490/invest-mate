package io.yjk.investmate.koreainvestment.infra.gateway.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.yjk.investmate.koreainvestment.infra.gateway.KoreaInvestmentGatewayRequest;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class KoreaInvestmentAccountGatewayRequest implements KoreaInvestmentGatewayRequest {
    @JsonProperty("CANO")
    private String cano;
    @JsonProperty("ACNT_PRDT_CD")
    private String acntPrdtCd;
    @JsonProperty("OVRS_EXCG_CD")
    private String ovrsExcgCd;
    @JsonProperty("TR_CRCY_CD")
    private String trCrcyCd;
    @JsonProperty("CTX_AREA_FK200")
    private String ctxAreaFk200;
    @JsonProperty("CTX_AREA_NK200")
    private String ctxAreaNk200;
}
