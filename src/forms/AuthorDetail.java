/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import java.awt.Color;
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
public class AuthorDetail extends javax.swing.JFrame {
String AuthorName;
 
/**
     * Creates new form nimra_ahmed
     */
    
     
    /**
     * Creates new form AuthorDetail
     */
    public AuthorDetail() {
        initComponents();
    }
public AuthorDetail(String Name) {
      AuthorName = Name;
      String u="?useUnicode=yes&characterEncoding=UTF-8";
AuthorName=u+AuthorName;
AuthorName=AuthorName.substring(39,AuthorName.length());
        initComponents();
        shower();
          detailer();

}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(771, 479));
        setMinimumSize(new java.awt.Dimension(771, 479));
        setPreferredSize(new java.awt.Dimension(650, 480));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Khmer UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NOVEL MANIA");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 4, 770, 70);

        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(180, 130, 410, 60);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(230, 90, 330, 30);

        jButton1.setFont(new java.awt.Font("Khmer UI", 1, 14)); // NOI18N
        jButton1.setText("SEARCH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(300, 220, 200, 40);

        jButton2.setFont(new java.awt.Font("Khmer UI", 1, 14)); // NOI18N
        jButton2.setText("BACK");
        jButton2.setMaximumSize(new java.awt.Dimension(87, 25));
        jButton2.setMinimumSize(new java.awt.Dimension(87, 25));
        jButton2.setPreferredSize(new java.awt.Dimension(87, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(300, 280, 200, 40);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 350, 0, 0);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(70, 330, 650, 140);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/8.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -40, 770, 580);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AuthorSearch s= new  AuthorSearch();
      s.setVisible(true);
      AuthorDetail.this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         String Name = jComboBox1.getSelectedItem().toString();
        NovelDetail s= new  NovelDetail(Name);
      s.setVisible(true);
      AuthorDetail.this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    void detailer()
    {
        AuthorName=AuthorName.replaceAll("\\s+","");
        
    OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);
FileManager.get().readModel( model, "urdu.owl" );
       String queryString ="prefix my: <http://www.semanticweb.org/bisma/ontologies/2016/2/urdu.owl#> \n" +
"PREFIX owl:     <http://www.w3.org/2002/07/owl#>\n" +
"SELECT ?p ?o  \n" +
"WHERE {  my:"+AuthorName+"?p ?o . filter isLiteral(?o) }";
      
Query query = QueryFactory.create(queryString);
QueryExecution qe= QueryExecutionFactory.create(query, model);
org.apache.jena.query.ResultSet resultset = qe.execSelect();
java.io.ByteArrayOutputStream baos= new java.io.ByteArrayOutputStream();
            ResultSetFormatter.outputAsCSV(baos, resultset);
            String answer= baos.toString();
            answer= java.util.Arrays.toString(answer.split("http://www.semanticweb.org/bisma/ontologies/2016/2/noveljkp.owl#author_detail,"));
      String[] array = answer.split(",");
      String s=array[3];
      int a= "http://www.semanticweb.org/bisma/ontologies/2016/2/urdu.owl#ناول_نگار".length();
     s = s.substring(0, s.length()-a);
   jLabel6.setText("<html><p>"+s+"</p></html>");
    }
    
    void shower()
{
    jLabel3.setText("SEARCH NOVEL OF"+AuthorName+":");
   AuthorName=AuthorName.replaceAll("\\s+","");
    OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);
FileManager.get().readModel( model, "urdu.owl" );
String queryString ="prefix uni: <http://www.semanticweb.org/bisma/ontologies/2016/2/urdu.owl#>\n" +
"select * {?Novel uni:تحریر_کردہ uni:"+AuthorName+"}";  
//System.out.println(queryString); 
Query query = QueryFactory.create(queryString);
QueryExecution qe= QueryExecutionFactory.create(query, model);
org.apache.jena.query.ResultSet resultset = qe.execSelect();
java.io.ByteArrayOutputStream baos= new java.io.ByteArrayOutputStream();
            ResultSetFormatter.outputAsCSV(baos, resultset);
            String answer= baos.toString();
           answer= java.util.Arrays.toString(answer.split("http://www.semanticweb.org/bisma/ontologies/2016/2/urdu.owl#"));
       String[]  array = answer.split(",");
       String lastOne = array[array.length-1];
       lastOne=lastOne.substring(0,lastOne.length()-1);
       for(int i=1;i<array.length-1;i++)
       {
       
           jComboBox1.addItem(array[i]);
       }
            jComboBox1.addItem(lastOne);
       
}
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
            java.util.logging.Logger.getLogger(AuthorDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuthorDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuthorDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuthorDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuthorDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
