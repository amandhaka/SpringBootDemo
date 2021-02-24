package com.example.springDemo.service.impl;

import com.example.springDemo.client.searchClient;
import com.example.springDemo.productDTO.productDto;
import com.example.springDemo.productDTO.productRequestDto;
import com.example.springDemo.productDTO.productResponseDto;
import com.example.springDemo.service.productInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class productService  implements productInterface {

    @Autowired
    private searchClient searchClient;
    @Override
    public productResponseDto searchProducts(productRequestDto productRequestDTO) {
        Map<String,Object> productResponse = searchClient.getProducts(productRequestDTO.getSearchTerm());
        List<Map<String,Object>> products=(List<Map<String,Object>>)((Map<String,Object>) productResponse.get("response")).get("docs");
        ArrayList<productDto> list=new ArrayList<>();
        for(Map<String,Object> product: products){
            productDto productDto=new productDto();
            productDto.setDescription((String)product.get("description"));
            productDto.setTitle((String)product.get("name"));
            productDto.setSalePrice(((Double)(product.get("salePrice"))).toString());
            int stock=(int) product.get("isInStock");
            if(stock>1){
                productDto.setInStock(true);
            } else{
                productDto.setInStock(false);
            }
            list.add(productDto);
        }


        String query = "stockLocation:"+ productRequestDTO.getStockLocation() ;
        Map<String,Object> productResponseLocation = searchClient.getProducts(query);
        List<Map<String,Object>> productsLocation=(List<Map<String,Object>>)((Map<String,Object>) productResponseLocation.get("response")).get("docs");
        ArrayList<productDto> listLocation=new ArrayList<>();
        for(Map<String,Object> product: productsLocation){
            productDto productDto=new productDto();
            productDto.setDescription((String)product.get("description"));
            productDto.setTitle((String)product.get("name"));
            productDto.setSalePrice(((Double)(product.get("salePrice"))).toString());
            productDto.setLocation(product.get("stockLocation").toString());
            int stock=(int) product.get("isInStock");
            if(stock>1){
                productDto.setInStock(true);
            } else{
                productDto.setInStock(false);
            }
            listLocation.add(productDto);
        }

        productResponseDto responseDto=new productResponseDto();
        responseDto.setProductDtoList(list);

        responseDto.setLocationList(listLocation);
        return responseDto;
    }
}
