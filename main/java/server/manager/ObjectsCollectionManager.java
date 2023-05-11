package server.manager;

import org.example.collections.Dragon;

import java.util.LinkedList;
import java.util.List;

public class ObjectsCollectionManager extends CollectionManager {
    public Dragon getDragonById(String login, long id) throws NullPointerException {
        Dragon dragon = dragons.stream().filter(dragon1 -> dragon1.getId() == id && dragon1.getUserLogin().equals(login))
                .findFirst().orElse(null);
        if (dragon != null) {
            return dragon;
        }
        throw new NullPointerException();
    }

    public Dragon getDragonById(long id) {
        Dragon dragon = dragons.stream().filter(dragon1 -> dragon1.getId() == id)
                .findFirst().orElse(null);
        if (dragon != null) {
            return dragon;
        }
        throw new NullPointerException();
    }

    public Dragon getDragonByIndex(int index) {return (Dragon) dragons.toArray()[index];}
    public List<Dragon> getAll() {return dragons;}

    public List<Dragon> ownerList(String login) {
        return new LinkedList<>(getAll()).stream().filter(dragon -> dragon.getUserLogin().equals(login)).toList();
    }

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
