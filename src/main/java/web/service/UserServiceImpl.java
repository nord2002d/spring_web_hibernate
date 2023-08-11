package web.service;

import org.springframework.stereotype.Service;
import web.exeptions.UserNotFoundException;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private EntityManagerFactory entityManagerFactory;

    public UserServiceImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void add(User user) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            if (user.getId() == null) {
                entityManager.persist(user);
            } else {
                entityManager.merge(user);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<User> listUsers() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<User> query = entityManager.createQuery("from User", User.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return List.of();
    }

    @Override
    public User getUser(long id) throws UserNotFoundException {
        EntityManager entityManager = null;
        Optional<User> user;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            user = Optional.ofNullable(entityManager.find(User.class, id));
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return user.orElseThrow(() -> new UserNotFoundException("Пользователь с таким id = " + id + ", не найден"));
    }

    @Override
    public void removeUser(long id) throws UserNotFoundException {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Optional<User> user = Optional.ofNullable(entityManager.find(User.class, id));
            entityManager.remove(user.orElseThrow(() -> new UserNotFoundException(
                    "В процессе удаления возникла ошибка: пользователь с таким id = " + id + ", не найден")));
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }


    }


}
