package com.example.springDemo.controller;


import com.example.springDemo.productDTO.productRequestDto;
import com.example.springDemo.productDTO.productResponseDto;
import com.example.springDemo.service.impl.productService;
import com.example.springDemo.service.productInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {
    @Autowired
    private productInterface productInterface;

    @PostMapping(value="/search")
    public productResponseDto searchProducts(@RequestBody productRequestDto productRequestDto){
        return productInterface.searchProducts(productRequestDto);
    }


}
