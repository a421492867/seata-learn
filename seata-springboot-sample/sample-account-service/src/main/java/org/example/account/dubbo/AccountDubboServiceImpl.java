package org.example.account.dubbo;

import io.seata.core.context.RootContext;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.example.account.service.IAccountService;
import org.example.common.dto.AccountDTO;
import org.example.common.dubbo.AccountDubboService;
import org.example.common.res.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

@Service
public class AccountDubboServiceImpl implements AccountDubboService {

    private Logger logger = LoggerFactory.getLogger(AccountDubboServiceImpl.class);


    @Resource
    private IAccountService accountService;

    @Override
    public Response decreaseAccount(AccountDTO accountDTO) {
        logger.info("全局事务Id : " + RootContext.getXID());
        return accountService.decreaseAccount(accountDTO);
    }
}
