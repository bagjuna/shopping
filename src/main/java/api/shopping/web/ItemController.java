package api.shopping.web;

import api.shopping.domain.Item;
import api.shopping.domain.Member;
import api.shopping.request.ItemRequest;
import api.shopping.response.ItemResponse;
import api.shopping.service.ItemService;
import api.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final MemberService memberService;
    private final ItemService itemService;


    @PostMapping("/save/item")
    public void save(@RequestParam("memberId") Long id, @RequestBody ItemRequest itemRequest) {
        Member member = memberService.findById(id);
        Item item = new Item(itemRequest.getName(), itemRequest.getDescription(), itemRequest.getPrice(), itemRequest.getQuantity(), member);
        itemService.save(item);
    }

    @GetMapping("/get")
    public Item findById(@RequestParam("id") Long id) {
        System.out.println("id = " + id);
        return itemService.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestParam("id") Long id, @RequestBody ItemRequest itemRequest) {
        Item findItem = itemService.findById(id);
        findItem.setName(itemRequest.getName());
        findItem.setDescription(itemRequest.getDescription());
        findItem.setPrice(itemRequest.getPrice());
        findItem.setQuantity(itemRequest.getQuantity());

        itemService.save(findItem);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        itemService.delete(id);
    }


    @GetMapping("/getList")
    public String getList() {
        log.info("getList");
        return itemService.getList().toString();
    }

    @GetMapping("/getListByCond")
    public List<ItemResponse> getList(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("price") Integer price, @RequestParam("quantity") Integer quantity) {
        return itemService.getListByCond(name, description, price, quantity);

    }

}
