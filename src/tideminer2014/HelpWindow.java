/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tideminer2014;

/**
 *
 * @author Chris
 */
public class HelpWindow extends javax.swing.JFrame {

    /**
     * Creates new form HelpWindow
     */
    public HelpWindow() {
        this.setVisible(false);
        initComponents();
        this.setTitle("TideMiner Help");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaHelpText = new javax.swing.JTextArea();
        jButtonCloseHelp = new javax.swing.JButton();

        jTextAreaHelpText.setColumns(20);
        jTextAreaHelpText.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextAreaHelpText.setLineWrap(true);
        jTextAreaHelpText.setRows(5);
        jTextAreaHelpText.setText("TideMiner Help\n\nTideMiner is a single-task program. It takes a file of tide elevations as generated by the NOAA site and a set of elevations (one per line - manually entered or pasted in from a spreadsheet) and calculates the amount of time each of those elevations has been submerged. The results of that analysis are presented as tab-delimited text suitable for copy-pasting into Excel or whatever you preferred spreadsheet program is, and can also be saved as a .tab file.\n\nThe format of the NOAA files looks something like:\n\n    Date Time, Water Level, Sigma, O, F, R, L, Quality \n    2014-09-24 00:00,2.236,0.007,0,0,0,0,v\n    2014-09-24 00:06,2.291,0.007,0,0,0,0,v\n    2014-09-24 00:12,2.331,0.003,0,0,0,0,v\n\nand TideMiner only actually cares about the first two data elements of each line (e.g. 2014-09-24 00:00 and 2.236 for the first of the data lines above). Non-data lines (blanks, headings, or anything else the program can read) are silently skipped. The key aspects of this format are\n   - the time is in the form of 'YYYY-MM-DD HH:MI' (Excel format code is yyyy-mm-dd hh:mm)\n   - the time is separated by a comma from the elevation\n   - the elevation is given as a decimal number\n   - one time-elevation pair per line\n   - file name ends in .csv\nWhile TideMiner is designed to work with the data from NOAA, you can construct your own tide elevation files as long as the formatting constraints are met.\n\nThe elevations for which flood duration are to be calculated are entered one per box - this means that a given analysis can only check up to 12 elevations at a time. The analysis silently skips any elevation that it cannot read - if you're having problems in this area double check your elevation numbers (e.g. make sure your decimal markers are periods and not commas, make sure the minus sign preceds the decimal for a negative elevation, make sure there are zeroes and not o's, etc.).\n\nThe system TideMiner uses for analysis is:\n1. convert the tide elevation entries into a series of intervals, each with a start and end time and a duration\n2. run through the intervals, and for each one check each elevation\n3. if the interval heights are all above the elevation, increase the elevation's flood duration, otherwise\n4. if the interval spans the elevation, increase the elevations duration by an appropriate fraction of the interval's duration, toggle the elevations flood state, and if necessary increment its flood count\n\nFor 'appropriate fraction' above, that's most easily explained by example. Imagine an interval with a start height of 1, and end height of 5, and a duration of 8. If this interval is checked against an elevation of 2 then we use the interval height span of 4 and the elevation-interval max difference of 3 (= 5 - 2) to get a fraction of 3/4, which is multiplied by the interval duration of 8 to get 6. That 6 is then added to the elevation's flood duration.\n\nEssentially, the program models a straight, smooth transition between the begin and end height of each interval and interpolates the duration of intermediate values.");
        jTextAreaHelpText.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextAreaHelpText);

        jButtonCloseHelp.setText("Close");
        jButtonCloseHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseHelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(747, Short.MAX_VALUE)
                .addComponent(jButtonCloseHelp)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCloseHelp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCloseHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseHelpActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCloseHelpActionPerformed

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
            java.util.logging.Logger.getLogger(HelpWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HelpWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HelpWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HelpWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HelpWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCloseHelp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaHelpText;
    // End of variables declaration//GEN-END:variables
}
