package com.example.service.representant;

import com.example.entites.Etudiant;
import com.example.entites.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService {
    public Post findPostByTitre(String idTitre);
    public List<String> findAllSubjects();

    public List<Post> findMyPosts(Etudiant etudiant);
    public List<Post> findPostsBySubject(String subject);
    public List<Post> findResponseToPost(String idPost);
    int findCountSubject(String subject);
    List<Post> findPostBySearch(String search);
    List<Post> findOtherPostBySearch(Etudiant etudiant);

    List<Post> findMinePostBySearch(String search,Etudiant etudiant);
    Post findPostById(String id);
    public Post savePost(Post post);
    public List<Post> chercherTout();
}
