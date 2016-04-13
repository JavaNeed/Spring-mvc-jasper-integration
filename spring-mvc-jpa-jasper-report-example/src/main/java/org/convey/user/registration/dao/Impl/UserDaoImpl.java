package org.convey.user.registration.dao.Impl;

import org.convey.user.registration.dao.UserDao;
import org.convey.user.registration.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;


    public User registerNewUser(User user) {
        User userMerged = entityManager.merge(user);
        entityManager.flush();
        return userMerged;
    }

    public User updateUserDetails(User user) {
        User userMerged = entityManager.merge(user);
        entityManager.flush();
        return userMerged;
    }


    public User getUserDetailsByEmail(User user) {
        Query query = entityManager.createQuery("from User where email = :email");
        query.setParameter("email",user.getEmail());

        User retrievedUser = (User) query.getSingleResult();
        return retrievedUser;
    }

    public void removeAllRegisteredUsers() {
        Query query=entityManager.createQuery("delete from User");
        query.executeUpdate();
    }

    public List<User> retrieveAllRegisteredUsers() {
        Query query=entityManager.createQuery("from User");
        List<User> allUsers = query.getResultList();
        return allUsers;
    }

    public void removeUser(User user) {
        entityManager.remove(user);
        entityManager.flush();
    }
}


