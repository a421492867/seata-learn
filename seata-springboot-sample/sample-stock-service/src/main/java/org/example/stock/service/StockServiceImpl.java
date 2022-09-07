package org.example.stock.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.example.common.Constants;
import org.example.common.dto.CommodityDTO;
import org.example.common.res.Response;
import org.example.stock.entity.TStock;
import org.example.stock.mapper.StockMapper;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, TStock> implements IStockService {
    @Override
    public Response decreaseStock(CommodityDTO commodityDTO) {
        int stock = baseMapper.decreaseStock(commodityDTO.getCommodityCode(), commodityDTO.getCount());
        return stock > 0 ? new Response(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getMsg(), null)
                : new Response(Constants.ResponseCode.FAIL.getCode(), Constants.ResponseCode.FAIL.getMsg(), null);
    }
}
