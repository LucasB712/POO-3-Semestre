package br.edu.idp.tech.poo.dao;


import br.edu.idp.tech.poo.entidade.Carro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;


public class CarroJpaDao {

    private EntityManager entityManager;

    public CarroJpaDao() {
        // criação do gerente de persistência com a sua própria conexão
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bd_carros");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void salvar(Carro carro) {
        // salvar carro criado
        entityManager.getTransaction().begin();
        entityManager.persist(carro);
        entityManager.getTransaction().commit();
    }

    public void salvar(List<Carro> novosCarros) {
        for (Carro carro : novosCarros) {
            this.salvar(carro);
        }
    }

    public List<Carro> findAll() {
        List<Carro> carrosNoJpa = entityManager
                .createQuery("from Carro")
                .getResultList();
        return carrosNoJpa;
    }

}
