package com.jaafar.micromessageconsummer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import org.springframework.amqp.support.converter.MessageConverter;

import org.springframework.amqp.core.Binding;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

@Configuration
public class RabbitMQ {
  @Value("${spring.rabbitmq.host}")
  String host;

  @Value("${spring.rabbitmq.username}")
  String username;

  @Value("${spring.rabbitmq.password}")
  String password;

  @Value("${spring.rabbitmq.template.default-receive-queue}")
  String queue;

  @Value("${spring.rabbitmq.template.exchange}")
  String exchange;

  @Value("${spring.rabbitmq.template.routing-key}")
  String routingKey;


  @Bean
  CachingConnectionFactory connectionFactory() {
      CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
      cachingConnectionFactory.setUsername(username);
      cachingConnectionFactory.setPassword(password);
      return cachingConnectionFactory;
  }

  @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

  @Bean RabbitTemplate rabbitTemplate( CachingConnectionFactory connectionFactory) {
      RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
      rabbitTemplate.setMessageConverter(jsonMessageConverter());
      return rabbitTemplate;
     
  }

  @Bean
  public TopicExchange exchange() {
      return new TopicExchange(exchange);
  }

  @Bean
  public Queue queue() {
      return new Queue(queue, true);
  }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
  
}