package com.bugproof.config;

import io.micronaut.configuration.jdbi.JdbiCustomizer;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.jdbi.v3.core.Handles;
import org.jdbi.v3.core.Jdbi;


/**
 * Class which contains additional configuration for jdbi.
 */
@Named("hikari")
@Singleton
public class JdbiBeanConfigurer implements JdbiCustomizer {

    @Override
    public void customize(final Jdbi jdbi) {
        jdbi.getConfig(Handles.class).setForceEndTransactions(false);
    }

}
