/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.MudialInterclubes.Negocio;

import br.edu.ifnmg.MundialInterclubes.Aprensetação.Time;
import br.edu.ifnmg.MundialInterclubes.Persistencia.TimeDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wellington
 */
public class TimeBO {

    public void incluirTime(Time timeEmEdicao) throws SQLException {
        this.validarCampos(timeEmEdicao);
        this.vericaTimeExistente(timeEmEdicao);
        TimeDAO timeDAO = new TimeDAO();
        timeDAO.incluirTime(timeEmEdicao);
    }   

    public void validarCampos(Time timeEmEdicao) {
        if(timeEmEdicao.getNome().trim().equals("")){
            throw new CamposObrigatoriosExceptions("Campo Nome do time está vazio!\n");
        }
         if(timeEmEdicao.getSiglaPais().trim().equals("")){
            throw new CamposObrigatoriosExceptions("Campo Sigla do país do time está vazio!\n");
        }        
    }

    private void vericaTimeExistente(Time timeEmEdicao) throws SQLException{
        List<Time> times = new ArrayList<>();
        times = buscarTodosTimes();
        for(Time t: times){
            if(t.getNome().equals(timeEmEdicao.getNome())){
                throw new TimeExistenteException("Já existe um time com este nome!");
            }
        }
    }
    
    public List<Time> buscarTodosTimes() throws SQLException {
       TimeDAO timeDAO = new TimeDAO();
       return timeDAO.buscarTodosTimes();
    }
    
}
