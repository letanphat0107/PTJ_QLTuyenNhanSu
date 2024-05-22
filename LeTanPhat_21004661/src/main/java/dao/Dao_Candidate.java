package dao;


import entity.Candidate;
import entity.Certificate;

import java.util.Map;
import java.util.Set;

public interface Dao_Candidate {
    public Map<Candidate, Long> listCadidatesByCompanies();
    public Map<Candidate, Long> listCandidatesWithLongestWorking();
    public boolean addCandidate(Candidate candidate);
    public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates();
}
