package chanBlog.blogs.controller;

import chanBlog.blogs.model.Blogger;
import chanBlog.blogs.util.Pagination;
import chanBlog.blogs.repository.BloggerDAOImpl;
import chanBlog.blogs.util.DescByBloggerTitle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "bloggers", urlPatterns = {"/bloggers/post-form.do", "/bloggers/post.do",
        "/bloggers/list.do", "/bloggers/sort.do",
        "/bloggers/detail.do", "/bloggers/update-form.do", "/bloggers/update.do", "/bloggers/delete.do" ,
        "/bloggers/login-form.do", "/bloggers/login.do", "/bloggers/logout.do"})
public class BloggerController extends HttpServlet {
        //DAO or Repository 객체 생성
        BloggerDAOImpl dao = new BloggerDAOImpl();
        protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(); //session 객체생성, 서블릿에서는 session 객체가 기본 객체가 아님
            request.setCharacterEncoding("UTF-8");
            String uri = request.getRequestURI();
            String contextPath = request.getContextPath();
            String command = uri.substring(contextPath.length() + 1); // blogs/post.do, blogs/list.do가 반환
            String action = command.substring(command.lastIndexOf("/") + 1); // post.do, list.do 반환
            Blogger blogger = new Blogger();

            if(action.equals("post-form.do")) {
                request.getRequestDispatcher("blogger-post-form.jsp").forward(request, response);
            } else if(action.equals("post.do")){
                blogger.setEmail(request.getParameter("email"));
                blogger.setPw(request.getParameter("pw"));
                blogger.setName(request.getParameter("name"));
                blogger.setPhone(request.getParameter("phone"));
                blogger.setAddress(request.getParameter("address"));

                if (dao.create(blogger) > 0) {
                    request.setAttribute("blogger", blogger);
                    request.setAttribute("msg", "성공 : 회원등록을 성공하였습니다.");
                    //처리결과를 view에 전달한다.
                    request.getRequestDispatcher("../status/message.jsp").forward(request,response);
                } else {
                    request.setAttribute("msg", "성공 : 회원등록을 실패하였습니다.");
                    request.getRequestDispatcher("../status/error.jsp").forward(request,response);
                }
            }
            if(action.equals("login-form.do")) {
                request.getRequestDispatcher("blogger-login-form.jsp").forward(request, response);
            }
            else if(action.equals("login.do")) {
                blogger.setEmail(request.getParameter("email"));
                blogger.setPw(request.getParameter("pw"));
                Blogger retBlogger = null;
                if (((retBlogger = dao.read(blogger)) != null) && retBlogger.getEmail().equals("admin@induk.ac.kr") && retBlogger.getPw().equals("cometrue")) {
                    session.setAttribute("Admin", retBlogger);
                    request.getRequestDispatcher("../main/index.jsp").forward(request, response);
                }
                else if ((retBlogger = dao.read(blogger)) != null) {
                    session.setAttribute("logined", retBlogger);
                    request.getRequestDispatcher("../main/index.jsp").forward(request, response);
                }
                else {
                    request.setAttribute("msg", "실패 : 회원 로그인 실패하였습니다.");
                    request.getRequestDispatcher("../status/message.jsp").forward(request, response);
                }
            }
            else if(action.equals("logout.do")) {
                session.invalidate(); // session 객체를 무효화 함. 즉 logined 속성값이 메모리에서 사라지게 됨
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
            }
            else if(action.equals("list.do")) {
                ArrayList<Blogger> bloggerList = new ArrayList<Blogger>(); // 처리결과 한개 이상의 블로그를 저장하는 객체
                String pageNo = request.getParameter("pn");
                int curPageNo = (pageNo != null)? Integer.parseInt(pageNo):1;
                int perPage = 3;
                int perPagination = 3;
                int totalRows = dao.readTotalRows();

                Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);

