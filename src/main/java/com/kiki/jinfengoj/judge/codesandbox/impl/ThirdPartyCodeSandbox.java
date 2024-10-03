package com.kiki.jinfengoj.judge.codesandbox.impl;

import com.kiki.jinfengoj.judge.codesandbox.CodeSandbox;
import com.kiki.jinfengoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.kiki.jinfengoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author kiki
 * @Description 第三方代码沙箱
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest request) {
        System.out.println("第三方代码沙箱");
        return null;
    }

    @Override
    public ExecuteCodeResponse executeCodeByDocker(ExecuteCodeRequest request) {
        return null;
    }

    @Override
    public ExecuteCodeResponse executeCodeByNative(ExecuteCodeRequest request) {
        return null;
    }
}
