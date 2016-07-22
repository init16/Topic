package com.sang.topic.service;

import com.sang.topic.mapper.PostMapper;
import com.sang.topic.mapper.UserMapper;
import com.sang.topic.model.Post;
import com.sang.topic.util.MyBatisSession;
import com.sang.topic.model.support.Page;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    public List<Post> getByPage(Page page){
        try(SqlSession session = MyBatisSession.getSession()) {
            int rowNumber = session.getMapper(PostMapper.class).selectCount();
            page.setRowNumber(rowNumber);
            return session.selectList("selectPostByPage", null, page.toRowBounds());
        }
    }

    public List<Post> getByTopicAndPage(Integer topicId, Page page) {
        try(SqlSession session = MyBatisSession.getSession()) {
            int rowNumber = session.getMapper(PostMapper.class).selectCountByTopic(topicId);
            page.setRowNumber(rowNumber);
            return session.selectList("selectPostByTopicAndPage", topicId, page.toRowBounds());
        }
    }

    public Post get(Integer id) {
        return postMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public int insert (Post post) {
        post.setUserUsername(userMapper.selectByPrimaryKey(post.getUserId()).getUsername());
        post.setCreateTime(new Date());
        return postMapper.insertSelective(post);
    }

    @Transactional
    public int update(Post post) {
        return postMapper.updateByPrimaryKeySelective(post);
    }
}
