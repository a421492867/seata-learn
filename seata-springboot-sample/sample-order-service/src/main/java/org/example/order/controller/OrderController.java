package org.example.order.controller;

import org.example.common.dto.OrderDTO;
import org.example.common.res.Response;
import org.example.order.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private IOrderService orderService;

    @PostMapping("create-order")
    public Response createOrder(@RequestBody OrderDTO orderDTO){
        logger.info("请求订单微服务：{}", orderDTO.toString());
        return orderService.createOrder(orderDTO);
    }
}
