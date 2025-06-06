package com.example.bcsd.board.repository;

import com.example.bcsd.board.model.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Board save(Board board) {
        em.persist(board);

        return board;
    }

    public Board findById(Long id) {
        Board board = em.find(Board.class, id);

        return board;
    }

    public boolean boardExists(Long id) {
        return em.find(Board.class, id) != null;
    }

    public List<Board> findAll() {
        String jpql = "SELECT b FROM Board b";
        TypedQuery<Board> typedQuery = em.createQuery(jpql, Board.class);

        return typedQuery.getResultList();
    }

    @Transactional
    public Board updateSave(Board board) {
        return em.merge(board);
    }

    @Transactional
    public void delete(Long id) {
        Board board = em.find(Board.class, id);

        if (board != null) {
            em.remove(board);
        }
    }
}
