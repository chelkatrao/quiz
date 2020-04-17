package com.company.quiz.service;

import com.company.quiz.model.auth.User;
import com.company.quiz.model.quiz.Answer;
import com.company.quiz.model.quiz.Question;
import com.company.quiz.repository.auth.CompanyRepository;
import com.company.quiz.repository.auth.RoleRepository;
import com.company.quiz.repository.quiz.AnswerRepository;
import com.company.quiz.repository.quiz.QuestionRepository;
import com.company.quiz.service.quiz.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final String SLQ = " select s.question_id , s.answer_id , count(ss.answer) from\n" +
            "  (select a.question_id, q.value as question_value , a.id as answer_id , a.value as answer_value \n" +
            "  from question q join answer a on q.id = a.question_id)\n" +
            "   s left join scores ss on ss.question = s.question_id and ss.answer = s.answer_id where s.question_id = :questionId group by s.question_id,s.answer_id order by question_id";

    private final String SQL2 = "select u.company , s.answer , count(s.answer) from scores s join users u on s.users = u.id where s.answer =:answer group by u.company , s.answer";

    private EntityManager entityManager;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private CompanyRepository companyRepository;
    private RoleRepository roleRepository;
    private UserSession userSession;

    public ReportService(EntityManagerFactory scoreRepository,
                         QuestionRepository questionRepository,
                         AnswerRepository answerRepository,
                         CompanyRepository companyRepository,
                         UserSession userSession,
                         RoleRepository roleRepository) {
        this.entityManager = scoreRepository.createEntityManager();
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.companyRepository = companyRepository;
        this.userSession = userSession;
        this.roleRepository = roleRepository;
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
            map.put("answerId", answerid);
            map.put("value", (Float.valueOf(count) / fullCount) * 100);
            map.put("name", answerRepository.findById(answerid).get().getValue());
            l.add(map);
        });
        resultMap.put("questionid", question.getId());
        resultMap.put("questionName", question.getValue());
        resultMap.put("answers", l);
        return resultMap;
    }

    @Cacheable(key = "#root.method")
    public List<HashMap> companyByAnswer(Long answerId) {
        User user = userSession.getUser();
        boolean isAdmin = false;
        if (user.getRoles().stream().map(m -> m.getId()).collect(Collectors.toSet()).
                contains(roleRepository.findByRoleName("SUPER_ADMIN_ROLE").getId())) {
            isAdmin = true;
        }

        Query query = entityManager.createNativeQuery(SQL2);
        query.setParameter("answer", answerId);
        List<Object[]> list = query.getResultList();

        if (true) {
            return list.stream().map(x -> {
                HashMap map = new HashMap();
                map.put("company", companyRepository.findById(((BigInteger) x[0]).longValue()));
                map.put("count", ((BigInteger) x[2]).longValue());
                return map;
            }).collect(Collectors.toList());
        } else {
            List<Object[]> filtered = list.stream().filter(x -> companyRepository.findById(((BigInteger) x[0]).longValue()).equals(user.getCompany())).collect(Collectors.toList());
            return filtered.stream().map(x -> {
                HashMap map = new HashMap();
                map.put("company", companyRepository.findById(((BigInteger) x[0]).longValue()));
                map.put("count", ((BigInteger) x[2]).longValue());
                return map;
            }).collect(Collectors.toList());
        }

    }
}
