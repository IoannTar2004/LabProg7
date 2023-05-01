package server.manager;

import org.example.collections.Dragon;
import org.example.collections.DragonFields;
import org.example.tools.DragonOptions;
import server.tools.IdGenerator;
import server.tools.Sort;

import java.util.Date;

public class ObjectsManager extends CollectionManager {

    public void add(Object... args) {

        Dragon dragon = new Dragon();

        for(DragonFields fields: DragonFields.values()) {
            if (args[fields.ordinal()] != null) {
                dragon = new DragonOptions().dragonInput(dragon, fields, args[fields.ordinal()]);
            }
        }
        dragon.setId(IdGenerator.generate());
        dragon.setCreationDate(new Date());
        dragons.add(dragon);
        dragons.sort(new Sort.SortBySize());
    }

    public void add(Dragon dragon) {
        if (dragon != null) {
            dragons.add(dragon);
        }
    }

    public int length() {
        return dragons.size();
    }
    public void remove(Dragon dragon) {
        dragons.remove(dragon);
    }
    public void clear() {dragons.clear();}
    public void remove_first() {dragons.remove(0);}

    public Dragon replace(Long id, Object... args) {
        Dragon dragon = new ObjectsCollectionManager().getDragonById(id);
        for(DragonFields fields: DragonFields.values()) {
            if (args[fields.ordinal()] != null) {
                dragon = new DragonOptions().dragonInput(dragon, fields, args[fields.ordinal()]);
            }
        }
        return dragon;
    }
}
