package org.example.stock.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.stock.entity.TStock;

@Mapper
public interface StockMapper extends BaseMapper<TStock> {

    int decreaseStock(@Param("commodityCode") String commodityCode, @Param("count") Integer count);
}
