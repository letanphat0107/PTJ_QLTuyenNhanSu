package dao;


import entity.Position;

import java.util.Map;

public interface Dao_Experience {
    public Map<Position, Integer> listYearsOfExperrienceByPosition(String cadiateID);
}
