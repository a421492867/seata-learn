package org.example.common.dubbo;

import org.example.common.dto.CommodityDTO;
import org.example.common.res.Response;

public interface StockDubboService {

    Response decreaseStock(CommodityDTO commodityDTO);
}
