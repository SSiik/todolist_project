//package com.example.backend.todolist;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MessagingConfig {
//
//    @Bean
//    public Queue queue1(){
//        return new Queue("BOARD_QUEUE",false);
//    }
////    @Bean
////    public Queue queue2(){
////        return new Queue("SUBBOARD_QUEUE",false);
////    }
////    @Bean
////    public Queue queue3(){
////        return new Queue("MEMO_QUEUE",false);
////    }
//
//    @Bean
//    public TopicExchange exchange(){
//        return new TopicExchange("test_exchange");
//    }
//
//    @Bean
//    public Binding binding1(Queue queue,TopicExchange exchange){
//        return BindingBuilder.bind(queue1()).to(exchange).with("BOARD_KEY");
//    }
//
////    @Bean
////    public Binding binding2(Queue queue,TopicExchange exchange){
////        return BindingBuilder.bind(queue2()).to(exchange).with("SUBBOARD_KEY");
////    }
////
////    @Bean
////    public Binding binding3(Queue queue,TopicExchange exchange){
////        return BindingBuilder.bind(queue3()).to(exchange).with("MEMO_KEY");
////    }
//
////    @Bean
////    public MessageConverter converter(){
////        return new Jackson2JsonMessageConverter();
////    }
////
////    @Bean
////    public AmqpTemplate template(ConnectionFactory connectionFactory){
////        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
////        rabbitTemplate.setMessageConverter(converter());
////        return rabbitTemplate;
////    }
//}
