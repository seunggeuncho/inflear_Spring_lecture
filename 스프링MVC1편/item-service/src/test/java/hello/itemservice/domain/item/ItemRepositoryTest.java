package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.storeClear();
    }

    @Test
    void save(){
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findByID(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    public void findAll(){
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);
        Item itemC = new Item("itemC", 300000, 100);

        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);
        //when
        List<Item> items = itemRepository.findAll();

        //then
        assertThat(items.size()).isEqualTo(3);
        assertThat(items).contains(itemA,itemB,itemC);
    }

    @Test
    public void updateItem(){
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item saveItem = itemRepository.save(itemA);
        Long itemID = saveItem.getId();

        //when
        Item updateA = new Item("updateA", 20000,20);
        itemRepository.update(itemID, updateA);

        //then
        Item findItem = itemRepository.findByID(itemID);

        assertThat(findItem.getItemName()).isEqualTo(updateA.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateA.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateA.getQuantity());
    }
}
