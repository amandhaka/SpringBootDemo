package com.example.springDemo.productDTO;

import java.util.List;

public class productResponseDto {
    private List<productDto> productDtoList;
    private List<productDto> locationList;

    public List<productDto> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<productDto> locationList) {
        this.locationList = locationList;
    }

    public List<productDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<productDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
