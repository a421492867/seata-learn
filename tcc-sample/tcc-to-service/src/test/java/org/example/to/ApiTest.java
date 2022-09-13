package org.example.to;

import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.example.common.dubbo.FromDubboService;
import org.example.common.dubbo.ToDubboService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Reference
    private FromDubboService fromDubboService;

    @Reference
    private ToDubboService toDubboService;

    @Test
    @GlobalTransactional
    public void transfer(){
        String from  = "A", to = "B";
        double amount = 120;

        String xid = RootContext.getXID();
        BusinessActionContext actionContext = new BusinessActionContext();
        actionContext.setXid(xid);

        boolean res = fromDubboService.prepare(actionContext, from, amount);
        if(!res){
            throw new RuntimeException(from + "预扣款失败");
        }

        res = toDubboService.prepare(actionContext, to, amount);
        if(!res){
            throw new RuntimeException(to + "预收款失败");
        }
        logger.info("success");

    }
}
