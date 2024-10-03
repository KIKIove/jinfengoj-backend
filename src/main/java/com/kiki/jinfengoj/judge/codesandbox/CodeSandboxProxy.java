package com.kiki.jinfengoj.judge.codesandbox;

import com.kiki.jinfengoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.kiki.jinfengoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kiki
 * @Description 代码沙箱代理类
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandbox{

    private final CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox){
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCodeByDocker(ExecuteCodeRequest request) {
        log.info("docker代码沙箱请求信息：" + request);
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCodeByDocker(request);
        log.info("docker代码沙箱响应信息：" + executeCodeResponse);
        return executeCodeResponse;
    }

    @Override
    public ExecuteCodeResponse executeCodeByNative(ExecuteCodeRequest request) {
        log.info("native代码沙箱请求信息：" + request);
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCodeByNative(request);
        log.info("native代码沙箱响应信息：" + executeCodeResponse);
        return executeCodeResponse;

    }
}
