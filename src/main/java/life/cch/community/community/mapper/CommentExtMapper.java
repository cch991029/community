package life.cch.community.community.mapper;

import life.cch.community.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}