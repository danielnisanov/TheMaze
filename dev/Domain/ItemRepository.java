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
    public void add(Item item) throws Exception {
        if (items.containsKey(Integer.toString(item.getItemID()))) {
            throw new Exception("Item " + item.getItemID() + " already exists.");
        }
        else {
            Item itemFromDB = itemDAO.get(Integer.toString(item.getItemID()));
            if (itemFromDB != null) {
                items.put(Integer.toString(item.getItemID()), itemFromDB);
            }
            else {
                itemDAO.add(item);
                items.put(Integer.toString(item.getItemID()), item);
            }
        }
    }

    @Override
    public void remove(String id) throws Exception {
        if (!items.containsKey(id)) {
            Item itemFromDB = itemDAO.get(id);
            if (itemFromDB == null) {
                throw new Exception("Item " + id + " doesn't exist.");
            }
            else {
                itemDAO.remove(id);
            }
        }
        else {
            items.remove(id);
            itemDAO.remove(id);
        }
    }

    @Override
    public Item get(String id) throws Exception {
        Item item = items.get(id);
        if (item != null) {
            return item;
        }
        else {
            item = itemDAO.get(id);
            if (item != null) {
                items.put(id, item);
                return item;
            }
            else {
                throw new Exception("Item " + id + " doesn't exist.");
            }
        }
    }

    public void moveItemToShelf(String id) throws Exception {
        Item item = items.get(id);
        if (item != null){
            item.setOnShelf(true);
            itemDAO.update(item);
        }
        else{
            item = itemDAO.get(id);
            if (item != null) {
                item.setOnShelf(true);
                itemDAO.update(item);
                items.put(id, item);
            }
            else {
                throw new Exception("Item " + id + " doesn't exist.");
            }
        }
    }
    public boolean checkItemLocation(String id) throws Exception {
        Item item = items.get(id);
        if (item != null) {
            return item.isOnShelf();
        }
        else{
            item = itemDAO.get(id);
            if (item != null) {
                items.put(id, item);
                return item.isOnShelf();
            }
            else{
                throw new Exception("Item " + id + " doesn't exist.");
            }
        }
    }

    public void updateItemDamaged(String id) throws Exception {
        Item item = items.get(id);
        if (item != null){ //in repo
            item.setDamaged(true);
            itemDAO.update(item);
        }
        else{ //not in repo
            item = itemDAO.get(id);
            if (item != null) { // not in repo, in db
                item.setDamaged(true);
                itemDAO.update(item);
                items.put(id, item);
            }
            else {
                throw new Exception("Item " + id + " doesn't exist.");
            }
        }
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
