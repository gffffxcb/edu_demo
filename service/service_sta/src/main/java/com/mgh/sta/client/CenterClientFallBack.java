package com.mgh.sta.client;

import com.mgh.commanUtils.MyResult;
import org.springframework.stereotype.Component;

/**
 * @author MGH
 * @create 2022-0301 3:34 下午
 */
@Component
public class CenterClientFallBack implements CenterClient{
    @Override
    public MyResult getRegisterCount(String day) {
        return null;
    }
}
