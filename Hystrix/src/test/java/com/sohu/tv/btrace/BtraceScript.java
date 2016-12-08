/**
 * Copyright (c) 2012 Sohu. All Rights Reserved
 */
package com.sohu.tv.btrace;

import static com.sun.btrace.BTraceUtils.jstack;
import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;


@BTrace
public class BtraceScript {
    @OnMethod(
            clazz = "com.sohu.tv.digg.vote.controller.VotingController",
            method = "votingList",
            location = @Location(Kind.CALL)
            )
    public static void btraceFun() {
        println("trace: ================");
        jstack();

    }
}
