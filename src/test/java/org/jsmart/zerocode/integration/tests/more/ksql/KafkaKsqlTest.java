package org.jsmart.zerocode.integration.tests.more.ksql;

import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@TargetEnv("kafka_servers/kafka_test_server_avro.properties")
@RunWith(ZeroCodeUnitRunner.class)
public class KafkaKsqlTest {

    @Ignore("This can be run only after adding confluent-avro maven dependencies")
    @Test
    @JsonTestCase("kafka/consume/ksql/test_ksql_query.json")
    public void testKafkaConsume_ksql() throws Exception {
    }

    @Ignore("Confluent Team is helping us to sort this out")
    @Test
    @JsonTestCase("kafka/consume/ksql/WIP_ISSUE_test_ksql_print_records.json")
    public void testKafkaConsume_printTopic() throws Exception {
    }

}
