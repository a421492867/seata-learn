package org.example.to.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("account")
public class Account {

    private String accountNo;

    private double amount;

    private double freezedAmount;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFreezedAmount() {
        return freezedAmount;
    }

    public void setFreezedAmount(double freezedAmount) {
        this.freezedAmount = freezedAmount;
    }
}
