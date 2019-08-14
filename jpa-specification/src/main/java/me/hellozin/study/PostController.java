package me.hellozin.study;

import me.hellozin.study.PostSpecs.SearchKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/post/list")
    public List<Post> getPostList(@RequestParam(required = false) Map<String, Object> searchRequest) {

        Map<SearchKey, Object> searchKeys = new HashMap<>();
        for (String key : searchRequest.keySet()) {
            searchKeys.put(SearchKey.valueOf(key.toUpperCase()), searchRequest.get(key));
        }

        return searchKeys.isEmpty()
                ? postRepository.findAll()
                : postRepository.findAll(PostSpecs.searchWith(searchKeys));
    }

}
