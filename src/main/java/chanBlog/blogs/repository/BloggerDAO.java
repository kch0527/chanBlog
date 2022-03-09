package chanBlog.blogs.repository;

import chanBlog.blogs.util.Pagination;
import chanBlog.blogs.model.Blogger;

import java.util.List;

public interface BloggerDAO {
    int create(Blogger member);
    Blogger read(Blogger member);
    List<Blogger> readList();
    int update(Blogger member);
    int delete(Blogger member);

    int readTotalRows();
    List<Blogger> readListPagination(Pagination pagination);
}
