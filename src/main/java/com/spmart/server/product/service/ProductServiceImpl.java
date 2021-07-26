package com.spmart.server.product.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.spmart.server.common.dto.PageDto;
import com.spmart.server.common.dto.PageResponse;
import com.spmart.server.product.domain.CategoryItem;
import com.spmart.server.product.domain.Product;
import com.spmart.server.product.dto.ProductCard;
import com.spmart.server.product.dto.ProductDetail;
import com.spmart.server.product.dto.ProductRequest;
import com.spmart.server.product.repository.CategoryItemRepository;
import com.spmart.server.product.repository.OptionValueRepository;
import com.spmart.server.product.repository.ProductOptionRepository;
import com.spmart.server.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductOptionRepository productOptionRepository;
	private final CategoryItemRepository categoryItemRepository;
	private final OptionValueRepository optionValueRepository;
	private final ModelMapper modelMapper;

	public PageResponse<ProductCard> getProductCardList(Long categoryItemId, PageDto pageDto) {
		Page<ProductCard> pageInfo = productRepository.findAllByCategoryItemId(categoryItemId, pageDto.of())
			.map(product -> modelMapper.map(product, ProductCard.class));

		return PageResponse.<ProductCard>builder()
			.contents(pageInfo.getContent())
			.pageNumber(pageInfo.getNumber())
			.pageSize(pageInfo.getSize())
			.totalPages(pageInfo.getTotalPages())
			.build();
	}

	public ProductDetail getProductDetail(Long productId) {
		return modelMapper
			.map(productRepository.findById(productId)
				.orElseThrow(EntityNotFoundException::new), ProductDetail.class);
	}

	public void registProduct(ProductRequest productRequest) {
		if (!categoryItemRepository.existsById(productRequest.getCategoryItemId())) {
			throw new IllegalArgumentException("존재하지 않는 카테고리");
		}
		productRepository.save(productRequest.toEntity());
	}

	public void updateProduct(ProductRequest productRequest) {
		Product product = productRepository.findById(productRequest.getId()).orElseThrow(IllegalArgumentException::new);

		product.update(productRequest.toEntity());
		productRepository.save(product);
	}

	public void removeProduct(Long productId) {
		productRepository.delete(Product.builder().id(productId).build());
	}
}
