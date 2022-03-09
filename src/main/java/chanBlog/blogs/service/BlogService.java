package chanBlog.blogs.service;

import chanBlog.blogs.model.Blog;

import java.util.List;

public interface BlogService {
    int postBlog(Blog blog); // 생성
    Blog getBlog(Long id);		// primary key에 해당하는 id로  조회
    List<Blog> getBlogs(); // 모든 사용자 조회
    List<Blog> getBlogsByTitle(String title); // title으로 조회
    List<Blog> getBlogsByBlogger(String blogger); // blogger으로 조회
    List<Blog> getBlogsByPage(int index, int size); // 페이지로 조회
    int updateBlog(Blog blog); // 수정
    int deleteBlog(Long id); // 삭제
    int getTotalRowCount(); // total row count for pagination
}
