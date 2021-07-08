package com.spmart.server.common.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PageResponse<T> {

	private List<T> contents = new ArrayList<>();

	private Integer page;

	private Integer pageSize;

	private Integer totalPages;

	@Builder
	public PageResponse(List<T> contents, Integer page, Integer pageSize, Integer totalPages) {
		this.contents = contents;
		this.page = page;
		this.pageSize = pageSize;
		this.totalPages = totalPages;
	}
}
