package org.example.stock.service;

import org.example.common.dto.CommodityDTO;
import org.example.common.res.Response;

public interface IStockService {

    Response decreaseStock(CommodityDTO commodityDTO);
}
