package br.edu.ifpb.infra;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/04/2022, 21:30:55
 */

import br.edu.ifpb.domain.Livro;
import br.edu.ifpb.domain.Livros;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class LivrosEmJDBC implements Livros {
    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;

    @Override
    public Livro criar(Livro livro) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(
                "INSERT INTO livros(titulo, dataDeLancamento, url_capa) VALUES ( ?, ? , ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            prepareStatement.setString(1,livro.titulo());
            prepareStatement.setDate(
                2, Date.valueOf(livro.dataLancamento())
            );
            prepareStatement.setString(3,livro.getCapa());
            prepareStatement.executeUpdate();
            ResultSet resultSet = prepareStatement.getGeneratedKeys();
            if(resultSet.next())
                livro.setId(resultSet.getInt("id"));
            return livro;
        } catch (SQLException ex) {
            Logger.getLogger(LivrosEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }

    @Override
    public Livro atualizar(long id, Livro livro) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(
                    "UPDATE livros SET titulo= ?, dataDeLancamento= ?, url_capa = ? WHERE id=?"
            );
            prepareStatement.setString(1,livro.titulo());
            prepareStatement.setDate(
                    2,Date.valueOf(livro.dataLancamento())
            );
            prepareStatement.setString(3,livro.getCapa());
            prepareStatement.setLong(4, id);
            prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivrosEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
        return livro;
    }

    @Override
    public Livro buscarPorId(long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(
                    "SELECT * FROM livros where id = ?"
            );
            prepareStatement.setLong(1, id);
            ResultSet result = prepareStatement.executeQuery();
            if(result.next())
                return criarLivros(result);
            return null;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public boolean remover(long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(
                    "DELETE FROM livros WHERE id=?"
            );
            prepareStatement.setLong(1, id);
            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(LivrosEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    }

    @Override
    public List<Livro> todos() {
        try (Connection connection = dataSource.getConnection()) {
            List<Livro> lista = new ArrayList<>();
            ResultSet result = connection.prepareStatement(
                "SELECT * FROM livros"
            ).executeQuery();
            while (result.next()) {
                lista.add(
                    criarLivros(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    private Livro criarLivros(ResultSet result) throws SQLException {
        LocalDate dataDeLancamento = result.getDate("dataDeLancamento").toLocalDate();
        String titulo = result.getString("titulo");
        String capa = result.getString("url_capa");
        int id = result.getInt("id");
        return new Livro(id,titulo,dataDeLancamento, capa);
    }

}
