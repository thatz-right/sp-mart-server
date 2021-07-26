package com.spmart.server.common.dto;

import org.springframework.data.domain.PageRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageDto {
	private int pageNumber;
	private int pageSize;
	private long offset;

	public PageRequest of() {
		return PageRequest.of(this.pageNumber, this.pageSize);
	}
}