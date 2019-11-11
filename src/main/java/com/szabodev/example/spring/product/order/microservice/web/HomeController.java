package com.szabodev.example.spring.product.order.microservice.web;

import com.szabodev.example.spring.product.order.microservice.dto.DeletedOrderDTO;
import com.szabodev.example.spring.product.order.microservice.dto.OrderRequestDTO;
import com.szabodev.example.spring.product.order.microservice.dto.OrderResponseDTO;
import com.szabodev.example.spring.product.order.microservice.dto.ProductOrderDTO;
import com.szabodev.example.spring.product.order.microservice.service.ProductOrderService;
import com.szabodev.example.spring.product.order.microservice.service.remote.ProductService;
import com.szabodev.example.spring.product.order.microservice.service.remote.ProductStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private static final String PRODUCT_PRODUCTS = "product/products";
    private static final String PRODUCT_ORDER_PRODUCT_ORDERS = "product-order/product-orders";
    private static final String PRODUCT_ORDER_NEW_ORDER = "product-order/new-order";
    private static final String REDIRECT_PRODUCT_ORDERS = "redirect:/orders";
    private static final String NEW_ORDER = "newOrder";

    private final ProductService productService;
    private final ProductOrderService productOrderService;
    private final ProductStoreService productStoreService;

    @GetMapping({"/", "/products"})
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        return PRODUCT_PRODUCTS;
    }

    @GetMapping("/products/{id}/order/{name}")
    public String products(Model model, @PathVariable Long id, @PathVariable String name) {
        model.addAttribute("productName", name);
        model.addAttribute(NEW_ORDER, ProductOrderDTO.builder().amount(1).productId(id).build());
        return PRODUCT_ORDER_NEW_ORDER;
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("orders", productOrderService.findAll(true));
        return PRODUCT_ORDER_PRODUCT_ORDERS;
    }

    @PostMapping("/orders")
    public String saveOrder(@ModelAttribute(NEW_ORDER) ProductOrderDTO order, Model model) {
        OrderResponseDTO orderResponse = productStoreService.requestOrder(
                OrderRequestDTO.builder().amount(order.getAmount()).productId(order.getProductId()).build()
        );
        if (orderResponse != null && "OK".equalsIgnoreCase(orderResponse.getStatus())) {
            Optional<ProductOrderDTO> existingOrder = productOrderService.findByProductId(order.getProductId());
            if (existingOrder.isPresent()) {
                existingOrder.get().setAmount(existingOrder.get().getAmount() + order.getAmount());
                ProductOrderDTO saved = productOrderService.save(existingOrder.get());
                log.info("Order updated: {}", saved);
            } else {
                ProductOrderDTO saved = productOrderService.save(order);
                log.info("Order saved: {}", saved);
            }
            return REDIRECT_PRODUCT_ORDERS;
        } else {
            model.addAttribute("errorMessage", "Cannot process order");
            return products(model);
        }
    }

    @GetMapping("/orders/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        productOrderService.findById(id).ifPresent(productOrder -> {
            log.info("Delete productOrder: {}", productOrder);
            productOrderService.deleteById(id);
            productStoreService.orderDeleted(DeletedOrderDTO.builder().productId(productOrder.getProductId()).amount(productOrder.getAmount()).build());
        });
        return REDIRECT_PRODUCT_ORDERS;
    }
}
