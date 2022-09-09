package org.example.to.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.to.entity.Account;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    Account getAccountForUpdate(@Param("accountNo") String accountNo);
}
