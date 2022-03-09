package chanBlog.blogs.repository;

import chanBlog.blogs.model.Blog;
import chanBlog.blogs.util.Pagination;

import java.util.List;

public interface BlogDAO {
    int create(Blog blog);
    Blog read(Blog blog);
    List<Blog> readList();
    int update(Blog blog);
    int delete(Blog blog);

    int readTotalRows();
    List<Blog> readListPagination(Pagination pagination);
}
