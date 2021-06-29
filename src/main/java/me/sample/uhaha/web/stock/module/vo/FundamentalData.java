package me.sample.uhaha.web.stock.module.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FundamentalData {
	
	private String code;
	private String name;
	private String sales;
	private String operatingProfit;
	private String netProfit;
	private String operatingMargin;
	private String netProfitMargin;
	private String roe;
	private String per;
	private String pbr;

}
