package com.kiki.jinfengoj.model.dto.question;

import lombok.Data;

/**
 * @author kiki
 * @date 2024/9/30
 * @Description 题目用例
 */
@Data
public class JudgeCase {
    /**
     * 输入
     */
    private String input;

    /**
     * 输出
     */
    private String output;
}
