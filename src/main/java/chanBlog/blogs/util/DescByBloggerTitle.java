package chanBlog.blogs.util;
import chanBlog.blogs.model.Blogger;

import java.util.Comparator;

public class DescByBloggerTitle implements Comparator<Blogger>{

    @Override
    public int compare(Blogger o1, Blogger o2) {
        return o2.getName().compareTo(o1.getName());
    }
}
