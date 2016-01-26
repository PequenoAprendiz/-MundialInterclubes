/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.MundialInterclubes.Persistencia;

import br.edu.ifnmg.MundialInterclubes.Aprensetação.Relatorio;
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
public class RelatorioDAO {

    private static final String SQL_CONSULTA_RELATORIO = "SELECT J.NOME, T.NOME FROM JOGADOR J JOIN TIMEFUTEBOL T ON T.ID = J.IDTIME ORDER BY J.NOME";

    public List<Relatorio> buscarJogadorTime() throws SQLException {
      Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Relatorio> listaJogaorTime = new ArrayList<>();
        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_CONSULTA_RELATORIO);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                Relatorio relatorio = this.extrairLinhaResultadoBuscarJogadorTime(resultado);
                //Adiciona um item à lista que será retornada
                listaJogaorTime.add(relatorio);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaJogaorTime;
    }

    private Relatorio extrairLinhaResultadoBuscarJogadorTime(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        Relatorio relatorio = new Relatorio();     
        relatorio.setNomeJogador(resultado.getString(1));
        relatorio.setNomeTime(resultado.getString(2));
        return relatorio;
    }
}
