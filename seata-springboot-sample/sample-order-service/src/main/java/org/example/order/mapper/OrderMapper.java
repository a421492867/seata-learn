package org.example.order.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.order.entity.TOrder;

@Mapper
public interface OrderMapper extends BaseMapper<TOrder> {

    void createOrder(@Param("order") TOrder order);
}
