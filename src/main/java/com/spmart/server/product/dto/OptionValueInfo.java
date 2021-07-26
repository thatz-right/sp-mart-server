package com.spmart.server.product.dto;

import com.spmart.server.product.domain.OptionValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OptionValueInfo {
	private String name;
	private int extraPrice;

	public OptionValue toEntity() {
		return OptionValue.builder().name(name).extraPrice(extraPrice).build();
	}
}
