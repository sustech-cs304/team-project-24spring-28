package org.example.backend.domain;

import jakarta.persistence.Entity;
import org.checkerframework.checker.signature.qual.Identifier;

public class Permission {
    private boolean canCreate;
    private boolean canEnroll;
    private boolean canComment;

    public boolean isCanCreate() {
        return canCreate;
    }

    public void setCanCreate(boolean canCreate) {
        this.canCreate = canCreate;
    }

    public boolean isCanEnroll() {
        return canEnroll;
    }

    public void setCanEnroll(boolean canEnroll) {
        this.canEnroll = canEnroll;
    }

    public boolean isCanComment() {
        return canComment;
    }

    public void setCanComment(boolean canComment) {
        this.canComment = canComment;
    }
}
