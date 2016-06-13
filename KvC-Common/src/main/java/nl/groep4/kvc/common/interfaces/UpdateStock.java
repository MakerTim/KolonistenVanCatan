package nl.groep4.kvc.common.interfaces;

import java.util.EnumMap;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Resource;

public interface UpdateStock {

    public void updateStock(EnumMap<Resource, Integer> resources);

    public void updateStock(List<Card> cards);
}
