package org.example.stock.dubbo;

import io.seata.core.context.RootContext;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.example.common.dto.CommodityDTO;
import org.example.common.dubbo.StockDubboService;
import org.example.common.res.Response;
import org.example.stock.service.IStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

@DubboService
public class StockDubboServiceImpl implements StockDubboService {

    private Logger logger = LoggerFactory.getLogger(StockDubboServiceImpl.class);

    @Resource
    private IStockService stockService;

    @Override
    public Response decreaseStock(CommodityDTO commodityDTO) {
        logger.info("全局事务id ：" + RootContext.getXID());
        return stockService.decreaseStock(commodityDTO);
    }
}
