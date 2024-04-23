import dao.impl.Impl_Position;
import entity.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_ImplPosition {
    private Impl_Position impl_position;

    @BeforeAll
    public void init() {
        impl_position = new Impl_Position();
    }

    @Test
    public void testListPositions() {
        List<Position> positions = impl_position.listPositions("Analyst", 10000, 15000);
        positions.forEach(position -> {
            System.out.println(position);
        });
    }
}
