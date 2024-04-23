package dao;


import entity.Position;

import java.util.List;

public interface Dao_Position  {
    public List<Position> listPositions(String name, double salaryFrom, double salaryTo);
}
