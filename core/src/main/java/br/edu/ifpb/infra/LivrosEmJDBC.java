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
    public void criar(Livro livro) {
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                "INSERT INTO livros(titulo, dataDeLancamento) VALUES ( ?, ? );"
            );
            statement.setString(1,livro.titulo());
            statement.setDate(
                2,Date.valueOf(livro.dataLancamento())
            );
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivrosEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }

    }

    @Override
    public Livro buscarPorId(long id) {
        try {
            PreparedStatement prepareStatement = dataSource.getConnection().prepareStatement(
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
    public List<Livro> todos() {
        try {
            List<Livro> lista = new ArrayList<>();
            ResultSet result = dataSource.getConnection().prepareStatement(
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
