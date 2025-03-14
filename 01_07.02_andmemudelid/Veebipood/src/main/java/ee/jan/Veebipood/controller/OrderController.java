package ee.jan.Veebipood.controller;


import ee.jan.Veebipood.entity.Order;
import ee.jan.Veebipood.entity.Product;
import ee.jan.Veebipood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    // TODO: Ei tagastataks koiki tellimusi
    // TODO: Pean votma front-endist AINULT ID ja mitte usaldama front-endist tulevad toote hinda
    @PostMapping("orders")
    public List<Order> addOrder(@RequestBody Order order) { // suure tahega viited entityle, vaikse tahega muutuja ja addorder suvaline.
        order.setCreated(new Date());
        double sum = 0;
        for (Product p: order.getProducts()) {
            sum = sum + p.getPrice();
            // sum += p.getPrice();
        }
//        for (int i = 0; i < order.getProducts().size(); i++) {
//            Product p = order.getProducts().get(i);
//            sum = sum + p.getPrice();
//            sum += p.getPrice();

        order.setTotalSum(sum);
        orderRepository.save(order);
        return orderRepository.findAll();
    }
}
