package com.dglbc.websocket.controller;

import com.dglbc.websocket.entity.Greeting;
import com.dglbc.websocket.entity.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

/**
 * Created by gdlbc on 2017/6/30.
 */
@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

//    @MessageMapping("/hello")
//    public Greeting greeting1(HelloMessage message) throws InterruptedException {
//        System.out.println("Hello:"+message);
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hi," + message.getName() );
//    }
}
