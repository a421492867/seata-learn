package org.example.common.dubbo;

import org.example.common.dto.AccountDTO;
import org.example.common.res.Response;

public interface AccountDubboService {

    Response decreaseAccount(AccountDTO accountDTO);
}
