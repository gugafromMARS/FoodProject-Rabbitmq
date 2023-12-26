package gsc.projects.orderservice.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {


    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    @Value("${spring.rabbitmq.queue.name}")
    private String queueName;


    @Bean
    public Queue createQueue(){
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange createExchange(){
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding createBinding(){
        return BindingBuilder.bind(createQueue())
                .to(createExchange())
                .with(routingKey);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
