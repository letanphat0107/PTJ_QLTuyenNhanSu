package dao.impl;

import dao.Dao_Candidate;
import entity.Candidate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Impl_Candidate implements Dao_Candidate{
    EntityManager em;
    public Impl_Candidate() {
        em = Persistence.createEntityManagerFactory("JPARMI_SQL").createEntityManager();
    }
    @Override
    public Map<Candidate, Long> listCadidatesByCompanies() {
        //Liệt kê danh sách các ứng viên và số công ty mà các ứng viên này từng làm
        Map<Candidate, Long> map = new HashMap<>();
        String query = "SELECT c.candidate_id, c.full_name, COUNT(DISTINCT w.company_name) AS company_count FROM candidates c \n" +
                "                LEFT JOIN experiences w ON c.candidate_id = w.candidate_id \n" +
                "                GROUP BY c.candidate_id, c.full_name";

        try{
//            map = (Map<Candidate, Long>) em.createNativeQuery(query, Candidate.class)
//                    .getResultList()
//                    .stream().collect(Collectors.toMap(c -> (Candidate) c, c -> (Long) c));
            List<Object[]> list = em.createNativeQuery(query).getResultList();
            list.forEach(e -> {
                Candidate c = em.find(Candidate.class, (String) e[0]);
                map.put(c, ((Number) e[2]).longValue());
            });
             return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Candidate, Long> listCandidatesWithLongestWorking() {
        //Tìm danh sách các ứng viên đã làm việc trên một vị trí công việc nào đó có thời gian làm lâu nhất.
        Map<Candidate, Long> map = new HashMap<>();
        String query = "SELECT c.candidate_id, c.full_name, MAX(w.to_date - w.from_date) AS working_time FROM candidates c \n" +
                "                               LEFT JOIN experiences w ON c.candidate_id = w.candidate_id \n" +
                "                               GROUP BY c.candidate_id, c.full_name";

        try{
            List<Object[]> list = em.createNativeQuery(query).getResultList();
            list.forEach(e -> {
                Candidate c = em.find(Candidate.class, (String) e[0]);
                map.put(c, ((Number) e[2]).longValue());
            });
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
