package org.example.to.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

public interface IAccountService {
    public boolean prepare(BusinessActionContext businessActionContext,
                           @BusinessActionContextParameter(paramName = "accountNo") String accountNo,
                           @BusinessActionContextParameter(paramName = "amount") double amount);

    public boolean commit(BusinessActionContext businessActionContext);

    public boolean rollback(BusinessActionContext businessActionContext);
}
