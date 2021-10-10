package ru.veryprost.webSocket2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

}
/*
WebSocketConfig аннотирован @Configuration, чтобы указать, что это класс конфигурации Spring. Он также аннотирован
@EnableWebSocketMessageBroker. Как следует из названия, @EnableWebSocketMessageBroker включает обработку сообщений
WebSocket, поддерживаемую брокером сообщений.

Метод configureMessageBroker() реализует метод по умолчанию в WebSocketMessageBrokerConfigurer для настройки брокера
сообщений. Он начинается с вызова enableSimpleBroker(), чтобы включить простой брокер сообщений на основе памяти для
передачи приветственных сообщений обратно клиенту в пункты назначения с префиксом /topic.
Он также назначает префикс /app для сообщений, которые связаны с методами, аннотированными @MessageMapping.
Этот префикс будет использоваться для определения всех отображений сообщений. Например, /app/start - это конечная точка,
для обработки которой привязан метод MainController.generate().

Метод registerStompEndpoints() регистрирует конечную точку /gs-guide-websocket, позволяя SockJS использовать
альтернативные транспорты, если WebSocket недоступен. Клиент SockJS будет пытаться подключиться к /gs-guide-websocket
и использовать наилучший доступный транспорт (websocket, xhr-streaming, xhr-polling и так далее).
 */