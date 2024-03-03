package com.minseojo.neatif.repository;

import com.minseojo.neatif.domain.item.ImageFile;
import com.minseojo.neatif.domain.item.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Slf4j
public class FileRepository {

    @PersistenceContext
    EntityManager em;

    public ImageFile findById(Long id) {
        return em.find(ImageFile.class, id);
    }

    public List<ImageFile> findByIdWithItem(Long itemId) {
        return em.createQuery("SELECT i FROM ImageFile i JOIN i.item item WHERE item.id = :itemId", ImageFile.class)
                .setParameter("itemId", itemId)
                .getResultList();
    }
}
