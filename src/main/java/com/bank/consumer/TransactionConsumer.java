package com.bank.consumer;

import com.bank.dto.TransactionEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class TransactionConsumer {
    @Inject
    ObjectMapper objectMapper;

    private static final Logger LOG = Logger.getLogger(TransactionConsumer.class);

    @Incoming("transactions-in")
    public void consume(JsonObject payload) {
        TransactionEvent event = objectMapper.convertValue(
                payload.getMap(),           // convert JsonObject → Map → POJO
                TransactionEvent.class
        );
        System.out.println("Event consumed: " + event);
        LOG.info("Event consumed: " + event);
    }
}
