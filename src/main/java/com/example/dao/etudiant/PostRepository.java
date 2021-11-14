package com.example.dao.etudiant;

import com.example.entites.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String>{
    @Query("select p from Post p where p.titre_post like ?1 and p.answer = 0")
    Post findPostByTitre(String idTitre);

    @Query("select p from Post p where p.id_post like ?1 and p.answer = 0")
    Post findPostById(String id);

    @Query("select distinct p.sujet from Post p where p.answer = 0")
    List<String> findAllSubjects();

    @Query("select count(p.sujet) from Post p where p.sujet = ?1 and p.answer = 0")
    int findCountSubject(String subject);

    @Query("select p from Post p where p.etudiant like ?1 and p.answer = 0 order by unix_timestamp(date_pub) DESC")
    List<Post> findMyPosts(Etudiant etudiant);

    @Query("select p from Post p where p.sujet like ?1 and p.answer = 0 order by unix_timestamp(date_pub) DESC")
    List<Post> findPostsBySubject(String subject);

    @Query("select p from Post p where p.answer like ?1")
    List<Post> findResponseToPost(String idPost);

    @Query("select p from Post p where (p.description like %?1 or p.titre_post like %?1) and p.answer = 0 order by unix_timestamp(date_pub) DESC")
    List<Post> findPostBySearch(String search);

    @Query("select p from Post p where p.answer = 0 and p.etudiant not like ?1 order by unix_timestamp(date_pub) DESC")
    List<Post> findOtherPostBySearch(Etudiant etudiant);

    @Query("select p from Post p where (p.description like %?1 or p.titre_post like %?1) and p.answer = 0 and p.id.etudiant = etudiant order by unix_timestamp(date_pub) DESC")
    List<Post> findMinePostBySearch(String search,Etudiant etudiant);

    void deleteById(String idPost);

    Post getOne(String idPost);

    @Query(value = "select p from Post p where p.answer = 0 order by unix_timestamp(date_pub) DESC")
    List<Post> chercherTout();


}
