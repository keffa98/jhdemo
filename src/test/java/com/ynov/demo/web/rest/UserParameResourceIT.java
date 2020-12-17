package com.ynov.demo.web.rest;

import com.ynov.demo.JhdemoApp;
import com.ynov.demo.domain.UserParame;
import com.ynov.demo.repository.UserParameRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserParameResource} REST controller.
 */
@SpringBootTest(classes = JhdemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserParameResourceIT {

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ISBN = "AAAAAAAAAA";
    private static final String UPDATED_ISBN = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private UserParameRepository userParameRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserParameMockMvc;

    private UserParame userParame;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserParame createEntity(EntityManager em) {
        UserParame userParame = new UserParame()
            .titre(DEFAULT_TITRE)
            .description(DEFAULT_DESCRIPTION)
            .isbn(DEFAULT_ISBN)
            .code(DEFAULT_CODE);
        return userParame;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserParame createUpdatedEntity(EntityManager em) {
        UserParame userParame = new UserParame()
            .titre(UPDATED_TITRE)
            .description(UPDATED_DESCRIPTION)
            .isbn(UPDATED_ISBN)
            .code(UPDATED_CODE);
        return userParame;
    }

    @BeforeEach
    public void initTest() {
        userParame = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserParame() throws Exception {
        int databaseSizeBeforeCreate = userParameRepository.findAll().size();
        // Create the UserParame
        restUserParameMockMvc.perform(post("/api/user-parames")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userParame)))
            .andExpect(status().isCreated());

        // Validate the UserParame in the database
        List<UserParame> userParameList = userParameRepository.findAll();
        assertThat(userParameList).hasSize(databaseSizeBeforeCreate + 1);
        UserParame testUserParame = userParameList.get(userParameList.size() - 1);
        assertThat(testUserParame.getTitre()).isEqualTo(DEFAULT_TITRE);
        assertThat(testUserParame.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testUserParame.getIsbn()).isEqualTo(DEFAULT_ISBN);
        assertThat(testUserParame.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createUserParameWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userParameRepository.findAll().size();

        // Create the UserParame with an existing ID
        userParame.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserParameMockMvc.perform(post("/api/user-parames")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userParame)))
            .andExpect(status().isBadRequest());

        // Validate the UserParame in the database
        List<UserParame> userParameList = userParameRepository.findAll();
        assertThat(userParameList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTitreIsRequired() throws Exception {
        int databaseSizeBeforeTest = userParameRepository.findAll().size();
        // set the field null
        userParame.setTitre(null);

        // Create the UserParame, which fails.


        restUserParameMockMvc.perform(post("/api/user-parames")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userParame)))
            .andExpect(status().isBadRequest());

        List<UserParame> userParameList = userParameRepository.findAll();
        assertThat(userParameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = userParameRepository.findAll().size();
        // set the field null
        userParame.setDescription(null);

        // Create the UserParame, which fails.


        restUserParameMockMvc.perform(post("/api/user-parames")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userParame)))
            .andExpect(status().isBadRequest());

        List<UserParame> userParameList = userParameRepository.findAll();
        assertThat(userParameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsbnIsRequired() throws Exception {
        int databaseSizeBeforeTest = userParameRepository.findAll().size();
        // set the field null
        userParame.setIsbn(null);

        // Create the UserParame, which fails.


        restUserParameMockMvc.perform(post("/api/user-parames")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userParame)))
            .andExpect(status().isBadRequest());

        List<UserParame> userParameList = userParameRepository.findAll();
        assertThat(userParameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = userParameRepository.findAll().size();
        // set the field null
        userParame.setCode(null);

        // Create the UserParame, which fails.


        restUserParameMockMvc.perform(post("/api/user-parames")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userParame)))
            .andExpect(status().isBadRequest());

        List<UserParame> userParameList = userParameRepository.findAll();
        assertThat(userParameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserParames() throws Exception {
        // Initialize the database
        userParameRepository.saveAndFlush(userParame);

        // Get all the userParameList
        restUserParameMockMvc.perform(get("/api/user-parames?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userParame.getId().intValue())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].isbn").value(hasItem(DEFAULT_ISBN)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getUserParame() throws Exception {
        // Initialize the database
        userParameRepository.saveAndFlush(userParame);

        // Get the userParame
        restUserParameMockMvc.perform(get("/api/user-parames/{id}", userParame.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userParame.getId().intValue()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.isbn").value(DEFAULT_ISBN))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingUserParame() throws Exception {
        // Get the userParame
        restUserParameMockMvc.perform(get("/api/user-parames/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserParame() throws Exception {
        // Initialize the database
        userParameRepository.saveAndFlush(userParame);

        int databaseSizeBeforeUpdate = userParameRepository.findAll().size();

        // Update the userParame
        UserParame updatedUserParame = userParameRepository.findById(userParame.getId()).get();
        // Disconnect from session so that the updates on updatedUserParame are not directly saved in db
        em.detach(updatedUserParame);
        updatedUserParame
            .titre(UPDATED_TITRE)
            .description(UPDATED_DESCRIPTION)
            .isbn(UPDATED_ISBN)
            .code(UPDATED_CODE);

        restUserParameMockMvc.perform(put("/api/user-parames")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserParame)))
            .andExpect(status().isOk());

        // Validate the UserParame in the database
        List<UserParame> userParameList = userParameRepository.findAll();
        assertThat(userParameList).hasSize(databaseSizeBeforeUpdate);
        UserParame testUserParame = userParameList.get(userParameList.size() - 1);
        assertThat(testUserParame.getTitre()).isEqualTo(UPDATED_TITRE);
        assertThat(testUserParame.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testUserParame.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testUserParame.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserParame() throws Exception {
        int databaseSizeBeforeUpdate = userParameRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserParameMockMvc.perform(put("/api/user-parames")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userParame)))
            .andExpect(status().isBadRequest());

        // Validate the UserParame in the database
        List<UserParame> userParameList = userParameRepository.findAll();
        assertThat(userParameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserParame() throws Exception {
        // Initialize the database
        userParameRepository.saveAndFlush(userParame);

        int databaseSizeBeforeDelete = userParameRepository.findAll().size();

        // Delete the userParame
        restUserParameMockMvc.perform(delete("/api/user-parames/{id}", userParame.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserParame> userParameList = userParameRepository.findAll();
        assertThat(userParameList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
