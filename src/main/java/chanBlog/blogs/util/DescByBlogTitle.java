package chanBlog.blogs.util;

import chanBlog.blogs.model.Blog;

import java.util.Comparator;

public class DescByBlogTitle implements Comparator<Blog> {
    @Override
    public int compare(Blog o1, Blog o2) {
        return o2.getContent().compareTo(o1.getTitle()); // o2 <= o1 : 음수 / o2 > o1 : 1이상 내림차순
        // return o1.getContent().compareTo(o2.getTitle()); // 오름 차순
        // return o2.getTitle().compareTo(o1.getTitle()); // 제목기준 내림차순
        // return o2.getEmail().compareTo(o1.getEmail()); // email기준 오름차순
    }
}
