package org.example.from.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

public interface IAccountService {


    @TwoPhaseBusinessAction(name = "firstTccAction", commitMethod = "commit", rollbackMethod = "rollback")
    public boolean prepare(BusinessActionContext businessActionContext,
                           @BusinessActionContextParameter(paramName = "accountNo") String accountNo,
                           @BusinessActionContextParameter(paramName = "amount") double amount);

    public boolean commit(BusinessActionContext businessActionContext);

    public boolean rollback(BusinessActionContext businessActionContext);
}
