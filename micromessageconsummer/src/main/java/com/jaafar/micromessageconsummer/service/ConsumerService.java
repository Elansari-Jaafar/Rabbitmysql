package com.jaafar.micromessageconsummer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaafar.micromessageconsummer.domain.User;
import com.jaafar.micromessageconsummer.repository.UserRepository;

@Service
public class ConsumerService {
    @Autowired
    private UserRepository userRepository;
    
    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receivedMessage(User user){
        userRepository.save(user);
        logger.info("User received from the queue: " + user);
    }
    
}
