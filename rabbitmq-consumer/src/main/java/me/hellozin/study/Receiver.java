package me.hellozin.study;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/*
* 어떤 종류의 message 기반 프로그램을 사용하든 발행된 메시지를 받기위한
* Receiver가 존재해야 한다.
* */
@Component
public class Receiver {

    /*
     * 메시지 수신을 인지하기 위해 CountDownLatch를 사용.
     * 실제 서비스에서는 사용되지 않을 것.
     * */
//    private CountDownLatch latch = new CountDownLatch(1);

    public void handleMessage(String message) {
        System.out.println("Received <" + message + ">");
//        latch.countDown();
    }

//    public CountDownLatch getLatch() {
//        return latch;
//    }

}
