package com.herval.ecomapp.controller;

import com.herval.ecomapp.dto.CartItemRequest;
import com.herval.ecomapp.model.CartItem;
import com.herval.ecomapp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId, @RequestBody CartItemRequest request) {
        if (!cartService.addToCart(userId, request)) {
            return ResponseEntity.badRequest().body("Produto fora de estoque ou Usuário não encontrado ou Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long productId, @RequestHeader("X-User-ID") String userId) {
        boolean deleted = cartService.deleteItemFromCart(userId, productId);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(@RequestHeader("X-User-ID") String userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }
}
