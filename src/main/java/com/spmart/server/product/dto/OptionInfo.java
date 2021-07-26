package com.spmart.server.product.dto;

import java.util.List;

import com.spmart.server.product.domain.OptionValue;
import com.spmart.server.product.domain.ProductOption;

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
public class OptionInfo {
	String name;
	List<OptionValueInfo> values;

	public ProductOption toEntity() {
		ProductOption productOption = ProductOption.builder().name(name).build();

		for(OptionValueInfo optionValueInfo: values) {
			productOption.addOptionValue(optionValueInfo.toEntity());
		}
		return productOption;
	}
}