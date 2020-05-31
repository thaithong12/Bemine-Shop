package com.tq.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tq.entities.ProductDetails;
import com.tq.entities.Size;
import com.tq.repositories.SizeRepository;

@Service
public class SizeService {
	@Autowired
	SizeRepository sizeRepository;
	
	public Set<Size> getSizeByProductDetail(List<ProductDetails> productDetails){
		Set<Size> sizes = new HashSet();
		if(!CollectionUtils.isEmpty(productDetails)) {
			for (ProductDetails details : productDetails) {
				sizes.add(details.getSize());
			}
		}
		return sizes;
	}
	public Size getSizeBySizeId(int id) {
		return sizeRepository.findOne(id);
	}
}
