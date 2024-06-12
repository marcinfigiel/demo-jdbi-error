package com.bugproof.service;

import com.bugproof.dao.BarDao;
import com.bugproof.dao.FooDao;
import com.bugproof.model.Bar;
import com.bugproof.model.Foo;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@Singleton
public class DatabaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseService.class);

    private final Jdbi jdbi;


    public DatabaseService(@Named("hikari") final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Transactional
    public List<Foo> getFoos() {
        return jdbi.withExtension(FooDao.class, FooDao::fetchFoos);
    }

    @Transactional
    public Foo getFooById(final long id) {
        LOGGER.info("Fetching foo: {}", id);
        return jdbi.withExtension(FooDao.class, dao -> dao.fetchFoo(id));
    }

    @Transactional
    public void saveFoo(final Foo foo) {
        LOGGER.info("Saving foo: {}", foo.id());
        jdbi.useExtension(FooDao.class, dao -> dao.storeFoo(foo));
    }

    @Transactional
    public List<Bar> getBars() {
        return jdbi.withExtension(BarDao.class, BarDao::fetchBars);
    }

    @Transactional
    public Long saveBar(final Bar bar) {
        LOGGER.info("Saving bar: {}", bar.name());
        return jdbi.withExtension(BarDao.class, dao -> dao.storeBar(bar));
    }

}
