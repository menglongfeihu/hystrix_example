/**
 * Copyright (c) 2012 Sohu. All Rights Reserved
 */
package com.sohu.tv.hystrix.command;

import java.io.IOException;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class CircuitBreakerCommand extends HystrixCommand<String> {
    private final String name;
    public CircuitBreakerCommand(String name) {
        // 最少配置:指定命令组名(CommandGroup)
        //super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey(name)));
        this.name = name;
    }


    @Override
    protected String getFallback() {
        return "exeucute Falled";
    }
    @Override
    protected String run() throws Exception {
        return "Hello " + name + " thread:" + Thread.currentThread().getName();
    }
    /**
     * @throws IOException
     * @description:
     * @author anzengli (anzengli@sohu-inc.com)
     * @date 2016-11-17 下午4:16:47
     * @version V1.0
     * @param args
     * @return void
     * @throws
     *
     */
    public static void main(String[] args) throws IOException {

        CircuitBreakerCommand command = new CircuitBreakerCommand("hystrix");
        HystrixCommandProperties properties = command.getProperties();

        System.out.println("executionIsolationStrategy = " + properties.executionIsolationStrategy().get());
        System.out.println("executionIsolationSemaphoreMaxConcurrentRequests = " + properties.executionIsolationSemaphoreMaxConcurrentRequests().get());
        System.out.println("===================================================");
        ConfigurationManager.loadCascadedPropertiesFromResources("application");
        System.setProperty("archaius.configurationSource.defaultFileName","config.properties"); //Another option
        DynamicStringProperty sampleProp = DynamicPropertyFactory.getInstance().getStringProperty("hystrix.command.hystrix.execution.isolation.thread.timeoutInMilliseconds", "");
        System.setProperty("hystrix.command.hystrix.execution.isolation.thread.timeoutInMilliseconds",sampleProp.get()); //Another option

        properties = command.getProperties();
        System.out.println("executionIsolationStrategy = " + properties.executionIsolationStrategy().get());
        System.out.println("executionIsolationSemaphoreMaxConcurrentRequests = " + properties.executionIsolationSemaphoreMaxConcurrentRequests().get());

    }

}
