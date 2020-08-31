package org.jsmart.zerocode.integration.tests.kafka.consume.negative;

import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@TargetEnv("kafka_servers/kafka_test_server_avro.properties")
@RunWith(ZeroCodeUnitRunner.class)
public class KafkaConsumeAvroNegativeTest {

    @Test
    @JsonTestCase("kafka/consume/negative/test_kafka_rest_proxy_avro_msg_wrong_value.json")
    public void testKafkaConsume_avroWrongValue() throws Exception {
    }

    @Ignore("For running Avro tests please uncomment the dependency 'kafka-avro-serializer' in the 'pom.xml' file")
    @Test
    @JsonTestCase("kafka/consume/negative/test_produce_step_direct_invalid_avro_msg.json")
    public void testKafkaWrongData_loadDirectTopic() throws Exception {
    }

}
