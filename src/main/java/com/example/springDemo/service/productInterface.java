package com.example.springDemo.service;

import com.example.springDemo.productDTO.productRequestDto;
import com.example.springDemo.productDTO.productResponseDto;

public interface productInterface {
    productResponseDto searchProducts (productRequestDto productRequestDTO);
}
