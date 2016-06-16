/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

/**
 *
 * @author Bisma
 */
public class Detail extends javax.swing.JFrame {
String name;
String nname;
    /**
     * Creates new form Detail
     */
    public Detail() {
        initComponents();
    }
    public Detail(String i,String m) {
        name=i;
        String u="?useUnicode=yes&characterEncoding=UTF-8";
name=u+name;
name=name.substring(39,name.length());
nname=m;
        String un="?useUnicode=yes&characterEncoding=UTF-8";
nname=un+nname;
nname=nname.substring(39,nname.length());
        initComponents();
        shower();
    }

   void shower()
   { 
       name=name.replaceAll("\\s+","");
     OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);
FileManager.get().readModel( model, "urdu.owl" );
       String queryString ="prefix my: <http://www.semanticweb.org/bisma/ontologies/2016/2/urdu.owl#> \n" +
"PREFIX owl:  <http://www.w3.org/2002/07/owl#>\n" +
"SELECT ?p ?o  \n" +
"WHERE {  my:"+name+" ?p ?o . filter isLiteral(?o) }";
     
Query query = QueryFactory.create(queryString);
QueryExecution qe= QueryExecutionFactory.create(query, model);
org.apache.jena.query.ResultSet resultset = qe.execSelect();
java.io.ByteArrayOutputStream baos= new java.io.ByteArrayOutputStream();
            ResultSetFormatter.outputAsCSV(baos, resultset);
           
            String answer= baos.toString();
       //     System.out.println(answer);  
           answer=answer.substring(91);
     //System.out.println(answer);    
     jLabel2.setText("<html><h1>"+name+"</h1><p>"+answer+"</p></html>");
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 472));
        setMinimumSize(new java.awt.Dimension(700, 472));
        getContentPane().setLayout(null);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 120, 590, 200);

        jLabel3.setFont(new java.awt.Font("Khmer UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("NOVEL MANIA");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 4, 750, 70);

        jButton1.setFont(new java.awt.Font("Khmer UI", 1, 14)); // NOI18N
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(290, 350, 180, 60);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Bisma\\Documents\\NetBeansProjects\\OntologyNovel\\src\\forms\\7.jpg")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 710, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     NovelDetail s= new  NovelDetail(nname);
      s.setVisible(true);
      Detail.this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Detail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Detail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Detail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Detail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Detail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
