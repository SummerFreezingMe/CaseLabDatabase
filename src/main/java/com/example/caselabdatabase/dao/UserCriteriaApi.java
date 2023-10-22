package com.example.caselabdatabase.dao;

import com.example.caselabdatabase.config.HibernateSessionFactoryUtil;
import com.example.caselabdatabase.entity.User;
import com.example.caselabdatabase.entity.UserCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public class UserCriteriaApi  {


    public void create(User user) {

    }


    public void update(Long id, User user) {

    }


    public void delete(Long id) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(builder.equal(root.get("id"), id));
            session.createQuery(criteriaQuery).getSingleResult();
        }
    }


    public User findById(Long id) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(builder.equal(root.get("id"), id));
            return session.createQuery(criteriaQuery).getSingleResult();
        }
    }


    public List<User> findAll(UserCriteria criteria) {
        Map<String, Object> filters = criteria.getCriteria();
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            for (String key: filters.keySet()) {
                Predicate pr = builder.greaterThanOrEqualTo(root.get(key), filters.get(key).toString());
                criteriaQuery.where(pr);
            }
            Query<User> query = session.createQuery(criteriaQuery);
            return query.setFirstResult(criteria.getCurrentPage()).setMaxResults(10).getResultList();
        }
    }
}