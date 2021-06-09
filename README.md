This project shows how grpc-spring-boot-starter hangs waiting for Kafka.

* Run HangsWaitingForKafkaApplication to trigger bug.
* Run HangsWaitingForKafkaWorkaroundApplication to show workaround.

The reason for hanging is that the bean `grpcInternalConfigurator` in `GRpcAutoConfiguration`
implements `java.util.Consumer` which also happens to be how you can define
a (Kafka) message consumer in Spring Cloud. Spring will try to create a topic called `grpcInternalConfigurator-in-0` on
the default binder service (Kafka in this case). Since Kafka isn't available when
app starts it will hang for a default of 60 s before the application context
is fully initialized.

The workaround assigns `grpcInternalConfigurator-in-0` to a NoOpBinder instead.
