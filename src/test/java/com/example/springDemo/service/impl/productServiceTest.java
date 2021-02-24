package com.example.springDemo.service.impl;

import com.example.springDemo.productDTO.productRequestDto;
import com.example.springDemo.client.searchClient;
import com.example.springDemo.productDTO.productResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class productServiceTest {

    @InjectMocks
    private productService searchService;

    @Mock
    private searchClient searchClient;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    public void teardown(){

    }
    @Test
    void searchProducts() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,Object> searchTermMockObject=objectMapper.readValue(
                new URL("file:src/test/resources/search.mock"),Map.class);
        Map<String,Object> locationMockObject=objectMapper.readValue(
                new URL("file:src/test/resources/location.mock"),Map.class);

        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:jakarta")).thenReturn(locationMockObject);

        productRequestDto requestDTO = new productRequestDto();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setStockLocation("jakarta");
        productResponseDto response = searchService.searchProducts(requestDTO);

        assertEquals(response.getProductDtoList().size(),10);
        assertEquals(response.getLocationList().size(),10);
    }

    @Test
    public void testGetProductsExceptionTest() throws IOException{
        ObjectMapper objectMapper=new ObjectMapper();
         Map<String,Object> searchTermMockObject=objectMapper.readValue(
                 new URL("file:src/test/resources/search.mock"),Map.class);
         Map<String,Object> locationMockObject=objectMapper.readValue(
                 new URL("file:src/test/resources/location.mock"),Map.class);
        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:jakarta")).thenThrow(NullPointerException.class);
        
        productRequestDto requestDTO = new productRequestDto();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setStockLocation("jakarta");
        productResponseDto response = searchService.searchProducts(requestDTO);

        assertEquals(response.getProductDtoList().size(),10);       
        assertEquals(response.getLocationList(),null);
        
        Mockito.verify(searchClient).getProducts("samsung");
        Mockito.verify(searchClient).getProducts("stockLocation:jakarta");

    }
}
