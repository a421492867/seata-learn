package org.example.stock.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.example.common.Constants;
import org.example.common.dto.CommodityDTO;
import org.example.common.res.Response;
import org.example.stock.entity.TStock;
import org.example.stock.mapper.StockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, TStock> implements IStockService {

    private Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Override
    public Response decreaseStock(CommodityDTO commodityDTO) {
        int stock = baseMapper.decreaseStock(commodityDTO.getCommodityCode(), commodityDTO.getCount());
        if(stock > 0){
            logger.info("decreaseStock : success");
        }else {
            logger.info("decreaseStock : fail");
        }
        return stock > 0 ? new Response(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getMsg(), null)
                : new Response(Constants.ResponseCode.FAIL.getCode(), Constants.ResponseCode.FAIL.getMsg(), null);
    }
}
