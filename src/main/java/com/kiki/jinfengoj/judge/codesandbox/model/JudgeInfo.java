package com.kiki.jinfengoj.judge.codesandbox.model;

import lombok.Data;

/**
 * @author kiki
 * @date 2024/9/30
 * @Description 题目配置
 */
@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;
    
    /**
     * 消耗内存
     */
    private Long memory;

    /**
     * 消耗时间
     */
    private Long time;

}
