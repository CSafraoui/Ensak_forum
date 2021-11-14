package com.example.service.etudiant;

import com.example.dao.admin.UserRepository;
import com.example.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserMetier {
	@Autowired
    private UserRepository repo;
	
	public User GetUserId(Long id) {
		User user=repo.getOne(id);
		return user;
	}
}
