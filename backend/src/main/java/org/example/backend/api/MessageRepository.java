package org.example.backend.api;

import org.example.backend.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessageByType(String type);

    List<Message> findMessageByFromId(long id);

    List<Message> findMessageByToId(long id);

    List<Message> findMessageByFromIdAndToId(long fromId, long toId);

    List<Message> findMessageByFromIdAndType(long fromId, String type);

    List<Message> findMessageByToIdAndType(long toId, String type);

    List<Message> findMessageByFromIdAndToIdAndType(long fromId, long toId, String type);

    List<Message> findMessageByToIdAndRead(long toId, boolean read);

    List<Message> findMessageByToIdAndTypeAndRead(long toId, String type, boolean read);
}
