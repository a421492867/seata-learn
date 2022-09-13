package org.example.from.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.from.entity.Account;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    Account getAccountForUpdate(@Param("accountNo") String accountNo);

    void updateAccount(Account account);

}
