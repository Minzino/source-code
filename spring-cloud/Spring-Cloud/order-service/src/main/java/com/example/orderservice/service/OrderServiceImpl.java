package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public OrderDto createOrder(OrderDto orderDetail) {
		orderDetail.setOrderId(UUID.randomUUID().toString());
		orderDetail.setTotalPrice(orderDetail.getQuantity() * orderDetail.getUnitPrice());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		OrderEntity orderEntity = modelMapper.map(orderDetail, OrderEntity.class);

		orderRepository.save(orderEntity);

		OrderDto returnValue = modelMapper.map(orderEntity, OrderDto.class);
		return returnValue;
	}

	@Override
	public OrderDto getOrderByOrderId(String orderId) {
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		OrderDto orderDto = new ModelMapper().map(orderEntity, OrderDto.class);
		return orderDto;
	}

	@Override
	public Iterable<OrderEntity> getOrderByUserId(String userId) {
		return orderRepository.findByUserId(userId);
	}
}
