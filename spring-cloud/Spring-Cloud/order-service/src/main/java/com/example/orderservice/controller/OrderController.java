package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.messagequeue.OrderProducer;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/order-service")
public class OrderController {

	private final Environment env;
	private final OrderService orderService;
	private final KafkaProducer kafkaProducer;
	private final OrderProducer orderProducer;

	@Autowired
	public OrderController(Environment env, OrderService orderService, KafkaProducer kafkaProducer,
		OrderProducer orderProducer) {
		this.env = env;
		this.orderService = orderService;
		this.kafkaProducer = kafkaProducer;
		this.orderProducer = orderProducer;
	}

	@GetMapping("/health_check")
	public String status() {
		return String.format("Ot's Working ing Order Service on PORT %s",
			env.getProperty("local.server.port"));
	}

	@PostMapping(value = "/{userId}/orders")
	public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
		@RequestBody RequestOrder orderDetail) {

		log.info("Before add orders data");
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		OrderDto orderDto = modelMapper.map(orderDetail, OrderDto.class);
		orderDto.setUserId(userId);
		/* jpa */
		OrderDto createdOrder = orderService.createOrder(orderDto);
		ResponseOrder responseOrder = modelMapper.map(createdOrder, ResponseOrder.class);
		/* kafka*/
//		orderDto.setOrderId(UUID.randomUUID().toString());
//		orderDto.setTotalPrice(orderDetail.getQuantity() * orderDetail.getUnitPrice());
//		ResponseOrder returnValue = modelMapper.map(orderDto,ResponseOrder.class);

		/* Send an order to the Kafka */
//		kafkaProducer.send("example-catalog-topic",orderDto);
//		orderProducer.send("orders", orderDto);

		log.info("After added orders data");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
	}

	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) throws Exception {
		log.info("Before retrieve orders data");
		Iterable<OrderEntity> orderList = orderService.getOrderByUserId(userId);

		List<ResponseOrder> result = new ArrayList<>();
		orderList.forEach(v -> {
			result.add(new ModelMapper().map(v, ResponseOrder.class));
		});

		try {
			Thread.sleep(1000);
			throw new Exception("장애 발생");
		} catch(InterruptedException ex) {
			log.warn(ex.getMessage());
		}

		log.info("Add retrieved orders data");

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}

