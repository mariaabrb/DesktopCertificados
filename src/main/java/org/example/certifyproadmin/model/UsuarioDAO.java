package org.example.certifyproadmin.model;

import org.example.certifyproadmin.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UsuarioDAO {
    private EntityManager entityManager;

    public UsuarioDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void salvar(Usuario u) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(u);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}