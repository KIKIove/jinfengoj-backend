package com.kiki.jinfengoj.judge.codesandbox;

import com.kiki.jinfengoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.kiki.jinfengoj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.kiki.jinfengoj.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * @author kiki
 * @Description 代码沙箱工厂类
 */
public class CodeSandboxFactory {

    /**
     * 根据传入的参数获取对应的沙箱实例
     * @param type
     * @return
     */
    public static CodeSandbox newInstance(String type) {
        switch (type){
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
