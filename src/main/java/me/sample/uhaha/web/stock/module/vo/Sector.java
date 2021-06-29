package me.sample.uhaha.web.stock.module.vo;

import java.util.List;

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
public class Sector {

	private String sectorName;
	private String sectorUrl;
	private List<FundamentalData> fundamentalDataList;

}
