package com.tq.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tq.entities.Color;
import com.tq.entities.ProductDetails;
import com.tq.repositories.ColorRepository;

@Service
public class ColorService {
	@Autowired
	ColorRepository colorRepository;
	
	public Set<Color> getColorByProductDetail(List<ProductDetails> productDetails){
		Set<Color> colors = new HashSet();
		if(!CollectionUtils.isEmpty(productDetails)) {
			for (ProductDetails details : productDetails) {
				colors.add(details.getColor());
			}
		}
		return colors;
	}
	public Color getColorById(int id) {
		return colorRepository.findOne(id);
	}
}
