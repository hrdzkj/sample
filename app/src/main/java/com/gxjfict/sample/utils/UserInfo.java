package com.gxjfict.sample.utils;

/**
 * Created by LiuYi on 2018/9/19.
 */

public class UserInfo {
    private static JsonData userInfo=JsonData.create("{}");

    public static JsonData getUserInfo() {
        return JsonData.create(userInfo.toString());
    }

    public static void setUserInfo(JsonData userInfo) {
        if (userInfo==null){
            UserInfo.userInfo = JsonData.create("{}");
        }else {
            UserInfo.userInfo = userInfo;
        }
    }


}
