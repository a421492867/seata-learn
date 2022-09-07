package org.example.order.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Reference;
import org.example.common.Constants;
import org.example.common.dto.AccountDTO;
import org.example.common.dto.OrderDTO;
import org.example.common.dubbo.AccountDubboService;
import org.example.common.res.Response;
import org.example.order.entity.TOrder;
import org.example.order.mapper.OrderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, TOrder> implements IOrderService {

    @Reference
    private AccountDubboService accountDubboService;

    @Override
    public Response createOrder(OrderDTO orderDTO) {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(orderDTO.getUserId());
        accountDTO.setAmount(orderDTO.getOrderAmount());
        Response accountRes = accountDubboService.decreaseAccount(accountDTO);
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(accountRes.getCode())){
            return new Response(Constants.ResponseCode.FAIL.getCode(), Constants.ResponseCode.FAIL.getMsg(), null);
        }

        orderDTO.setOrderNo(UUID.randomUUID().toString().replace("-", ""));

        TOrder tOrder = new TOrder();
        BeanUtils.copyProperties(orderDTO, tOrder);

        tOrder.setCount(orderDTO.getOrderCount());
        tOrder.setAmount(orderDTO.getOrderAmount().doubleValue());
        try {
            baseMapper.createOrder(tOrder);
        }catch (Exception e){
            return new Response(Constants.ResponseCode.FAIL.getCode(), Constants.ResponseCode.FAIL.getMsg(), null);
        }
        return new Response(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getMsg(),
                null);
    }
}
