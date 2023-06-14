package com.pms.orders.service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.pms.orders.model.Sequence;

@Service
public class SequenceGeneratorService {

	@Autowired
    private MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        Query query = new Query(Criteria.where("id").is(seqName));
        Update update = new Update().inc("seq", 1);
        FindAndModifyOptions options = options().returnNew(true).upsert(true);
        Sequence sequence = mongoOperations.findAndModify(query, update, options, Sequence.class);
        return !Objects.isNull(sequence)?sequence.getSeq():1;
    }
}
