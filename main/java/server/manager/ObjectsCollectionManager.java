package server.manager;

import org.example.collections.Dragon;

import java.util.List;

public class ObjectsCollectionManager extends CollectionManager {
    public Dragon getDragonById(Long id) {
        for (int i = 0; i < dragons.size(); i++) {
            Dragon dragon = (Dragon) dragons.toArray()[i];
            if (id == dragon.getId()) {
                return dragon;
            }
        }
        return null;
    }

    public Dragon getDragonByIndex(int index) {return (Dragon) dragons.toArray()[index];}
    public List<Dragon> getAll() {return dragons;}

    public String getName(Dragon dragon) {
        return dragon.getName();
    }
    public Long getId(Dragon dragon) {
        return dragon.getId();
    }
    public String getCoordinates(Dragon dragon) {
        return dragon.getCoordinates();
    }
    public String getColor(Dragon dragon) {
        return dragon.getColor();
    }
    public String getCharacter(Dragon dragon) {
        return dragon.getCharacter();
    }
    public double getCave(Dragon dragon) {
        return dragon.getCave();
    }
    public Integer getAge(Dragon dragon) {
        return dragon.getAge();
    }
    public  String getType(Dragon dragon) {
        return dragon.getType();
    }
}
