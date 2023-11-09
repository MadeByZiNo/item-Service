package hello.itemService.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA",10000,10);

        Item savedItem = itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        Item item1 = new Item("itemA",10000,10);
        Item item2 = new Item("itemB",50000,5);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).contains(item1,item2);
    }

    @Test
    void updateItem() {
        Item item = new Item("itemA",10000,10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        Item updateItem = new Item("item", 20000, 5);
        itemRepository.update(itemId,updateItem);

        Item findItem = itemRepository.findById(itemId);

        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}