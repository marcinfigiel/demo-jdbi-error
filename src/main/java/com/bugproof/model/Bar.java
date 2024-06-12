package com.bugproof.model;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Bar(Long id, String name, Long fooId) {}
