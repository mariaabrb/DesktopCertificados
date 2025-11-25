package org.example.certifyproadmin.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtils {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("exemploPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}