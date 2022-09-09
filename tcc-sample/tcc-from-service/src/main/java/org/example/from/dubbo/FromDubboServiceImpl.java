package org.example.from.dubbo;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.common.dubbo.FromDubboService;
import org.example.from.service.AccountServiceImpl;
import org.example.from.service.IAccountService;

import javax.annotation.Resource;

@DubboService
public class FromDubboServiceImpl implements FromDubboService {

    @Resource
    private AccountServiceImpl accountService;
    @Override
    public boolean prepare(BusinessActionContext businessActionContext, String accountNo, double amount) {
        return accountService.prepare(businessActionContext, accountNo, amount);
    }

    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        return accountService.commit(businessActionContext);
    }

    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        return accountService.rollback(businessActionContext);
    }
}
