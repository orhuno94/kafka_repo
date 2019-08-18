package producer;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
 
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;

public class SimpleProducer {

	Producer producer;
	String topicName;
	Long CLIENT_ID;

	public SimpleProducer(String topicName){

		// Configure the Producer
		Properties configProperties = new Properties();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"localhost:9092");
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				LongSerializer.class.getName());
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");

		this.producer = new KafkaProducer<String, String>(configProperties);
		this.topicName=topicName;
	}

	public void sendMessage(ArrayList<String> lines){
		for(int i=0; i<lines.size(); i++){
			ProducerRecord<Long, String> rec = new ProducerRecord<>(
					this.topicName, lines.get(i));
			this.producer.send(rec);
			System.out.println("Message Sent!");

		}
	}
}