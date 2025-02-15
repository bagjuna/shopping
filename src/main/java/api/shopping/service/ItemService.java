package api.shopping.service;

import api.shopping.domain.Item;
import api.shopping.repository.ItemRepository;
import api.shopping.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    // create
    public void save(Item item) {
        itemRepository.save(item);
    }

    // read
    @Transactional(readOnly = true)
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    // update
    public void update(Item item) {
        itemRepository.save(item);
    }

    // delete
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }


    public List<ItemResponse> getList() {
        List<Item> all = itemRepository.findAll();
        List<ItemResponse> itemResponses = new ArrayList<>();
        for (Item item : all) {
            itemResponses.add(new ItemResponse(item.getName(), item.getDescription(), item.getPrice(), item.getQuantity()));
        }
        return itemResponses;
    }

    public List<ItemResponse> getListByCond(String name, String description, Integer price, Integer quantity) {
        log.info("name = " + name);
        log.info("description = " + description);
        log.info("price = " + price);
        log.info("quantity = " + quantity);
        List<Item> allWithCondition = itemRepository.findAllWithCondition(name, description, price, quantity);

        List<ItemResponse> itemResponses = new ArrayList<>();
        for (Item item : allWithCondition) {
            itemResponses.add(new ItemResponse(item.getName(), item.getDescription(), item.getPrice(), item.getQuantity()));
        }
        return itemResponses;
    }

}
