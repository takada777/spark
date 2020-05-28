package kadai;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
    //	UserDao2 dao = new UserDao2();;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 //Dao1 dao=new Dao1();
		//UserDao dao=new UserDao();
		UserDao2 dao=new UserDao2();
		String userid=request.getParameter("userid");
		String passwd=request.getParameter("passwd");
		
		
		
		if(dao.login(userid, passwd)) {
			UserEntity uentity =new UserEntity();
		uentity=dao.rolecheck(userid, passwd);
			int role=Integer.parseInt(uentity.getRole()) ;
			
			HttpSession session=request.getSession();
			session. setAttribute("userid", userid);
			if(role==2) {
				getServletContext().getRequestDispatcher("/Main.jsp").forward(request, response);
			}else if(role==1) {
				getServletContext().getRequestDispatcher("/AdminMain.jsp").forward(request, response);
			}else {
				
			}
			
		}else {
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		
	}
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
