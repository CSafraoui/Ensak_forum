package com.example.dao.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entites.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("select count(u) from User u")
	public int nbrUsers();
	
	@Query("select u from User u where u.login like :login")
	public User findUserByLogin(@Param("login") String login);

}
