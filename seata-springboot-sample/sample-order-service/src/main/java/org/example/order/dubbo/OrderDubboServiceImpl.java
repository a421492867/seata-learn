package org.example.order.dubbo;

import io.seata.core.context.RootContext;
import org.apache.dubbo.config.annotation.Service;
import org.example.common.dto.OrderDTO;
import org.example.common.dubbo.OrderDubboService;
import org.example.common.res.Response;
import org.example.order.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

@Service
public class OrderDubboServiceImpl implements OrderDubboService {

    private Logger logger = LoggerFactory.getLogger(OrderDubboServiceImpl.class);


    @Resource
    private IOrderService orderService;

    @Override
    public Response createOrder(OrderDTO orderDTO) {
        logger.info("全局事务Id : {}", RootContext.getXID());
        return orderService.createOrder(orderDTO);
    }
}
