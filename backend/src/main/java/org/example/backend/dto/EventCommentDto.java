package org.example.backend.dto;

import org.example.backend.domain.EventComment;
import org.example.backend.domain.ReplyComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventCommentDto {
    private long id;
    private String comment;
    private long userId;
    private String userName;
    private long eventId;
    private List<Long> replyCommentIds;

    public EventCommentDto(EventComment eventComment) {
        this.id = eventComment.getId();
        this.comment = eventComment.getComment();
        this.userId = eventComment.getUser().getId();
        this.eventId = eventComment.getEvent().getId();
        this.userName = eventComment.getUser().getName();
        this.replyCommentIds = eventComment.getReplyComments().stream().map(ReplyComment::getId).toList();
    }
}
