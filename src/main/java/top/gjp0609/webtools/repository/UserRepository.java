package top.gjp0609.webtools.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import top.gjp0609.webtools.common.validation.Groups;
import top.gjp0609.webtools.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public interface UserRepository extends
        JpaRepository<User, Long>,
        JpaSpecificationExecutor<User>,
        Serializable {

    @Override
//    @Cacheable(value = "user.find.all")
    List<User> findAll();

    @Transactional
    @Modifying
    @Query("update User set name = ?1 where id = ?2")
    int modifyById(String name, Long id);

    List<User> findAllByNameLike(@NotBlank(groups = {Groups.Add.class}) @Size(min = 1, max = 30, groups = {Groups.Add.class, Groups.Edit.class}) String name);

    List<User> findAllByNameLike(@NotBlank(groups = {Groups.Add.class}) @Size(min = 1, max = 30, groups = {Groups.Add.class, Groups.Edit.class}) String name, Pageable pageable);

}
