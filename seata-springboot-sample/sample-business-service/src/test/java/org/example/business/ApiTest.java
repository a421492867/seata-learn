package org.example.business;

import org.apache.dubbo.config.annotation.Reference;
import org.example.business.service.BusinessService;
import org.example.business.service.BusinessServiceImpl;
import org.example.common.dto.BusinessDTO;
import org.example.common.dubbo.OrderDubboService;
import org.example.common.dubbo.StockDubboService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);

    @Reference
    private StockDubboService stockDubboService;

    @Reference
    private OrderDubboService orderDubboService;

    @Resource
    private BusinessService businessService;

    @Test
    public void test(){
        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setUserId("1");
        businessDTO.setCommodityCode("C201901140001");
        businessDTO.setName("fan");
        businessDTO.setCount(50);
        businessDTO.setAmount(new BigDecimal(100));
        businessService.handleBusiness(businessDTO);
    }
}
