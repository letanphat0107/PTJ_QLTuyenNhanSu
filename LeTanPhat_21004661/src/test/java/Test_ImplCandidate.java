import dao.impl.Impl_Candidate;
import entity.Candidate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_ImplCandidate {
    private Impl_Candidate impl_candidate;

    @BeforeAll
    public void init() {
        impl_candidate = new Impl_Candidate();
    }

    @Test
    public void test_listCadidatesByCompanies() {
        assertNotNull(impl_candidate.listCadidatesByCompanies());
    }

    @Test
    public void test_listCandidatesWithLongestWorking() {
        assertNotNull(impl_candidate.listCandidatesWithLongestWorking());
    }

    @Test
    public void test_addCandidate() {
        Candidate candidate = new Candidate();
        candidate.setId("C100");
        candidate.setFullName("Nguyen Van A");
        candidate.setYearOfBirth(1990);
        candidate.setGender("Male");
        candidate.setEmail("nguyenvana@gmail.com");
        candidate.setPhone("0987654321");
        candidate.setDescription("Good");

        assertTrue(impl_candidate.addCandidate(candidate));
    }

    @Test
    void testAddDuplicateCandidate() {
        Candidate candidate = new Candidate();
        candidate.setId("C100");
        candidate.setFullName("Nguyen Van A");
        candidate.setYearOfBirth(1990);
        candidate.setGender("Male");
        candidate.setEmail("nguyenvana@gmail.com");
        candidate.setPhone("0987654321");
        candidate.setDescription("Good");

        assertEquals(false, impl_candidate.addCandidate(candidate));
    }

    @Test
    public void test_listCadidatesAndCertificates() {
        System.out.println(impl_candidate.listCadidatesAndCertificates());
    }
}
