package dao;


import entity.Candidate;

import java.util.Map;

public interface Dao_Candidate {
    public Map<Candidate, Long> listCadidatesByCompanies();
    public Map<Candidate, Long> listCandidatesWithLongestWorking();
}
