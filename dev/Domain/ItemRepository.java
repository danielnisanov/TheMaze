package Domain;

import DAL.ItemDAO;
import DAL.ProductDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRepository implements IRepository <Item>{
    private Map<String,Item> items;
    private ItemDAO itemDAO;

    public ItemRepository() {
        items = new HashMap<>();
        try {
            itemDAO = new ItemDAO();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ItemDAO", e);
        }
    }

    @Override
    public void add(Item item) throws Exception{
        if(items.containsKey(item.getItemID())) {
            throw new Exception("Item " + item.getItemID() + " already exist.");
        }
        items.put((Integer.toString(item.getItemID())), item);
        itemDAO.add(item);
    }



    @Override
    public void remove(String id) throws Exception {
        isExists(Integer.valueOf(id));
        items.remove(id);
        itemDAO.remove(id);
    }

    @Override
    public Item get(String id) throws Exception {
        Item item = items.get(id);
        if (item == null) {
            item = itemDAO.get(id);
            if (item != null) {
                items.put(id, item);
            }
        }
        isExists(Integer.valueOf(id));
        return item;
    }



    public void moveItemToShelf(String id) throws Exception {
        isExists(Integer.valueOf(id));
        Item item = get(id);
        item.setOnShelf(true);
        itemDAO.update(item);
    }
    public boolean checkItemLocation(String id) throws Exception {
        Item item = items.get(id);
        if (item == null) {
            item = itemDAO.get(id);
            if (item != null) {
                items.put(id, item);
            }
        }
        isExists(Integer.valueOf(id));
        return item.isOnShelf();
    }

    public void updateItemDamaged(String id) throws Exception {
        isExists(Integer.valueOf(id));
        Item item = get(id);
        item.setDamaged(true);
        itemDAO.update(item);
    }


    public void isExists(int id) throws Exception {
        if (!items.containsKey(Integer.toString(id)) && itemDAO.get(Integer.toString(id)) == null) {
            throw new Exception("Item with id " + id + " doesn't exist.");
        }
    }

    @Override
    public String toString() {
        return "items=" + items ;
    }
}
