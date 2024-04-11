package org.example.backend.domain;

import jakarta.persistence.Entity;
import org.checkerframework.checker.signature.qual.Identifier;

public class Permission {
    private boolean canCreate;
    private boolean canEnroll;
    private boolean canComment;
}
