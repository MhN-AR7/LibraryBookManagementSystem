package ir.maktabsharif.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaConfig {
    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("postgres-pu");

    private JpaConfig() {
    }

    public static EntityManagerFactory getEMF() {
        return EMF;
    }

    public static void closeEMF() {
        EMF.close();
    }
}
