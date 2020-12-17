package com.ynov.demo.web.rest;

import com.ynov.demo.domain.UserParame;
import com.ynov.demo.repository.UserParameRepository;
import com.ynov.demo.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ynov.demo.domain.UserParame}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UserParameResource {

    private final Logger log = LoggerFactory.getLogger(UserParameResource.class);

    private static final String ENTITY_NAME = "userParame";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserParameRepository userParameRepository;

    public UserParameResource(UserParameRepository userParameRepository) {
        this.userParameRepository = userParameRepository;
    }

    /**
     * {@code POST  /user-parames} : Create a new userParame.
     *
     * @param userParame the userParame to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userParame, or with status {@code 400 (Bad Request)} if the userParame has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-parames")
    public ResponseEntity<UserParame> createUserParame(@Valid @RequestBody UserParame userParame) throws URISyntaxException {
        log.debug("REST request to save UserParame : {}", userParame);
        if (userParame.getId() != null) {
            throw new BadRequestAlertException("A new userParame cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserParame result = userParameRepository.save(userParame);
        return ResponseEntity.created(new URI("/api/user-parames/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-parames} : Updates an existing userParame.
     *
     * @param userParame the userParame to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userParame,
     * or with status {@code 400 (Bad Request)} if the userParame is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userParame couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-parames")
    public ResponseEntity<UserParame> updateUserParame(@Valid @RequestBody UserParame userParame) throws URISyntaxException {
        log.debug("REST request to update UserParame : {}", userParame);
        if (userParame.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserParame result = userParameRepository.save(userParame);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userParame.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-parames} : get all the userParames.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userParames in body.
     */
    @GetMapping("/user-parames")
    public List<UserParame> getAllUserParames() {
        log.debug("REST request to get all UserParames");
        return userParameRepository.findAll();
    }

    /**
     * {@code GET  /user-parames/:id} : get the "id" userParame.
     *
     * @param id the id of the userParame to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userParame, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-parames/{id}")
    public ResponseEntity<UserParame> getUserParame(@PathVariable Long id) {
        log.debug("REST request to get UserParame : {}", id);
        Optional<UserParame> userParame = userParameRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userParame);
    }

    /**
     * {@code DELETE  /user-parames/:id} : delete the "id" userParame.
     *
     * @param id the id of the userParame to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-parames/{id}")
    public ResponseEntity<Void> deleteUserParame(@PathVariable Long id) {
        log.debug("REST request to delete UserParame : {}", id);
        userParameRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
