package org.example.common.dubbo;

import org.example.common.dto.OrderDTO;
import org.example.common.res.Response;

public interface OrderDubboService {

    Response createOrder(OrderDTO orderDTO);
}
