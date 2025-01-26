package com.jaafar.micromessageproducer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jaafar.micromessageproducer.domain.User;

@Service
public class ProducerService {

  private final RabbitTemplate rabbitTemplate;
  @Value("${spring.rabbitmq.template.routing-key}")
  private  String routingkey;

  @Value("${spring.rabbitmq.template.exchange}")
  private  String exchange;

  
  public ProducerService(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

 

  public void sendMessage (User user){
    rabbitTemplate.convertAndSend(exchange, routingkey, user);
  } 

}