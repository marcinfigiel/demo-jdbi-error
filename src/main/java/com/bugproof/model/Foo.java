package com.bugproof.model;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Foo(Long id, String name) {}
