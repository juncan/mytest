package com.test.it;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2022/1/21 16:30
 */
public interface Condition {
    boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata);
}
