package com.knife.entity;
import com.knife.mybatis.entity.CollectHead;
import com.knife.mybatis.mapper.CollectHeadMapper;
import com.mybatisflex.core.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @Description: TODO
 * @Author geey
 * @Date 2023/7/31 15:44
 * @Version 1.0
 */
@SpringBootTest
class CollectHeadTest {

    @Autowired
    private CollectHeadMapper collectHeadMapper;

    @Test
   public void ss(){
        CollectHead collectHead = new CollectHead();
        collectHead.setCollectTime(LocalDateTime.now());
        collectHead.setCollectUserId("123");
        collectHead.setPrimaryPhone("123");
        collectHeadMapper.insert(collectHead);
//        构造 QueryWrapper，也支持使用 QueryWrapper.create() 构造，效果相同
        QueryWrapper query = new QueryWrapper();
        //query.where(TableDefs.COLLECT_HEAD.COLLECT_ID.ge(100));

    }

}