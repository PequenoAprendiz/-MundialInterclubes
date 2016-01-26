/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.MudialInterclubes.Negocio;

import br.edu.ifnmg.MundialInterclubes.Aprensetação.Jogador;
import br.edu.ifnmg.MundialInterclubes.Persistencia.JogadorDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wellington
 */
public class JogadorBO {

    public void incluirJogador(Jogador jogadorEmEdicao) throws SQLException {
        this.validarCampos(jogadorEmEdicao);
        this.verificaJogadorEnumeroCamisa(jogadorEmEdicao);
        this.verificaTotalJogadores(jogadorEmEdicao);
        JogadorDAO jogadorDAO = new JogadorDAO();
        jogadorDAO.incluirJogador(jogadorEmEdicao);
    }

    private void verificaJogadorEnumeroCamisa(Jogador jogadorEmEdicao) throws SQLException {
        List<Jogador> jogadores = new ArrayList<>();
        JogadorDAO jogadorDAO = new JogadorDAO();
        jogadores = jogadorDAO.buscarTodosJogadores();
        
        for(Jogador j : jogadores){
            if(j.getNome().equals(jogadorEmEdicao.getNome())){
                throw new JogadorDuplicadoException("Já existe um jogador como este nome!");
            }
            if(j.getNumeroCamisa() == jogadorEmEdicao.getNumeroCamisa() && j.getIdTime() == jogadorEmEdicao.getIdTime()){
                throw new JogadorDuplicadoException("O numero escolhido já está sendo usado por outri jogador neste time!");
            }            
        }
    }

    private void verificaTotalJogadores(Jogador jogadorEmEdicao) throws SQLException {         
        JogadorDAO jogadorDAO = new JogadorDAO();
        int total = jogadorDAO.buscarTotalJogadores(jogadorEmEdicao.getIdTime());        
        if(total >= 5){
            throw new TimeCompletoException("O time selecionado já tem 23 jogadores");
        }       
    }

    private void validarCampos(Jogador jogadorEmEdicao) {
        if (jogadorEmEdicao.getNome().trim().equals("")) {
            throw new CamposObrigatoriosExceptions("Campo Nome do jogador está vazio!\n");
        }
        if (jogadorEmEdicao.getIdTime() < 0) {
            throw new CamposObrigatoriosExceptions("Campo time está vazio!\n");
        }
    }

}
