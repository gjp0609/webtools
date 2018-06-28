package top.gjp0609.webtools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.gjp0609.webtools.entity.Dept;
import top.gjp0609.webtools.entity.User;

import java.io.Serializable;
import java.util.List;

public interface DeptRepo extends
        JpaRepository<Dept, Long>,
        JpaSpecificationExecutor<Dept>,
        Serializable {

}
