package org.jsmart.zerocode.integration.tests.kafka.consume;

import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@Ignore("For running these Avro tests please uncomment the depdendency 'kafka-avro-serializer' in the 'pom.xml'." +
        "And also uncomment the '<repository>' section")
@TargetEnv("kafka_servers/kafka_test_server_avro.properties")
@RunWith(ZeroCodeUnitRunner.class)
public class KafkaConsumeAvroTest {

    @Test
    @JsonTestCase("kafka/consume/test_kafka_consume_avro_msg_json.json")
    public void testKafkaConsume_avroJson() throws Exception {
    }

    @Test
    @JsonTestCase("kafka/consume/test_kafka_consume_avro_msg_raw_int.json")
    public void testKafkaConsume_avroRaw() throws Exception {
    }

    @Test
    @JsonTestCase("kafka/consume/test_kafka_consume_avro_msg_raw_json.json")
    public void testKafkaConsume_avroRawJson() throws Exception {
    }

}
