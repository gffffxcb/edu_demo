package com.mgh.msm.service;

import java.util.HashMap;

/**
 * @author MGH
 * @create 2022-0224 10:30 上午
 */
public interface MsmService {
    Boolean sendMessage(String phone, String code, HashMap<String, Object> map);
}
