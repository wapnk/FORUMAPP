package app.forum.service;

import app.forum.dto.PostDTO;
import app.forum.model.Post;
import app.forum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DzialService dzialService;

    public List<Post> pobierzPosty() {
        return postRepository.getAll();
    }


    public Post dodajPost(Post post, Long dzial_id){
//        Dzial dzial=dzialService.pobierzDzialPoId(dzial_id);
//        List<Post> posty = dzial.getPosty();
//        posty.add(post);
//        dzial.setPosty(posty);
//        post.setDzial(dzial);
        return postRepository.save(post);
    }


    public Post pobierzPoId(Long id) {
        return postRepository.getById(id);
    }

    public List<Post> zapiszWszystkie(List<Post> posty) {
        return postRepository.saveAll(posty);
    }
}
