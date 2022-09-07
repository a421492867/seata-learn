package org.example.order.service;

import org.example.common.dto.OrderDTO;
import org.example.common.res.Response;

public interface IOrderService {

    Response createOrder(OrderDTO orderDTO);
}
