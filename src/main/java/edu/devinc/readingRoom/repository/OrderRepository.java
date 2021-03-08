package edu.devinc.readingRoom.repository;

import edu.devinc.readingRoom.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
