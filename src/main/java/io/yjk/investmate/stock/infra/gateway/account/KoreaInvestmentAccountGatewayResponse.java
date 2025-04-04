package io.yjk.investmate.stock.infra.gateway.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.yjk.investmate.stock.infra.gateway.KoreaInvestmentGatewayResponse;
import lombok.ToString;

import java.util.List;

@ToString
public class KoreaInvestmentAccountGatewayResponse implements KoreaInvestmentGatewayResponse {
    @JsonProperty("rt_cd")
    private String rt_cd;
    @JsonProperty("msg_cd")
    private String msg_cd;
    @JsonProperty("msg1")
    private String msg1;
    @JsonProperty("ctx_area_fk200")
    private String ctx_area_fk200;
    @JsonProperty("ctx_area_nk200")
    private String ctx_area_nk200;

    @JsonProperty("output1")
    private List<StockBalance> output1;

    @JsonProperty("output2")
    private StockProfit output2;


    @ToString
    private static class StockBalance {
        @JsonProperty("cano")
        private String cano;
        @JsonProperty("acnt_prdt_cd")
        private String acntPrdtCd;
        @JsonProperty("prdt_type_cd")
        private String prdtTypeCd;
        @JsonProperty("ovrs_pdno")
        private String ovrsPdno;
        @JsonProperty("ovrs_item_name")
        private String ovrsItemName;
        @JsonProperty("frcr_evlu_pfls_amt")
        private String frcrEvluPflsAmt;
        @JsonProperty("evlu_pfls_rt")
        private String evluPflsRt;
        @JsonProperty("pchs_avg_pric")
        private String pchsAvgPric;
        @JsonProperty("ovrs_cblc_qty")
        private String ovrsCblcQty;
        @JsonProperty("ord_psbl_qty")
        private String ordPsblQty;
        @JsonProperty("frcr_pchs_amt1")
        private String frcrPchsAmt1;
        @JsonProperty("ovrs_stck_evlu_amt")
        private String ovrsStckEvluAmt;
        @JsonProperty("now_pric2")
        private String nowPric2;
        @JsonProperty("tr_crcy_cd")
        private String trCrcyCd;
        @JsonProperty("ovrs_excg_cd")
        private String ovrsExcgCd;
        @JsonProperty("loan_type_cd")
        private String loanTypeCd;
        @JsonProperty("loan_dt")
        private String loanDt;
        @JsonProperty("expd_dt")
        private String expdDt;
    }

    @ToString
    private static class StockProfit {
        @JsonProperty("frcr_pchs_amt1")
        private String frcrPchsAmt1;
        @JsonProperty("ovrs_rlzt_pfls_amt")
        private String ovrsRlztPflsAmt;
        @JsonProperty("ovrs_tot_pfls")
        private String ovrsTotPfls;
        @JsonProperty("rlzt_erng_rt")
        private String rlztErngRt;
        @JsonProperty("tot_evlu_pfls_amt")
        private String totEvluPflsAmt;
        @JsonProperty("tot_pftrt")
        private String totPftrt;
        @JsonProperty("frcr_buy_amt_smtl1")
        private String frcrBuyAmtSmtl1;
        @JsonProperty("ovrs_rlzt_pfls_amt2")
        private String ovrsRlztPflsAmt2;
        @JsonProperty("frcr_buy_amt_smtl2")
        private String frcrBuyAmtSmtl2;
    }
}
