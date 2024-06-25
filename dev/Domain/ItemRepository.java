package Domain;

import java.util.List;
import java.util.Map;

public class ItemRepository implements IRepository <Item>{
    private Map<String,Item> items;

    @Override
    public void add(Item item) {
        items.put((Integer.toString(item.getItemID())), item);
    }


    @Override
    public void remove(String id) throws Exception {
        isExists(Integer.valueOf(id));
        items.remove(id);
    }

    @Override
    public Item get(String id) throws Exception{
        isExists(Integer.valueOf(id));
        return items.get(id);
    }

    public void moveItemToShelf(String id)  throws Exception{
        isExists(Integer.valueOf(id));
        Item item = get(id);
        item.setOnShelf(true);
    }

    public boolean checkItemLocation(String id) throws Exception{
        isExists(Integer.valueOf(id));
        Item item = get(id);
        return item.isOnShelf();
    }

    public void updateItemDamaged (String id) throws Exception{
        isExists(Integer.valueOf(id));
        Item item = get(id);
        item.setDamaged(true);
    }

    public void isExists(int id) throws Exception{
        if (!items.containsKey(Integer.toString(id))){
            throw new Exception("Item with id " + id + " doesn't exist.");
        }
    }

    @Override
    public String toString() {
        return "items=" + items ;
    }
}
