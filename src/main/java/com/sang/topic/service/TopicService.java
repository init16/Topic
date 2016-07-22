package com.sang.topic.service;

import com.sang.topic.mapper.TopicMapper;
import com.sang.topic.model.Topic;
import com.sang.topic.util.MyBatisSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicMapper topicMapper;

    public List<Topic> getAll() {
        return topicMapper.selectAll();
    }

    public List<Topic> selectAllOpen() {
        return topicMapper.selectAllOpen();
    }

    public Topic get(Integer id) {
        return topicMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public int insert(Topic topic) {
        return topicMapper.insertSelective(topic);
    }

    @Transactional
    public int update(Topic topic) {
        return topicMapper.updateByPrimaryKeySelective(topic);
    }
}