                if((bloggerList = (ArrayList<Blogger>) dao.readListPagination(pagination)) != null) { // 한 개 이상의 블로그가 반환. JCF(Java Collection Framework)에 대한 이해
                    request.setAttribute("bloggerList", bloggerList);
                    request.setAttribute("pagination", pagination);
                    request.getRequestDispatcher("blogger-list-view.jsp").forward(request, response); // blogs/list.jsp로 포워딩
                } else {
                    request.setAttribute("msg", "실패 : 회원 목록 조회를 실패하였습니다.");
                    request.getRequestDispatcher("../status/message.jsp").forward(request, response);
                }
            }else if (action.equals("detail.do")){
                String strId = request.getParameter("id");
                long id = Long.parseLong(strId);
                blogger.setId(id);
                Blogger retBlogger = null;
                if ((retBlogger = dao.BloggerRead(blogger)) != null){
                    System.out.println(retBlogger);
                    request.setAttribute("blogger", retBlogger);
                    request.getRequestDispatcher("blogger-detail.jsp").forward(request, response);
                }else{
                    request.setAttribute("msg", "실패");
                    request.getRequestDispatcher("../status/error.jsp").forward(request, response);
                }
            }else if(action.equals("update-form.do")) { //업데이트를 위한 정보 조회후 view에게 전송
                String strId = request.getParameter("id");
                long id = Long.parseLong(strId);
                blogger.setId(id);
                Blogger retBlogger = null;
                if ((retBlogger = dao.BloggerRead(blogger)) != null){
                    System.out.println(retBlogger);
                    request.setAttribute("blogger", retBlogger);
                    request.getRequestDispatcher("blogger-update-form.jsp").forward(request, response);
                }else{
                    request.setAttribute("msg", "업데이트 위한 조회 실패");
                    request.getRequestDispatcher("../status/error.jsp").forward(request, response);
                }
            }else if(action.equals("update.do")) { //dao에게 업데이트를 요청
                blogger.setId(Long.parseLong(request.getParameter("id")));
                blogger.setName(request.getParameter("name"));
                blogger.setEmail(request.getParameter("email"));
                blogger.setPw(request.getParameter("pw"));
                blogger.setPhone(request.getParameter("phone"));
                blogger.setAddress(request.getParameter("address"));

                if(dao.update(blogger) > 0) {
                    request.setAttribute("blogger", blogger);
                    //처리 결과를 view에게 전달한다. about.jsp -> processok.jsp
                    request.setAttribute("msg", "성공.");
                    request.getRequestDispatcher("../status/message.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", "실패.");
                    request.getRequestDispatcher("../status/error.jsp").forward(request, response);
                }
            }else if(action.equals("delete.do")) {
                blogger.setId(Long.parseLong(request.getParameter("id")));
                if(dao.delete(blogger) > 0) {
                    request.setAttribute("blogger", blogger);
                    request.setAttribute("work","멤버 삭제");
                    request.setAttribute("msg", "성공.");
                    request.getRequestDispatcher("../status/message.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", "실패.");
                    request.getRequestDispatcher("../status/error.jsp").forward(request, response);
                }
            }else if(action.equals("sort.do")){
                ArrayList<Blogger> bloggerList = new ArrayList<Blogger>();
                String properties = request.getParameter("by");
                if ((bloggerList = (ArrayList<Blogger>) dao.readList()) != null){ //한 개 이상의 블로그가 변환
                    if(properties.equals("desc,title"))
                        Collections.sort(bloggerList, new DescByBloggerTitle());  //블로그 제목 기준 내림차순
                    request.setAttribute("bloggerList", bloggerList);
                    request.getRequestDispatcher("blogger-list-view.jsp").forward(request, response);
                } else{
                    request.setAttribute("msg", "블로그 목록 조회 실패");
                    request.getRequestDispatcher("../status/error.jsp").forward(request, response);
                }
            }
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
            doService(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
            doService(request, response);
        }
}