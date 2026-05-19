package com.bank.service;

import com.bank.dto.TransactionEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@ApplicationScoped
public class TransactionProducer {
    @Inject
    @Channel("transactions-out")
    Emitter<TransactionEvent> eventEmitter;

    public void publish(TransactionEvent event){
        eventEmitter.send(event);
    }
}
