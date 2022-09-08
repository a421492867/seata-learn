package org.example.from.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.from.entity.Account;
import org.example.from.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

@Service
@DubboService
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    public boolean prepare(BusinessActionContext businessActionContext, String accountNo, double amount) {
        final  String xid = businessActionContext.getXid();
        return Boolean.TRUE.equals(transactionTemplate.execute(transactionStatus -> {
            try {
                Account account = baseMapper.getAccountForUpdate(accountNo);
                if (account == null) {
                    throw new RuntimeException("账户不存在");
                }
                if (account.getAmount() - amount < 0) {
                    throw new RuntimeException("余额不足");
                }

                double freezedAmount = account.getFreezedAmount() + amount;
                account.setFreezedAmount(freezedAmount);
                baseMapper.updateById(account);
                logger.info("prepare account {}, amount {}, dtc transaction id : {}", accountNo, amount,
                        xid);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                transactionStatus.setRollbackOnly();
                return false;
            }
        }));
    }

    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        final String xid = businessActionContext.getXid();

        final String accountNo = String.valueOf(businessActionContext.getActionContext("accountNo"));

        final double amount = Double.parseDouble(String.valueOf(businessActionContext.getActionContext("amount")));

        return Boolean.TRUE.equals(transactionTemplate.execute((TransactionCallback<Boolean>) transactionStatus -> {
            try{
                Account account = baseMapper.getAccountForUpdate(accountNo);

                double newAmount = account.getAmount() - amount;
                if(newAmount < 0){
                    throw new RuntimeException("余额不足");
                }
                account.setAmount(newAmount);
                account.setFreezedAmount(account.getFreezedAmount() - amount);
                baseMapper.updateById(account);
                logger.info("commit account {}  amount {}, dtx transaction id : {}", accountNo, amount, xid);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                transactionStatus.setRollbackOnly();
                return false;
            }
        }));
    }

    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        final String xid = businessActionContext.getXid();

        final String accountNo = String.valueOf(businessActionContext.getActionContext("accountNo"));

        final double amount = Double.parseDouble(String.valueOf(businessActionContext.getActionContext("amount")));

        return Boolean.TRUE.equals(transactionTemplate.execute(transactionStatus -> {
            try{
                Account account = baseMapper.getAccountForUpdate(accountNo);
                if(account == null){
                    return true;
                }

                account.setFreezedAmount(account.getFreezedAmount() - amount);
                baseMapper.updateById(account);
                logger.info("Undo account {} amount {}, dtx transaction id : {}", accountNo, amount, xid);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                transactionStatus.setRollbackOnly();
                return false;
            }
        }));
    }
}
