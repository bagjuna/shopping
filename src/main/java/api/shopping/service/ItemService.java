package api.shopping.service;

import api.shopping.domain.Item;
import api.shopping.repository.ItemRepository;
import api.shopping.request.ItemRequest;
import api.shopping.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    // update
    @Transactional
    public void update(Long id, ItemRequest request) {
        Item findItem = itemRepository.findById(id).orElse(null);
        findItem.setName(request.getName());
        findItem.setDescription(request.getDescription());
        findItem.setPrice(request.getPrice());
        findItem.setQuantity(request.getQuantity());
    }

    // delete
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

//
//    public Page<ItemResponse> getList(Pageable pageable) {
////        List<Item> all = itemRepository.findAll(pageable).getContent();
//        Page<Item> all1 = itemRepository.findAll(pageable);

    /// /        List<ItemResponse> itemResponses = new ArrayList<>();
    /// /        for (Item item : all) {
    /// /            itemResponses.add(new ItemResponse(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getQuantity()));
    /// /        }
    /// /        log.info("itemResponses: {}", itemResponses);
//        return all1.map(item -> new ItemResponse(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getQuantity()));
//    }
    public Page<ItemResponse> getList(Pageable pageable) {
        Page<Item> all1 = itemRepository.findAll(pageable);
        log.info("all1: {}", all1);
        return all1.map(item -> new ItemResponse(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getQuantity(), item.getCreatedAt(),item.getMember().getId())
        );
    }

    public List<ItemResponse> getListByCond(String name, String description, Integer price, Integer quantity) {
        List<Item> allWithCondition = itemRepository.findAllWithCondition(name, description, price, quantity);

        List<ItemResponse> itemResponses = new ArrayList<>();
        for (Item item : allWithCondition) {
            itemResponses.add(new ItemResponse(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getQuantity(), item.getCreatedAt(), item.getMember().getId()));
        }
        return itemResponses;
    }

}
