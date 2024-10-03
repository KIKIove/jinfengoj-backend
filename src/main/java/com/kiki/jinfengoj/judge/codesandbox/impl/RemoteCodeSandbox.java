package com.kiki.jinfengoj.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.kiki.jinfengoj.common.ErrorCode;
import com.kiki.jinfengoj.exception.BusinessException;
import com.kiki.jinfengoj.judge.codesandbox.CodeSandbox;
import com.kiki.jinfengoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.kiki.jinfengoj.judge.codesandbox.model.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.HttpClients;

/**
 * @author kiki
 * @Description 远程代码沙箱
 */
public class RemoteCodeSandbox implements CodeSandbox {

    /**
     * 调用docker代码沙箱
     * @param request
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCodeByDocker(ExecuteCodeRequest request) {
        System.out.println("远程docker代码沙箱---------");
        // String url = "http://localhost:8081/codebox/run";
        String url = "http://47.120.62.199:8081/codebox/run";
        String json = JSONUtil.toJsonStr(request);
        String responseStr = HttpUtil.createPost(url)
                .body(json)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)){
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR,responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }

    /**
     * 调用本地代码沙箱
     * @param request
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCodeByNative(ExecuteCodeRequest request) {
        System.out.println("远程本地代码沙箱----------");
        // String url = "http://localhost:8081/codebox/run";
        String url = "http://47.120.62.199:8081/codebox/run";
        String json = JSONUtil.toJsonStr(request);
        String responseStr = HttpUtil.createPost(url)
                .body(json)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)){
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR,responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
