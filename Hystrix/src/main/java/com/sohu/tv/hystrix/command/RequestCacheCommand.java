/**
 * Copyright (c) 2012 Sohu. All Rights Reserved
 */
package com.sohu.tv.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

public class RequestCacheCommand extends HystrixCommand<String> {


    private final int id;
    public RequestCacheCommand( int id) {
        super(HystrixCommandGroupKey.Factory.asKey("RequestCacheCommand"));
        this.id = id;
    }
    @Override
    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName() + " execute id=" + id);
        return "executed=" + id;
    }

    //重写getCacheKey方法,实现区分不同请求的逻辑
    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }
    /**
     * @description:
     * @author anzengli (anzengli@sohu-inc.com)
     * @date 2016-11-17 上午10:46:26
     * @version V1.0
     * @param args
     * @return void
     * @throws
     *
     */
    public static void main(String[] args) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            RequestCacheCommand command2a = new RequestCacheCommand(2);
            RequestCacheCommand command2b = new RequestCacheCommand(3);
            System.out.println("command2a.execute() == " + command2a.execute());
            //isResponseFromCache判定是否是在缓存中获取结果
            System.out.println("command2a.isResponseFromCache() == " + command2a.isResponseFromCache());
            System.out.println("command2b.execute() == " + command2b.execute());
            System.out.println("command2b.isResponseFromCache() == " + command2b.isResponseFromCache());
        } finally {
            context.shutdown();
        }
        context = HystrixRequestContext.initializeContext();
        try {
            RequestCacheCommand command3b = new RequestCacheCommand(2);
            System.out.println("command3b.execute() == " + command3b.execute());
            System.out.println("command3b.isResponseFromCache() == " + command3b.isResponseFromCache());
        } finally {
            context.shutdown();
        }
    }

}
