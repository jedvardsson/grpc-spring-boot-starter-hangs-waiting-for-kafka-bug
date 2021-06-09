package com.example;

import org.slf4j.Logger;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.Binding;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.messaging.MessageChannel;

import static org.slf4j.LoggerFactory.getLogger;

public class NoOpBinder implements Binder<MessageChannel, ConsumerProperties, ProducerProperties> {
    private static final Logger LOGGER = getLogger(NoOpBinder.class);
    @Override
    public Binding<MessageChannel> bindConsumer(
            String name, String group, MessageChannel inboundBindTarget, ConsumerProperties consumerProperties
    ) {
        LOGGER.warn("noop bindingConsumer: {}", name);
        return new NoOpBinding(name);
    }

    @Override
    public Binding<MessageChannel> bindProducer(String name, MessageChannel outboundBindTarget, ProducerProperties producerProperties) {
        LOGGER.warn("noop bindingProducer: {}", name);
        return new NoOpBinding(name);
    }

    private class NoOpBinding implements Binding<MessageChannel> {
        private String name;

        public NoOpBinding(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void unbind() {
            LOGGER.warn("noop unbind: {}", name);
        }

        @Override
        public String toString() {
            return "NoOpBinding{" +
                   "name='" + name + '\'' +
                   '}';
        }
    }
}
