package com.alen.grpcex.helloservice;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class HelloServiceServerSide {
    private static final Logger logger = Logger.getLogger(HelloServiceServerSide.class.getCanonicalName());

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder
                .forPort(6565)
                .addService(new HelloServiceServerSide.HelloService())
                .build();
        server.start();
        server.awaitTermination();
    }

    public static class HelloService extends HelloServiceGrpc.HelloServiceImplBase {
        @Override
        public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
//            super.hello(request, responseObserver);
            logger.info("received request: " + request.getId() + " : " + request.getName());
            HelloResponse response = HelloResponse.newBuilder()
                    .setId(request.getId())
                    .setResponse("Hello " + request.getName() + "!")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
