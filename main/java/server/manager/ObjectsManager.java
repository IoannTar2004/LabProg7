package server.manager;

import org.example.collections.Dragon;
import org.example.collections.DragonFields;
import org.example.tools.DragonOptions;
import server.database.DataBaseStuds;
import server.tools.IdGenerator;

public class ObjectsManager extends CollectionManager {

    public void insert(Object... args) {

        Dragon dragon = new Dragon();

        for(DragonFields fields: DragonFields.values()) {
            if (args[fields.ordinal()] != null) {
                dragon = new DragonOptions().dragonInput(dragon, fields, args[fields.ordinal()]);
            }
        }
        dragon.setId(IdGenerator.generate());
        new DataBaseStuds().insert(dragon);
        dragons.add(dragon);
    }

    public void insert(Dragon dragon) {
        dragon.setId(IdGenerator.generate());
        new DataBaseStuds().insert(dragon);
        dragons.add(dragon);
    }

    public void addFromDataBase(Dragon dragon) {
        dragons.add(dragon);
    }

    public int length() {
        return dragons.size();
    }
    public void remove(Dragon dragon) {
        dragons.remove(dragon);
    }

    public void clear(String login) {
        int i = 0;
        while (i < dragons.size()) {
            if (login.equals(dragons.get(i).getUserLogin())) {
                dragons.remove(i);
                continue;
            }
            i++;
        }
    }

    public void remove_first(String login) {
        while (true) {
            if ()
        }
    }

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
