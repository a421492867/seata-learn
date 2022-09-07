package org.example.account.controller;

import org.example.account.service.IAccountService;
import org.example.common.dto.AccountDTO;
import org.example.common.res.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("account")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Resource
    private IAccountService accountService;

    @PostMapping("/dec-account")
    public Response decAccount(@RequestBody AccountDTO accountDTO){
        logger.info("请求账户微服务：{}", accountDTO.toString());
        return accountService.decreaseAccount(accountDTO);
    }
}
