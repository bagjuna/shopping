package api.shopping.repository;

import api.shopping.domain.Item;

import java.util.List;

public interface ItemCustomRepository {

    List<Item> findAllWithCondition(String itemName, String description, Integer price, Integer quantity);
}
