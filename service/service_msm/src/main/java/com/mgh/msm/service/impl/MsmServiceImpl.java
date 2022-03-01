package com.mgh.msm.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.mgh.msm.service.MsmService;
import com.mgh.msm.utils.ConstantPropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author MGH
 * @create 2022-0224 10:31 上午
 */
@Service("msmServiceImpl")
@Slf4j
public class MsmServiceImpl implements MsmService {
    @Override
    public Boolean sendMessage(String phone, String code, HashMap<String, Object> map) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysAction("SendSms");
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.putQueryParameter("PhoneNumbers", "18838166131");
        request.putQueryParameter("SignName", "阿里云短信测试");
        request.putQueryParameter("TemplateCode", "SMS_154950909");
        request.putQueryParameter("TemplateParam", JSONObject.valueToString(map));
        log.info("发送信息：" + JSONObject.valueToString(map));
        try {
            CommonResponse response = client.getCommonResponse(request);
            boolean result = response.getHttpResponse().isSuccess();
            log.info(new Gson().toJson(response));
            return result;
        } catch (ClientException e) {
            log.info("ErrCode:" + e.getErrCode());
            log.info("ErrMsg:" + e.getErrMsg());
            log.info("RequestId:" + e.getRequestId());
        }
        return null;
    }


}
