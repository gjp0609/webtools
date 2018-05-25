//package top.gjp0609.webtools.repository.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Repository;
//import top.gjp0609.webtools.entity.User;
//import top.gjp0609.webtools.repository.UserRepository;
//
//import java.util.List;
//
//@Repository
//public interface UserRepositoryImpl extends UserRepository {
//
//    @Cacheable(cacheNames = "user.find.all")
//    @Override
//    public abstract List<User> findAll();
//
//    @Override
//    @Cacheable(cacheNames = "user.find.one")
//    public abstract User getOne(Long aLong);
//}
