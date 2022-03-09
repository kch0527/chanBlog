package chanBlog.blogs.controller;

import chanBlog.blogs.model.Blog;
import chanBlog.blogs.model.Blogger;
import chanBlog.blogs.repository.BlogDAOImpl;
import chanBlog.blogs.util.DescByBlogTitle;
import chanBlog.blogs.util.Pagination;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
//urlPatterns : 다수의 url을 기술할 수 있다.
@WebServlet(name = "blogs", urlPatterns ={"/blogs/post-form.do","/blogs/post.do",
        "/blogs/list.do", "/blogs/sort.do", "/main/index.do",
        "/blogs/detail.do","/blogs/updateForm.do", "/blogs/update.do", "/blogs/delete.do"})
public class BlogController extends HttpServlet {

    BlogDAOImpl dao = new BlogDAOImpl(); // 데이터베이스 처리 요청 또는 서비스 요청 코드가 추가
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1); // blogs/post.do, blogs/list.do가 반환
        String action = command.substring(command.lastIndexOf("/") + 1); // post.do, list.do 반환
        HttpSession session = request.getSession();

        if(action.equals("post-form.do")) {
            Blogger blogger = ((Blogger)session.getAttribute("logined"));
            if (blogger == null){
                blogger = ((Blogger)session.getAttribute("Admin"));
            }
            request.setAttribute("email", blogger.getEmail());

            request.getRequestDispatcher("postForm.jsp").forward(request, response);

        }
            else if(action.equals("post.do")) {
            Blog blog = new Blog();
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));
            if (dao.create(blog) > 0) {
                request.setAttribute("blog", blog);
                //처리 결과를 view에게 전달한다.
                request.setAttribute("msg", "성공.");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "실패.");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        } else if(action.equals("list.do")){
            ArrayList<Blog> blogList = new ArrayList<Blog>();
            String pageNo = request.getParameter("pn"); //매개변수로 전달된 현재 페이지 번호가 정수형으로 저장
            int curPageNo = (pageNo != null)? Integer.parseInt(pageNo):1;
            int perPage = 3; // 한 페이지에 나타나는 행의 수
            int perPagination = 3; // 한 화면에 나타나는 페이지 번호 수
            int totalRows = dao.readTotalRows(); // dao에서 총 행의 수를 질의함

            Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);

            if ((blogList = (ArrayList<Blog>) dao.readListPagination(pagination)) != null){ //한 개 이상의 블로그가 변환
                request.setAttribute("blogList", blogList);
                request.setAttribute("pagination", pagination);
                request.getRequestDispatcher("list.jsp").forward(request, response);
            } else{
                request.setAttribute("msg", "블로그 목록 조회 실패");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        } else if(action.equals("sort.do")){
            ArrayList<Blog> blogList = new ArrayList<Blog>();
            String properties = request.getParameter("by");
            if ((blogList = (ArrayList<Blog>) dao.readList()) != null){ //한 개 이상의 블로그가 변환
                if(properties.equals("desc,title"))
                    Collections.sort(blogList, new DescByBlogTitle());  //블로그 제목 기준 내림차순
                request.setAttribute("blogList", blogList);
                request.getRequestDispatcher("list.jsp").forward(request, response);
            } else{
                request.setAttribute("msg", "블로그 목록 조회 실패");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        }
        else if (action.equals("detail.do")){
            Blog blog = new Blog();
            String strId = request.getParameter("id");
            long id = Long.parseLong(strId);
            blog.setId(id);
            Blog retBlog = null;
            if ((retBlog = dao.read(blog)) != null){
                System.out.println(retBlog);
                request.setAttribute("blog", retBlog);
                request.getRequestDispatcher("detail.jsp").forward(request, response);
            }else{
                request.setAttribute("msg", "블로그 조회 실패");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        } else if(action.equals("updateForm.do")) { //업데이트를 위한 정보 조회후 view에게 전송
            Blog blog = new Blog();
            String strId = request.getParameter("id");
            long id = Long.parseLong(strId);
            blog.setId(id);
            Blog retBlog = null;
            if ((retBlog = dao.read(blog)) != null){
                System.out.println(retBlog);
                request.setAttribute("blog", retBlog);
                request.getRequestDispatcher("updateForm.jsp").forward(request, response);
            }else{
                request.setAttribute("msg", "업데이트 위한 조회 실패");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        } else if(action.equals("update.do")) { //dao에게 업데이트를 요청
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));

            if(dao.update(blog) > 0) {
                request.setAttribute("blog", blog);
                //처리 결과를 view에게 전달한다. about.jsp -> processok.jsp
                request.setAttribute("msg", "성공.");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "실패.");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        }else if(action.equals("delete.do")) {
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));

            if(dao.delete(blog) > 0) {
                request.setAttribute("blog", blog);
                request.setAttribute("work","블로그 삭제");
                //처리 결과를 view에게 전달한다. about.jsp -> processok.jsp
                request.setAttribute("msg", "성공.");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "실패.");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }
}