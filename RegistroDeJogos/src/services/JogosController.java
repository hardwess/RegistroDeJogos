package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.Jogo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JogosController")
public class JogosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		List<Jogo> lista = new ArrayList<>();
		getServletContext().setAttribute("JOGOS", lista);
		String sql = "select nome, dificuldade from jogos";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "admin");
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			lista.add((Jogo) getServletContext().getAttribute("JOGOS"));

			while (rs.next()) {
				Jogo j = new Jogo(rs.getString(1), rs.getString(2));
				lista.add(j);
			}

			getServletContext().removeAttribute("JOGOS");
			getServletContext().setAttribute("JOGOS", lista);
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
			System.out.println("teste");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("<html><h1>Acesse a pagina <a href=\"./jogos.jsp\">./jogos.jsp</a></h1></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		init();
		
		String txtNome = request.getParameter("txtNome");
		String txtDificuldade = request.getParameter("txtDificuldade");

		if (request.getParameter("cmd").equals("Registrar")) {
			if (txtNome.equals("")) {
				String message = String.format("Digite o nome do jogo!");
				request.getSession().setAttribute("MESSAGE", message);
				response.sendRedirect("./jogos.jsp");
			} else {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM",
							"admin");
					String sql = "INSERT INTO jogos (nome, dificuldade) VALUES (?, ?)";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, txtNome);
					stmt.setString(2, txtDificuldade);
					stmt.executeUpdate();
					response.getWriter().append("Nome: " + txtNome + " \n");
					response.getWriter().append("Dificuldade: " + txtDificuldade + " \n \n ");
					response.getWriter().append("Jogo Registrado Com Sucesso");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				String message = String.format("Jogo registrado com sucesso");
				request.getSession().setAttribute("MESSAGE", message);
				response.sendRedirect("./jogos.jsp");
			}
		} else {
			// TO DO
		}
	}

}
