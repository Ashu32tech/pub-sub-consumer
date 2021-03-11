package com.infogain.gcp.poc.pubsub.consumer;

import com.google.pubsub.v1.PubsubMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PubSubConsumerImpl extends PubSubConsumer {

    @Autowired
    private PubSubTemplate pubSubTemplate;

    @Value("${pubsub.subscription}")
    private String subscription;

    @Override
    public String subscription() {
        return subscription;
    }

    @Override
    protected void consume(BasicAcknowledgeablePubsubMessage basicAcknowledgeablePubsubMessage) {
        PubsubMessage message = basicAcknowledgeablePubsubMessage.getPubsubMessage();

        try {
            System.out.println(message.getData().toStringUtf8());
            System.out.println(message.getAttributesMap());
            String objectName = message.getAttributesMap().get("objectId");
            String bucketName = message.getAttributesMap().get("bucketId");
            String eventType = message.getAttributesMap().get("eventType");

            log.info("Event Type:::::" + eventType);
            log.info("File Name::::::" + objectName);
            log.info("Bucket Name::::" + bucketName);


        }catch(Exception ex) {
            log.error("Error Occured while receiving pubsub message:::::", ex);
        }
        basicAcknowledgeablePubsubMessage.ack();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void subscribe(){
        log.info("subscribing {} to {}", this.getClass().getSimpleName(), this.subscription());
        pubSubTemplate.subscribe(subscription(), this.consumer());
    }
}