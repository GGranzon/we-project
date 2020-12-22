package com.woniuxy.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
      log.info("创建时填充创建时字段和修改时字段");
      this.setFieldValByName("gmtCreate",new Date(),metaObject);
      this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("修改时填充更新时间字段");
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
