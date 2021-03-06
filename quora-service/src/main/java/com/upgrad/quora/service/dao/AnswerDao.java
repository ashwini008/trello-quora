package com.upgrad.quora.service.dao;
import com.upgrad.quora.service.entity.AnswerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

//Dao class for performing CRUD operations on answer table in the database.
@Repository
public class AnswerDao {

    //@PersistenceContext annotation is used to inject an EntityManager
    @PersistenceContext
    private EntityManager entityManager;

    //This method persists the AnswerEntity in the database
    public AnswerEntity createAnswer(AnswerEntity answerEntity) {
        entityManager.persist(answerEntity);
        return answerEntity;
    }
    // Method to fetch answerEntity from database as per their Id
    public AnswerEntity getAnswerById(final String answerId) {
        try {
            return entityManager.createNamedQuery("getAnswerByUuid", AnswerEntity.class).setParameter("uuid", answerId).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    //it will update the answer
    public void updateAnswer(AnswerEntity answerEntity) {
        entityManager.merge(answerEntity);
    }

    //This method deletes answers from database and accepts AnswerId as a parameter in String format
    public AnswerEntity deleteAnswer(final String answerId) {
        // fetch the answer to be deleted
        AnswerEntity deleteAnswer = getAnswerById(answerId);
        // Check if deleteAnswer is null or not
        if (deleteAnswer != null) {
            entityManager.remove(deleteAnswer);
        }
        return deleteAnswer;
    }
    // method used for getting all answer for a specific question from database and it returns a list of answers.
    public List<AnswerEntity> getAllAnswersToQuestion(final String questionId) {
        return entityManager.createNamedQuery("getAllAnswersToQuestion", AnswerEntity.class).setParameter("uuid", questionId).getResultList();
    }
}
