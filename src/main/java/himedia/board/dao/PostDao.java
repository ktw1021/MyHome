package himedia.board.dao;

import java.util.List;
import himedia.board.model.PostVo;

public interface PostDao {
    List<PostVo> getPosts(int page, int pageSize);
    int getPostCount();
    boolean addPost(PostVo post);
    PostVo getPostById(int postId);
    void incrementViewCount(int postId);
}
