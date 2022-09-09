package org.example.common.dubbo;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface ToDubboService {

    @TwoPhaseBusinessAction(name = "ToDubboService", commitMethod = "commit", rollbackMethod = "rollback")
    public boolean prepare(BusinessActionContext businessActionContext,
                           @BusinessActionContextParameter(paramName = "accountNo") String accountNo,
                           @BusinessActionContextParameter(paramName = "amount") double amount);

    public boolean commit(BusinessActionContext businessActionContext);

    public boolean rollback(BusinessActionContext businessActionContext);
}
