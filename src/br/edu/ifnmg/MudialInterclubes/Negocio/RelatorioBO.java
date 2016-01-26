/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.MudialInterclubes.Negocio;

import br.edu.ifnmg.MundialInterclubes.Aprensetação.Relatorio;
import br.edu.ifnmg.MundialInterclubes.Persistencia.RelatorioDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Wellington
 */
public class RelatorioBO {

    public List<Relatorio> buscarJogadorTime() throws SQLException {
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        return relatorioDAO.buscarJogadorTime();
    }
    
}
