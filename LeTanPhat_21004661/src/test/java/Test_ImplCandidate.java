import dao.impl.Impl_Candidate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_ImplCandidate {
    private Impl_Candidate impl_candidate;

    @BeforeAll
    public void init() {
        impl_candidate = new Impl_Candidate();
    }

    @Test
    public void test_listCadidatesByCompanies() {
        System.out.println(impl_candidate.listCadidatesByCompanies());
    }

    @Test
    public void test_listCandidatesWithLongestWorking() {
        System.out.println(impl_candidate.listCandidatesWithLongestWorking());
    }
}
