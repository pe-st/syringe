package ch.schlau.pesche.syringe.junit5;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.enterprise.context.Dependent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Dependent
@ExtendWith(SyringeInitCdiSe.class)
class SyringeInitCdiSeTest {

    @Test
    void dummy() {
        assertThat("what can I test here?".length(), is(21));
    }
}