package ir.maktabsharif.repository.member.impl;

import ir.maktabsharif.exception.DatabaseRepoException;
import ir.maktabsharif.model.Member;
import ir.maktabsharif.repository.member.MemberRepo;
import ir.maktabsharif.util.JpaConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

import java.util.Optional;

public class MemberRepoImpl implements MemberRepo {
    @Override
    public void insert(Member member) {
        EntityTransaction tx = null;
        try (EntityManager em = JpaConfig.getEMF().createEntityManager()) {
            tx = em.getTransaction();

            tx.begin();
            em.persist(member);
            tx.commit();
        }
        catch (PersistenceException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new DatabaseRepoException("Insert Member to Database Failed: " + e.getMessage());
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        try (EntityManager em = JpaConfig.getEMF().createEntityManager()) {
            Member member = em.find(Member.class, id);

            return Optional.ofNullable(member);
        }
        catch (PersistenceException e) {
            throw new DatabaseRepoException("Find Member From Database Failed: " + e.getMessage());
        }
    }

    @Override
    public boolean update(Member member) {
        EntityTransaction tx = null;
        try (EntityManager em = JpaConfig.getEMF().createEntityManager()) {
            Member existingMember = em.find(Member.class, member.getId());

            if (existingMember == null) return false;

            tx = em.getTransaction();

            tx.begin();
            existingMember.setFullName(member.getFullName());
            existingMember.setPhone(member.getPhone());
            existingMember.setEmail(member.getEmail());
            existingMember.setYearOfBirth(member.getYearOfBirth());
            tx.commit();
            return true;
        }
        catch (PersistenceException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new DatabaseRepoException("Update Member From Database Failed: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) {
        EntityTransaction tx = null;
        try (EntityManager em = JpaConfig.getEMF().createEntityManager()) {
            Member existingMember = em.find(Member.class, id);

            if (existingMember == null) return false;

            tx = em.getTransaction();

            tx.begin();
            em.remove(existingMember);
            tx.commit();
            return true;
        }
        catch (PersistenceException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new DatabaseRepoException("Delete Member From Database Failed: " + e.getMessage());
        }
    }
}
