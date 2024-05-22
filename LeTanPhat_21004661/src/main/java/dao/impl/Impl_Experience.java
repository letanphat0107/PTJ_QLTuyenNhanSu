/*
 * @ (#) Impl_Experience.java     1.0   5/22/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao.impl;

import dao.Dao_Experience;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 5/22/2024
 * @version:  1.0
 */
public class Impl_Experience implements Dao_Experience{
    EntityManager em;
    public Impl_Experience() {
        em = Persistence.createEntityManagerFactory("JPARMI_SQL").createEntityManager();
    }
    @Override
    public Map<Position, Integer> listYearsOfExperrienceByPosition(String cadiateID) {
        Map<Position, Integer> map = new HashMap<>();
        String sql = "SELECT p.position_id, e.candidate_id, p.name, DATEDIFF(day, e.from_date, e.to_date ) AS working_time FROM positions p JOIN experiences e ON p.position_id = e.position_id WHERE e.candidate_id = ?";

        try{
            List<Object[]> list = em.createNativeQuery(sql).setParameter(1, cadiateID).getResultList();
            list.forEach(e -> {
                Position p = em.find(Position.class, (String) e[0]);
                map.put(p, ((Number) e[3]).intValue());
            });
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
