/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.MundialInterclubes.Aprensetação;

import br.edu.ifnmg.MudialInterclubes.Negocio.CamposObrigatoriosExceptions;
import br.edu.ifnmg.MudialInterclubes.Negocio.TimeBO;
import br.edu.ifnmg.MudialInterclubes.Negocio.TimeExistenteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Wellington
 */
public class CadastroTimes extends javax.swing.JFrame {

    private Time timeEmEdicao;

    /**
     * Creates new form cadastroTimes
     */
    public CadastroTimes() throws SQLException {
        this.timeEmEdicao = new Time();
        prepararTela();
    }

    public void prepararTela() throws SQLException {
        try {            
            this.initComponents();
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Manobras cadastrados", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblNomeTime = new javax.swing.JLabel();
        txtNomeTime = new javax.swing.JTextField();
        lblNomeTime1 = new javax.swing.JLabel();
        txtSiglaPaises = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnfechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Time", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 2, 18))); // NOI18N

        lblNomeTime.setText("Nome:");

        lblNomeTime1.setText("Sigla País:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnfechar.setText("Cancelar");
        btnfechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeTime)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSiglaPaises, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNomeTime, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNomeTime1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(207, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnfechar)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblNomeTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNomeTime1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSiglaPaises, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnfechar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            this.incluirTime();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroTimes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnfecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfecharActionPerformed
      this.dispose();
    }//GEN-LAST:event_btnfecharActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnfechar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNomeTime;
    private javax.swing.JLabel lblNomeTime1;
    private javax.swing.JTextField txtNomeTime;
    private javax.swing.JTextField txtSiglaPaises;
    // End of variables declaration//GEN-END:variables

    private void incluirTime() throws SQLException {
        try {
            this.recuperarCamposTela();
            TimeBO timeBO = new TimeBO();
            timeBO.incluirTime(this.timeEmEdicao);
            JOptionPane.showMessageDialog(this, "Incluido com Sucesso!", "Novo time", JOptionPane.INFORMATION_MESSAGE); 
        } catch (CamposObrigatoriosExceptions c) {
            String mensagem = "Aviso!\n" + c.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Novo time", JOptionPane.INFORMATION_MESSAGE);            
        }catch (TimeExistenteException t) {
            String mensagem = "Aviso!\n" + t.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Novo time", JOptionPane.INFORMATION_MESSAGE);            
        }
    }

    private void recuperarCamposTela() {
        this.timeEmEdicao.setNome(txtNomeTime.getText());
        this.timeEmEdicao.setSiglaPais(txtSiglaPaises.getText());
    }
}
