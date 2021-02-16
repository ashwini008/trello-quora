package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.UserAuthEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserAuthDao {
    //@PersistenceContext annotation is used to inject an EntityManager
    @PersistenceContext
    EntityManager entityManager;
    // Method to get UserAuthEntity by accessToken from database
    public UserAuthEntity getUserAuthByToken(final String accessToken) {
        try {
            return entityManager.createNamedQuery("userAuthByAccessToken", UserAuthEntity.class).
                    setParameter("accessToken", accessToken).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}