package top.gjp0609.webtools.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.gjp0609.webtools.entity.User;

import java.io.Serializable;
import java.util.List;

public interface UserRepository extends
        JpaRepository<User, Long>,
        JpaSpecificationExecutor<User>,
        Serializable {

    @Override
    @Cacheable(value = "user.find.all")
    List<User> findAll();

}
