package dao.impl;

import dao.Dao_Candidate;
import entity.Candidate;
import entity.Certificate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.*;
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
        String query =  "SELECT c.candidate_id, c.full_name, MAX(DATEDIFF(day, w.from_date, w.to_date )) AS working_time \n" +
                "                FROM candidates c \n" +
                "                LEFT JOIN experiences w ON c.candidate_id = w.candidate_id \n" +
                "                where  w.from_date is not null and  w.to_date is not null\n" +
                "                GROUP BY c.candidate_id, c.full_name";

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

    @Override
    public boolean addCandidate(Candidate candidate) {
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            em.persist(candidate);
            et.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            et.rollback();
        }
        return false;
    }

    @Override
    public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
        Map<Candidate, Set<Certificate>> map = new HashMap<>();
        String sql = "SELECT c.candidate_id, c.full_name, cer.certificate_id, cer.name FROM candidates c \n" +
                "                LEFT JOIN certificates cer ON c.candidate_id = cer.candidate_id";

        try{
            List<Object[]> list = em.createNativeQuery(sql).getResultList();
            // Map tạm thời để lưu trữ các Candidate và Certificate tương ứng
            Map<String, Candidate> candidateMap = new HashMap<>();
            Map<String, Set<Certificate>> certificateMap = new HashMap<>();

            for (Object[] row : list) {
                String candidateId = (String) row[0];
                String candidateName = (String) row[1];
                String certificateId = (String) row[2];
                String certificateName = (String) row[3];

                Candidate candidate = candidateMap.computeIfAbsent(candidateId, id -> {
                    Candidate c = new Candidate();
                    c.setId(id);
                    c.setFullName(candidateName);
                    return c;
                });

                if (certificateId != null) {
                    Certificate certificate = new Certificate();
                    certificate.setId(certificateId);
                    certificate.setName(certificateName);

                    certificateMap.computeIfAbsent(candidateId, k -> new HashSet<>()).add(certificate);
                }
            }

            // Chuyển kết quả từ map tạm thời sang map kết quả
            for (Map.Entry<String, Candidate> entry : candidateMap.entrySet()) {
                map.put(entry.getValue(), certificateMap.getOrDefault(entry.getKey(), new HashSet<>()));
            }
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
