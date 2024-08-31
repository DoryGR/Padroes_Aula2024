package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Curso;

public class CursoDAOImp implements CursoDAO{

	private Connection conexao;
	
	public CursoDAOImp(Connection cnx) {
		this.conexao = cnx;
	}

	@Override
	public void insert(Curso c) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conexao.prepareStatement("INSERT INTO curso(nomecurso) "
				+ "VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1,c.getNomeCurso());

			int linha = pst.executeUpdate();
			
			if (linha>0) {
				rs = pst.getGeneratedKeys();
				rs.next();
				c.setIdcurso(rs.getInt(1));
				System.out.println("Curso Cadastrado!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Curso obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByid(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Curso findByid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Curso> findAll() {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Curso> lista = new ArrayList<>();
		try {
			pst = conexao.prepareStatement("SELECT * FROM curso");
			rs = pst.executeQuery();
			while (rs.next()) {   
				Curso c = new Curso(rs.getInt("idcurso"), 
						            rs.getString("nomecurso"));
				lista.add(c);
			}
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		
		return lista;
	}
	
}
