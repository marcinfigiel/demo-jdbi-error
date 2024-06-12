package com.bugproof.service;

import com.bugproof.model.Bar;
import com.bugproof.model.Foo;
import jakarta.inject.Singleton;

@Singleton
public class BusinessService {

    private final DatabaseService databaseService;


    public BusinessService(final DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Long saveBar(final Bar bar) {
        if (bar.fooId() != null) {
            createFooIfNecessary(bar.fooId());
        }
        return databaseService.saveBar(bar);
    }

    void createFooIfNecessary(final long fooId) {
        if (databaseService.getFooById(fooId) == null) {
            databaseService.saveFoo(new Foo(fooId, "foo_" + fooId));
        }
    }

}
