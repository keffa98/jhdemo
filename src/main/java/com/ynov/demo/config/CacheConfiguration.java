package com.ynov.demo.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.ynov.demo.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.ynov.demo.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.ynov.demo.domain.User.class.getName());
            createCache(cm, com.ynov.demo.domain.Authority.class.getName());
            createCache(cm, com.ynov.demo.domain.User.class.getName() + ".authorities");
            createCache(cm, com.ynov.demo.domain.Livre.class.getName());
            createCache(cm, com.ynov.demo.domain.Livre.class.getName() + ".exemplaires");
            createCache(cm, com.ynov.demo.domain.Livre.class.getName() + ".themes");
            createCache(cm, com.ynov.demo.domain.Livre.class.getName() + ".auteurs");
            createCache(cm, com.ynov.demo.domain.UserParame.class.getName());
            createCache(cm, com.ynov.demo.domain.Auteur.class.getName());
            createCache(cm, com.ynov.demo.domain.Auteur.class.getName() + ".livres");
            createCache(cm, com.ynov.demo.domain.Emplacement.class.getName());
            createCache(cm, com.ynov.demo.domain.Theme.class.getName());
            createCache(cm, com.ynov.demo.domain.Theme.class.getName() + ".livres");
            createCache(cm, com.ynov.demo.domain.Exemplaire.class.getName());
            createCache(cm, com.ynov.demo.domain.Emprunt.class.getName());
            createCache(cm, com.ynov.demo.domain.Region.class.getName());
            createCache(cm, com.ynov.demo.domain.Country.class.getName());
            createCache(cm, com.ynov.demo.domain.Location.class.getName());
            createCache(cm, com.ynov.demo.domain.Department.class.getName());
            createCache(cm, com.ynov.demo.domain.Department.class.getName() + ".employees");
            createCache(cm, com.ynov.demo.domain.Task.class.getName());
            createCache(cm, com.ynov.demo.domain.Task.class.getName() + ".jobs");
            createCache(cm, com.ynov.demo.domain.Employee.class.getName());
            createCache(cm, com.ynov.demo.domain.Employee.class.getName() + ".jobs");
            createCache(cm, com.ynov.demo.domain.Job.class.getName());
            createCache(cm, com.ynov.demo.domain.Job.class.getName() + ".tasks");
            createCache(cm, com.ynov.demo.domain.JobHistory.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
