package com.alen.grpcex.helloservice;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.logging.Logger;

public class HelloServiceClientSide {
    private static Logger logger = Logger.getLogger(HelloServiceClientSide.class.getCanonicalName());

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        HelloResponse response = stub.hello(HelloRequest.newBuilder()
                        .setId(12)
                        .setName("Alex")
                        .build());
        logger.info("Response received from server: " + response.getResponse());
        channel.shutdown();
    }
}
