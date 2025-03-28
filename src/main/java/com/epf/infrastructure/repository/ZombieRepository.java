package com.epf.infrastructure.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epf.core.model.Zombie;
import com.epf.infrastructure.dao.ZombieDAO;
import com.epf.infrastructure.mapper.ZombieEntityMapper;

@Repository
public class ZombieRepository {

    private final ZombieDAO zombieDAO;
    private final ZombieEntityMapper mapper;

    @Autowired
    public ZombieRepository(ZombieDAO zombieDAO, ZombieEntityMapper mapper) {
        this.zombieDAO = zombieDAO;
        this.mapper = mapper;
    }

    public List<Zombie> findAll() {
        return mapper.mapListEntityToListModel(zombieDAO.findAll());
    }

    public Zombie findById(Integer id) {
        return mapper.mapEntityToModel(zombieDAO.findById(id));
    }

    public Zombie save(Zombie zombie) {
        try {
            Zombie savedZombie = zombieDAO.save(zombie);
            return savedZombie;
        } catch (Exception e) {
            throw e;
        }
    }

    public void delete(Integer id) {
        try {
            zombieDAO.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
