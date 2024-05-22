/*
 * @ (#) Test_ImplExoerience.java     1.0   5/22/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

import dao.impl.Impl_Experience;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 5/22/2024
 * @version:  1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_ImplExperience {
    private Impl_Experience impl_experience;

    @BeforeAll
    public void init() {
        impl_experience = new Impl_Experience();
    }

    @Test
    public void test_listYearsOfExperrienceByPosition() {
        assertNotNull(impl_experience.listYearsOfExperrienceByPosition("C100"));
    }
}
