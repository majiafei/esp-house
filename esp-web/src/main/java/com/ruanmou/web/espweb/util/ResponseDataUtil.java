package com.ruanmou.web.espweb.util;


import com.ruanmou.web.espweb.common.ApiResponse;

/**
 * @ProjectName: espservers
 * @Auther: GERRY
 * @Date: 2018/12/10 11:34
 * @Description:
 */
public class ResponseDataUtil {
    public static ApiResponse setResponseData(String methodName) {
        //log.error("[fallback]->fail====>agencyService->methodName:"+methodName);
        ApiResponse userVOApiResponse = new ApiResponse();
        userVOApiResponse.setCode(-1);
        userVOApiResponse.setMessage("fallback->fail====>agencyService->methodName:"+methodName);
        return userVOApiResponse;
    }
}
