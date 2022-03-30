package hello.jejulu.service.post;

import hello.jejulu.domain.host.Host;
import hello.jejulu.domain.post.Category;
import hello.jejulu.domain.post.Post;
import hello.jejulu.domain.thumbnail.Thumbnail;
import hello.jejulu.repository.PostRepository_B;
import hello.jejulu.repository.ThumbnailRepository_B;
import hello.jejulu.web.controller.post.postForm.PostSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl {


    private final PostRepository_B postRepositoryB;
    private final ThumbnailService_B thumbnailServiceB;




    //포스트 저장
    @Transactional
    public void savePost(PostSaveForm form, Host host)throws IOException{


        Thumbnail thumbnail = thumbnailServiceB.createThumbnail(form.getFile());
        Post post = PostSaveForm.toPost(form, thumbnail,host);
        postRepositoryB.save(post);

    }

    //포스트 조회
    public Post searchPost(Long postId){
        Post post = postRepositoryB.findOne(postId);
        //조회수 증가
        post.countPlus();

        return post;
    }

    //포스트 업데이트
    @Transactional
    public void updatePost(Long postId, String title,
                           String description,Category category,
                           MultipartFile file,String content)throws IOException{



        //포스트 찾기
        Post post = postRepositoryB.findOne(postId);

        //file null 처리


        //썸네일 업데이트
        String updateThumbnailId = thumbnailServiceB.updateThumbnail(file,post.getThumbnail());

        //영속성 썸네일 조회
        Thumbnail thumbnail = thumbnailServiceB.findThumbnail(updateThumbnailId);

        //post 수정
        //dirty check
        post.setTitle(title);
        post.setDescription(description);
        post.setCategory(category);
        post.setThumbnail(thumbnail);
        post.setContent(content);
    }

    //포스트 삭제

    @Transactional
    public void deletePost(Long postId){

        Post post = postRepositoryB.findOne(postId);
        Thumbnail thumbnail = thumbnailServiceB.findThumbnail(post.getThumbnail().getId());
        thumbnailServiceB.deleteThumbnail(thumbnail.getId());
        postRepositoryB.removePost(post.getId());

    }

    public List<Post> findPostCategory(Category category){

        List<Post> posts = postRepositoryB.findByCategory(category);

        return posts;
    }
}
