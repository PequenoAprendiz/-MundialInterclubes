/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.MundialInterclubes.Persistencia;

import br.edu.ifnmg.MundialInterclubes.Aprensetação.Jogador;
import br.edu.ifnmg.MundialInterclubes.Aprensetação.Time;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wellington
 */
public class JogadorDAO {

    private static final String SQL_INSERT = "INSERT INTO JOGADOR (NOME, IDTIME, NUMEROCAMISA) VALUES (?,?,?)";
    private static final String SQL_BUSCAR_TODOS_JOGADORES = "SELECT ID, NOME,NUMEROCAMISA, IDTIME FROM JOGADOR";
    private static final String SQL_TOTAL_JOGADORES = "SELECT COUNT(ID) FROM JOGADOR WHERE IDTIME = ?";

    public void incluirJogador(Jogador jogadorEmEdicao) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_INSERT);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setString(1, jogadorEmEdicao.getNome());
            comando.setInt(2, jogadorEmEdicao.getIdTime());
            comando.setInt(3, jogadorEmEdicao.getNumeroCamisa());
            //Executa o comando
            comando.execute();
            //Persiste o comando no banco de dados
            conexao.commit();
            //System.out.println("Manobra cadastrada com sucesso!");
        } catch (Exception e) {
            //Caso aconteça alguma exeção é feito um rollback para o banco de
            //dados retornar ao seu estado anterior.
            if (conexao != null) {
                conexao.rollback();
            }
            throw e;
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando);
        }
    }

    public List<Jogador> buscarTodosJogadores() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Jogador> listaJogador = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS_JOGADORES);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                Jogador jogador = this.extrairLinhaResultadoBuscarTodosTimes(resultado);
                //Adiciona um item à lista que será retornada
                listaJogador.add(jogador);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaJogador;
    }

    private Jogador extrairLinhaResultadoBuscarTodosTimes(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        Jogador jogador = new Jogador();
        jogador.setId(resultado.getInt(1));
        jogador.setNome(resultado.getString(2));
        jogador.setNumeroCamisa(resultado.getInt(3));
        jogador.setIdTime(resultado.getInt(3));
        return jogador;
    }

    public int buscarTotalJogadores(int idTime) throws SQLException {

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        int total = 0;
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_TOTAL_JOGADORES);
            //Executa o comando e obtém o resultado da consulta
            comando.setInt(1, idTime);

            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            //Adiciona um item à lista que será retornada
          while (resultado.next()) {
                int totalJogadores = this.extrairLinhaResultadoTotalJogadores(resultado);
                //Adiciona um item à lista que será retornada
                total = totalJogadores;
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }

        return total;
    }

    private int extrairLinhaResultadoTotalJogadores(ResultSet resultado) throws SQLException {
        int total;
        total = resultado.getInt(1);
        return total;
    }

}
