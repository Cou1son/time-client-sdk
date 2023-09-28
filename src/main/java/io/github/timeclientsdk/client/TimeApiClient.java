package io.github.timeclientsdk.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import io.github.timeclientsdk.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * HttpUtil的请求封装有问题，得自己写
 * @author mitchell
 */
public class TimeApiClient {

    private String accessKey;

    private String secretKey;

    public TimeApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String methodGet(String url){
        String result = HttpRequest.get(url).addHeaders(getHeaderMap()).execute().body();
        return result;
    }


    public String methodPost(Object object,String url){
        //将Object转为JSON格式
        String json = JSONUtil.toJsonStr(object);

        HttpResponse execute = HttpRequest.post(url)
                //请求头
                .addHeaders(postHeaderMap(json))
                //请求体
                .body(json)
                //执行request请求
                .execute();

        return execute.body();
    }


    private Map<String,String> getHeaderMap(){
        Map<String,String> simpleMap = new HashMap<>();
        simpleMap.put("accessKey",accessKey);
        simpleMap.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        simpleMap.put("sign", SignUtils.getSign(secretKey));
        return simpleMap;
    }

    private Map<String,String> postHeaderMap(String body){
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("accessKey",accessKey);
        hashMap.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        hashMap.put("body",body);
        hashMap.put("sign", SignUtils.getSign(secretKey));
        return hashMap;
    }

}
