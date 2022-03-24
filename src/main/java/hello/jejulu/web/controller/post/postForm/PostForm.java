package hello.jejulu.web.controller.post.postForm;


import hello.jejulu.domain.Role;
import hello.jejulu.domain.host.Host;
import hello.jejulu.domain.post.Category;
import hello.jejulu.domain.post.Post;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Component
public class PostForm {


    //post
    private Long postId;
    private String title;
    private String content;
    private String description;
    private int count;
    private Category category;
    private String path;


    //host
    private Long hostId;
    private String hostName;
    private String address;
    private String email;
    private String phone;
    private LocalDateTime createDate;





    public static PostForm createPostView(Post post,Host host){
       return  PostForm.builder()
               .postId(post.getId())
               .hostId(host.getId())
               .hostName(host.getHostName())
               .phone(host.getPhone())
               .email(host.getEmail())
               .address(host.getAddress())
               .title(post.getTitle())
               .description(post.getDescription())
               .category(post.getCategory())
               .path(post.getThumbnail().getPath())
               .content(post.getContent())
               .count(post.getCount())
               .createDate(post.getCreateDate())
               .build();
    }

}
