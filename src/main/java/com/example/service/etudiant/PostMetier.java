package com.example.service.etudiant;


import com.example.dao.etudiant.PostRepository;
import com.example.entites.Etudiant;
import com.example.entites.Post;
import com.example.service.representant.IPostService;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class PostMetier implements IPostService, WebMvcConfigurer {
    @Autowired
    private PostRepository postRepository;


    public Post findPostByTitre(String idTitre) {
        return findPostByTitre(idTitre);
    }

    @Override
    public List<String> findAllSubjects() {
        return findAllSubjects();
    }

    public List<Post> findMyPosts(Etudiant etudiant){
        return findMyPosts(etudiant);
    }

    public List<Post> findPostsBySubject(String subject){
        return findPostsBySubject(subject);
    }

    public List<Post> findResponseToPost(String idPost){
        return findResponseToPost(idPost);
    }

    @Override
    public int findCountSubject(String subject) {
        return findCountSubject(subject);
    }

    @Override
    public List<Post> findPostBySearch(String search) {
        return findPostBySearch(search);
    }

    @Override
    public Post findPostById(String id) {
        return findPostById(id);
    }

    public List<Post> findOtherPostBySearch(Etudiant etudiant){
        return findOtherPostBySearch(etudiant);
    }
    public List<Post> findMinePostBySearch(String search,Etudiant etudiant){
        return findMinePostBySearch(search,etudiant);
    }
    public Post savePost(Post post){
        return postRepository.save(post);
    }

    @Override
    public List<Post> chercherTout() {
        return chercherTout();
    }

    public Page<Post> findPaginated(Pageable pageable, List<Post> books) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage *pageSize;
        List<Post> list;

        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }
        Page<Post> bookPage
                = new PageImpl<Post>(list, PageRequest.of(currentPage, pageSize), books.size());
        return bookPage;
    }

    public void delete(String idPost) {
        postRepository.deleteById(idPost);
    }
    public Post editPost(String idPost) {
        return postRepository.getOne(idPost);
    }


}
