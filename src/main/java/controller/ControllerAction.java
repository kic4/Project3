package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class ControllerAction extends HttpServlet {

		private Map commandMap = new HashMap(); //Map 인터페이스를 상속받고 있는 HashMap
  //Map은 키와 값으로 구성된 Entry객체를 저장하고 있는 구조를 가지고 있는 자료구조로 많은 양 데이터 검색에 뛰어남

		public void init(ServletConfig config) throws ServletException {
			//서블릿을 실행시 서블릿의 초기화 작업(생성자)
			
			String props = config.getInitParameter("propertyConfig");
			System.out.println("경로=" + props);
			// 경로에 맞는 commandPro.properties파일을 불러옴
			
			Properties pr = new Properties(); //Hashtables의 하위클래스로 Map을 상속받아 key와 value을 가짐
			//HashMap과 차이가 없지만 Properties 클래스는 파일 입출력을 지원한다.		
			// 명령어와 처리클래스의 매핑정보를 저장할 Properties객체 생성
			FileInputStream f = null; //FileInputStream 클래스로 파일 읽기

			try {
				f = new FileInputStream(props); // commandPro.properties파일의 내용을 읽어옴
				pr.load(f); // 파일의 정보를 Properties에 저장
			} catch (IOException e) {
				throw new ServletException(e);
			} finally {
				if (f != null)
					try {
						f.close();
					} catch (IOException ex) {
						System.out.println("ServletException(ex)=>"+ex);
					}
				}
			
			Iterator keyiter = pr.keySet().iterator();
			//Iterator 인터페이스는 컬렉션의 저장된 요소로 읽어올때 쓴다.
			//객체를 하나씩 꺼내서 객체명으로 Properties 객체에 저장된 객체로 접근
			
			while (keyiter.hasNext()) { //hasNext()메서드는 이동할 항목이 있으면 true 없으면 false
				String command = (String) keyiter.next(); //next()메서드는 가리키고 있는 위치에서 순차적으로 하나 증가하여 이동
				System.out.println("command=" + command);
				// 요청한 명령어(키)에 해당하는 클래스명을 구함
				String className = pr.getProperty(command);
				System.out.println("className=" + className);

				try {
					// 그 클래스의 객체를 얻어오기위해 메모리에 로드
					Class commandClass = Class.forName(className);
					System.out.println("commandClass=" + commandClass);
					Object commandInstance = commandClass.newInstance(); // 명령어 처리클래스 객체생성
					System.out.println("commandInstance=" + commandInstance);

					// Map객체 commandMap에 저장
					commandMap.put(command, commandInstance);
					System.out.println("commandMap=" + commandMap);

				} catch (ClassNotFoundException e) {
					throw new ServletException(e);
				} catch (InstantiationException e) {
					throw new ServletException(e);
				} catch (IllegalAccessException e) {
					throw new ServletException(e);
				}
			} // while	
		}

		public void doGet(// get방식의 서비스 메소드
				HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			requestPro(request, response);
		}

		protected void doPost(// post방식의 서비스 메소드
				HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			requestPro(request, response);
		}

		// 시용자의 요청을 분석해서 해당 작업을 처리
		private void requestPro(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String view = null; //요청명령어에 따라서 이동할 페이지 저장
			// /list.do = action.ListAction, /writeForm.do=action.WriteFormAction
			
			//ListAction com = null;
			//WriteFormAction com = null;
			CommandAction com = null; //어떠한 자식클래스의 객체라도 부모형으로 형변환
			//CommandAction com = new 	ListAction();
			//CommandAction com = new WriteFromAction();
			try {
				//요청명렁어 분리
				String command = request.getRequestURI();
				System.out.println("request.getRequestURI()=>"+request.getRequestURI());
				System.out.println("request.getContextPath()=>"+request.getContextPath());
				// /JspBoard2/list.do
				// /JspBoard2
				if(command.indexOf(request.getContextPath())==0) {
					command=command.substring(request.getContextPath().length());
					System.out.println("실질적인 command=>"+command);// /list.do
				}
				//요청명령어-> /list.do -> action.ListAction 객체 -> requestPro()를 호출하기 위해서
				com = (CommandAction)commandMap.get(command);
				System.out.println("com=>"+com); //action.ListAction@주소값
				view = com.requestPro(request, response);
				System.out.println("view=>"+view);// /list.jsp
			}catch(Throwable e) {
				throw new ServletException(e); //서블릿 예외처리
			}
			//위에서 요청명령어에 해당하는 view로 데이터를 공유시키면서 이동 -> forward
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response); // /list.jsp
		}
}