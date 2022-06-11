package com.test.it;

import org.springframework.context.annotation.ConfigurationCondition;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2022/1/21 16:31
 */
public class OnBeanCondition extends FilteringSpringBootCondition implements ConfigurationCondition {
    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return null;
    }
}
