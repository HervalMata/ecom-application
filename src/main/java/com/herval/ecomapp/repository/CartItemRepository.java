package com.herval.ecomapp.repository;

import com.herval.ecomapp.model.CartItem;
import com.herval.ecomapp.model.Product;
import com.herval.ecomapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);
    void deleteByUserAndProduct(User user, Product product);
    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
}
