package com.bugproof.dao;

import com.bugproof.model.Bar;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(Bar.class)
public interface BarDao {

    @SqlQuery("SELECT * FROM bar")
    List<Bar> fetchBars();

    @SqlQuery("SELECT * FROM bar WHERE id = :id")
    Bar fetchBar(long id);

    @GetGeneratedKeys("id")
    @SqlUpdate("INSERT INTO bar (name, foo_id) VALUES (:name, :fooId)")
    Long storeBar(@BindMethods Bar bar);

}
