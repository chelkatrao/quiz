package com.company.quiz.service;

import com.company.quiz.model.quiz.Answer;
import com.company.quiz.model.quiz.Question;
import com.company.quiz.repository.quiz.AnswerRepository;
import com.company.quiz.repository.quiz.QuestionRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.*;

@Service
public class ReportService {

    private final String SLQ = " select s.question_id , s.answer_id , count(ss.answer) from\n" +
            "  (select a.question_id, q.value as question_value , a.id as answer_id , a.value as answer_value \n" +
            "  from question q join answer a on q.id = a.question_id)\n" +
            "   s left join scores ss on ss.question = s.question_id and ss.answer = s.answer_id where s.question_id = :questionId group by s.question_id,s.answer_id order by question_id";

    private EntityManager entityManager;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    public ReportService(EntityManagerFactory scoreRepository,
                         QuestionRepository questionRepository,
                         AnswerRepository answerRepository) {
        this.entityManager = scoreRepository.createEntityManager();
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Cacheable(key = "#root.method")
    public Map reportPercentage(Long questionId) {
        Question question = questionRepository.findById(questionId).get();
        Map resultMap = new TreeMap();
        Query query = entityManager.createNativeQuery(SLQ);
        query.setParameter("questionId", question.getId());

        List<Object[]> list = query.getResultList();
        final Long[] counter = {0l};

        list.forEach(o -> {
            counter[0] += Long.parseLong(o[2].toString());
        });
        List l = new ArrayList();
        list.forEach(objects -> {
            Map map = new HashMap();
            Long answerid = Long.parseLong(objects[1].toString());
            Long count = Long.parseLong(objects[2].toString());

            Float fullCount = Float.valueOf(counter[0]);
            if (fullCount.equals(0f)) {
                fullCount = 1f;
            }

            map.put("value", (Float.valueOf(count) / fullCount) * 100);
            map.put("name", answerRepository.findById(answerid).get().getValue());
            l.add(map);
        });
        resultMap.put("questionid", question.getId());
        resultMap.put("questionName", question.getValue());
        resultMap.put("answers", l);
        return resultMap;
    }
}