package me.sample.uhaha.web.stock.module.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class CurrentBundle {

	private List<String> kospi60;
	private List<String> kosdaq60;

}
