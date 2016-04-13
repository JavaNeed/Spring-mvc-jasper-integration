package org.convey.user.registration.dao.Impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.convey.user.registration.dao.UserRoleDao;
import org.convey.user.registration.model.UserRole;
import org.springframework.stereotype.Repository;


@Repository
public class UserRoleDaoImpl implements UserRoleDao{

    @PersistenceContext
    private EntityManager entityManager;


    public UserRole addNewUserRole(UserRole userRole) {
        entityManager.persist(userRole);
        return userRole;
    }

    public void removeAllUserRoles() {
        Query query = entityManager.createQuery("delete from UserRole");
        query.executeUpdate();
    }
}
