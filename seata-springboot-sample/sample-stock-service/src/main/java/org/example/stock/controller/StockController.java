package org.example.stock.controller;

import org.example.common.dto.CommodityDTO;
import org.example.common.res.Response;
import org.example.stock.service.IStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/stock")
public class StockController {

    private Logger logger = LoggerFactory.getLogger(StockController.class);

    @Resource
    private IStockService stockService;

    @PostMapping("dec-stock")
    public Response decreaseStock(@RequestBody CommodityDTO commodityDTO){
        logger.info("请求库存微服务 : {}" , commodityDTO.toString());
        return stockService.decreaseStock(commodityDTO);

    }
}
