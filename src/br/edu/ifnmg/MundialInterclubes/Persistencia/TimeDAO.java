/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.MundialInterclubes.Persistencia;

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
public class TimeDAO {

    private static final String SQL_INSERT = "INSERT INTO TIMEFUTEBOL(NOME, SIGLA) VALUES (?,?)";
    private static final String SQL_BUSCAR_TODOS_TIMES = "SELECT ID, NOME FROM TIMEFUTEBOL";
    
   
    public void incluirTime(Time timeEmEdicao) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_INSERT);
            //Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setString(1, timeEmEdicao.getNome());
            comando.setString(2, timeEmEdicao.getSiglaPais());

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

    public List<Time> buscarTodosTimes( ) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Time> listaTimes = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS_TIMES);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                Time time = this.extrairLinhaResultadoBuscarTodosTimes(resultado);
                //Adiciona um item à lista que será retornada
                listaTimes.add(time);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaTimes;
    }

    private Time extrairLinhaResultadoBuscarTodosTimes(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        Time time = new Time();     
        time.setId(resultado.getInt(1));
        time.setNome(resultado.getString(2));
        return time;
    }
}
