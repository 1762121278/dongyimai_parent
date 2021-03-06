package com.offcn.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.search.service.ItemSearchService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/itemsearch")
public class SearchController {

    @Reference
    private ItemSearchService itemSearchService;


    @RequestMapping("/search")
    public Map<String,Object> search(@RequestBody Map searchMap){
        Map<String, Object> map = itemSearchService.search(searchMap);
        return map;
    }
}
