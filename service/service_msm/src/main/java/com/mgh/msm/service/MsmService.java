package com.mgh.msm.service;

import java.util.HashMap;

/**
 * @author MGH
 * @create 2022-0224 6:05 下午
 */
public interface MsmService {
    public Boolean sendMessage(String phone, String code, HashMap<String, Object> map);
}
