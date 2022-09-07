package org.example.business.controller;

import org.example.business.service.BusinessService;
import org.example.common.dto.BusinessDTO;
import org.example.common.res.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/business")
public class BusinessController {

    private Logger logger = LoggerFactory.getLogger(BusinessController.class);

    @Resource
    private BusinessService businessService;

    @PostMapping("/buy")
    public Response handleBusiness(@RequestBody BusinessDTO businessDTO){
        logger.info("请求参数：{}", businessDTO.toString());
        return businessService.handleBusiness(businessDTO);
    }
}
