package dao.impl;

import dao.Dao_Position;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class Impl_Position implements Dao_Position {
    EntityManager em;
    public Impl_Position() {
        em = Persistence.createEntityManagerFactory("JPARMI_SQL").createEntityManager();
    }
    @Override
    public List<Position> listPositions(String name, double salaryFrom, double salaryTo) {
        //Liệt kê danh sách  các vị trí công việc khi biết tên  vị trí  (tìm tương đối)  và mức lương  khoảng từ,
        //kết quả sắp xếp theo tên vị trí công việc
        List<Position> positions = null;
        String query = "SELECT * FROM positions WHERE name LIKE '%" + name + "%' AND salary BETWEEN " + salaryFrom + " AND " + salaryTo + " ORDER BY name";

        try {
            positions = em.createNativeQuery(query, Position.class).getResultList();
            return positions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
