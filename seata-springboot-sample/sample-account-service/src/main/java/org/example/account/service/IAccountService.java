package org.example.account.service;


import org.example.common.dto.AccountDTO;
import org.example.common.res.Response;

public interface IAccountService {

    Response decreaseAccount(AccountDTO accountDTO);
}
