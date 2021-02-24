package com.example.springDemo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "search-client", url = "http://10.177.68.77:8983/")
public interface searchClient {
    @RequestMapping(method = RequestMethod.GET,path = "/solr/productCollection/select" )
    Map<String, Object> getProducts(@RequestParam("q") String query);

}
