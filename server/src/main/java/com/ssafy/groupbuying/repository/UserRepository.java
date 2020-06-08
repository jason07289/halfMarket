package com.ssafy.groupbuying.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.groupbuying.vo.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	public User findById(long id);
	@Transactional
	@Modifying
    @Query(value="insert into user (email,password,nickname,name) values (:email,:password,:nickname,:name)", nativeQuery = true)
	int signUp (@Param("email")String email,@Param("password")String password, @Param("nickname")String nickname, @Param("name")String name);
	
	@Query(value = "select EXISTS(select * from user u where  u.email = :uEmail)", nativeQuery = true)
	int  existsByMail(@Param("uEmail") String uEmail);
	
	@Query(value = "select * from user u where  u.email = :uEmail", nativeQuery = true)
	User  findByMail(@Param("uEmail") String uEmail);
	
	@Query(value = "delete from user u where  u.email = :uEmail", nativeQuery = true)
	User  deleteByMail(@Param("uEmail") String uEmail);
	
	
	@Query(value = "select * from user order by reputation desc limit 3", nativeQuery = true)
	List<User>  findTopUser();
	
	@Query(value = "select EXISTS(select * from user u where  u.email = :uEmail and u.password = :uPass)", nativeQuery = true)
	int  checkPass(@Param("uEmail") String uEmail, @Param("uPass") String uPass);
	
	@Query(value = "select count(*) from user u , board b where :uId = b.user_id", nativeQuery = true)
	int  userBoardCount(@Param("uId") long uId);
}
