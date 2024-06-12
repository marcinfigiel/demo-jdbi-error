package com.bugproof.dao;

import com.bugproof.model.Foo;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(Foo.class)
public interface FooDao {

    @SqlQuery("SELECT * FROM foo")
    List<Foo> fetchFoos();

    @SqlQuery("SELECT * FROM foo WHERE id = :id")
    Foo fetchFoo(long id);

    @SqlUpdate("INSERT INTO foo (name) VALUES (:name)")
    void storeFoo(@BindMethods Foo foo);

}
