package com.example;

import com.example.v1.HelloRequest;
import com.example.v1.HelloResponse;
import com.example.v1.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        try {
            HelloResponse response = HelloResponse.newBuilder().setMessage("You said: " + request.getMessage()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Throwable t) {
            responseObserver.onError(t);
        }
    }
}
