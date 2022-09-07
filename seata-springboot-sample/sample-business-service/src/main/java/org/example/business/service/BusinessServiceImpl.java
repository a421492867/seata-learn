package org.example.business.service;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.example.common.Constants;
import org.example.common.dto.BusinessDTO;
import org.example.common.dto.CommodityDTO;
import org.example.common.dto.OrderDTO;
import org.example.common.dubbo.OrderDubboService;
import org.example.common.dubbo.StockDubboService;
import org.example.common.res.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService{

    private Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);

    @Reference
    private StockDubboService stockDubboService;

    @Reference
    private OrderDubboService orderDubboService;

    @GlobalTransactional
    @Override
    public Response handleBusiness(BusinessDTO businessDTO) {
        logger.info("全局事务XID : {}", RootContext.getXID());

        //扣减库存
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityCode(businessDTO.getCommodityCode());
        commodityDTO.setCount(businessDTO.getCount());
        Response stockRes = stockDubboService.decreaseStock(commodityDTO);

        //创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(businessDTO.getUserId());
        orderDTO.setCommodityCode(businessDTO.getCommodityCode());
        orderDTO.setOrderCount(businessDTO.getCount());
        orderDTO.setOrderAmount(businessDTO.getAmount());
        Response orderRes = orderDubboService.createOrder(orderDTO);

        if(stockRes.getCode() != 200 || orderRes.getCode() != 200){
            throw new RuntimeException("异常");
        }
        return new Response(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getMsg(),
                null);
    }
}
