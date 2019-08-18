package com.example.consumer.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MessageRepositoryImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }

    @Override
    public void save(Message message) {
        mongoTemplate.save(message);
    }

    @Override
    public List<Message> find(String start_date, String end_date, String server, String level) {
        Query query = new Query();
        List<Criteria> criterias=new ArrayList<Criteria>();

            criterias.add(Criteria.where("datetime").gt(start_date));
            criterias.add(Criteria.where("datetime").lt(end_date));

        if(!server.equals("")){
            criterias.add(Criteria.where("server").is(server));
        }
        if(!level.equals("")){
            criterias.add(Criteria.where("level").is(level));
        }
        query.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));
        System.out.println(query.toString());
        return mongoTemplate.find(query,Message.class);
    }

    @Override
    public List<Message> findAll() {
        return mongoTemplate.findAll(Message.class);
    }


}
