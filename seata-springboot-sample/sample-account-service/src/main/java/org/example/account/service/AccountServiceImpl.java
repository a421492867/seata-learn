package org.example.account.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.example.account.entity.TAccount;
import org.example.account.mapper.AccountMapper;
import org.example.common.Constants;
import org.example.common.dto.AccountDTO;
import org.example.common.res.Response;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, TAccount> implements IAccountService{


    @Override
    public Response decreaseAccount(AccountDTO accountDTO) {
        int account = baseMapper.decreaseAccount(accountDTO.getUserId(), accountDTO.getAmount().doubleValue());
        return account > 0 ? new Response(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getMsg(), null)
                : new Response(Constants.ResponseCode.FAIL.getCode(), Constants.ResponseCode.FAIL.getMsg(), null);

    }
}
