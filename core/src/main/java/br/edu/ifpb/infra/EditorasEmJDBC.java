package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Editora;
import br.edu.ifpb.domain.Editoras;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 25/04/2022, 21:45:44
 */
@Stateless
public class EditorasEmJDBC implements Editoras {
    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;

    @Override
    public void nova(Editora editora) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(
                "INSERT INTO editoras(localDeOrigem, nomeFantasia) VALUES ( ?, ? );"
            );
            prepareStatement.setString(1, editora.getLocalDeOrigem());
            prepareStatement.setString(2, editora.getNomeFantasia());
            prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivrosEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public boolean nomeDuplicado(String nomeFantasia) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(
                    "SELECT COUNT(nomeFantasia) AS contador FROM editoras WHERE nomeFantasia like ?"
            );
            prepareStatement.setString(1, nomeFantasia);
            ResultSet result = prepareStatement.executeQuery();
            if(result.next()) {
                long contador = result.getLong("contador");
                return contador > 0;
            }
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public List<Editora> todas() {
        try (Connection connection = dataSource.getConnection()) {
            List<Editora> lista = new ArrayList<>();
            ResultSet result = connection.prepareStatement(
                "SELECT * FROM editoras"
            ).executeQuery();
            while (result.next()) {
                lista.add(
                    criarEditoras(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<Editora> porLocalDeOrigem(String localDeOrigem) {
        try (Connection connection = dataSource.getConnection()) {
            List<Editora> lista = new ArrayList<>();
            PreparedStatement prepareStatement = connection.prepareStatement(
                    "SELECT * FROM editoras WHERE localDeOrigem like ?"
            );
            prepareStatement.setString(1, localDeOrigem);
            ResultSet result = prepareStatement.executeQuery();
            while (result.next()) {
                lista.add(
                        criarEditoras(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    private Editora criarEditoras(ResultSet result) throws SQLException {
        String localDeOrigem = result.getString("localDeOrigem");
        String nomeFantasia = result.getString("nomeFantasia");
        int codigo = result.getInt("codigo");
        return new Editora(codigo,localDeOrigem,nomeFantasia);
    }

}
