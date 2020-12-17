package com.ynov.demo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ynov.demo.web.rest.TestUtil;

public class UserParameTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserParame.class);
        UserParame userParame1 = new UserParame();
        userParame1.setId(1L);
        UserParame userParame2 = new UserParame();
        userParame2.setId(userParame1.getId());
        assertThat(userParame1).isEqualTo(userParame2);
        userParame2.setId(2L);
        assertThat(userParame1).isNotEqualTo(userParame2);
        userParame1.setId(null);
        assertThat(userParame1).isNotEqualTo(userParame2);
    }
}
