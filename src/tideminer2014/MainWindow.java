/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tideminer2014;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;


/*
TODO
    - figure out accurate tide cycle counting
        + from min and max elevations do cycle counts for 40, 50, and 60 % heights, and average those?
        + NOTE: FIX CYCLE COUNTING FOR ELEVATION QUERIES!!!!
            * current method doesn't really work... glitches on noisy data; figure out better peak/trough detection (look-ahead/behind?)
    - test data set for may1 - oct 1 by hour (odd min-max counting, among other things)
    - output as # cycles flooded, % cycles flooded, # duration flooded, % duration flooded
    - separate output line for totals / summaries
    - add excel format code to help doc
*/

/**
 *
 * @author Chris
 */
public class MainWindow extends javax.swing.JFrame {

    private ArrayList tideIntervals;
    private ArrayList elevationQueries;
    private ElevationQuery midEq;
    private ElevationQuery submergedEq;
    
    private double minTideHeight = 0;
    private double maxTideHeight = 0;
    
    private long numTidePeaks = 0;
    private long numTideTroughs = 0;
    private long totalTideSeconds = 0;

    private JFileChooser fileChooser;

    private final Color loadSignifierColorNONE = new Color(153, 51, 0);
    private final Color loadSignifierColorLOADED = new Color(0, 102, 51);

//    private ArrayList elevations;

    private FileNameExtensionFilter delimitedFilesFilter;
    
    DecimalFormat decimalFormat2Places = new DecimalFormat("#.00");
    
    HelpWindow helpWindow;
    AboutWindow aboutWindow;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        myInits();
    }

    private void myInits() {
        this.setBounds(100,100, 1025, 565);
        
        helpWindow = new HelpWindow();
        helpWindow.setBounds(this.getX()+50, this.getY()+50, helpWindow.getWidth(), helpWindow.getHeight());
        
        aboutWindow = new AboutWindow();
        aboutWindow.setBounds(this.getX()+75, this.getY()+75, aboutWindow.getWidth(), aboutWindow.getHeight());
        
        this.tideIntervals = new ArrayList(200);
        this.elevationQueries = new ArrayList(200);
//        this.elevations = new ArrayList(200);
        
        this.delimitedFilesFilter = new FileNameExtensionFilter("Delimited files", "tab", "csv");
        this.fileChooser = new JFileChooser();
        this.fileChooser.addChoosableFileFilter(this.delimitedFilesFilter);
        this.fileChooser.setFileFilter(delimitedFilesFilter);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButtonAnalyze = new javax.swing.JButton();
        jButtonLoad = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaResults = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabelMessages = new javax.swing.JLabel();
        jLabelLoadSignifier1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaElevations = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemFileLoad = new javax.swing.JMenuItem();
        jMenuItemFileSave = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemFileQuit = new javax.swing.JMenuItem();
        jMenuInfo = new javax.swing.JMenu();
        jMenuItemInfoAbout = new javax.swing.JMenuItem();
        jMenuItemInfoHelp = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(982, 535));
        setPreferredSize(new java.awt.Dimension(982, 535));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("2. Enter Elevations");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 177, -1));

        jButtonAnalyze.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jButtonAnalyze.setText("3. Analyze");
        jButtonAnalyze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnalyzeActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAnalyze, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 180, -1));

        jButtonLoad.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jButtonLoad.setText("1. Load Tide Data");
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        jButtonSave.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jButtonSave.setText("4. Save");
        jButtonSave.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        jTextAreaResults.setColumns(20);
        jTextAreaResults.setRows(5);
        jScrollPane1.setViewportView(jTextAreaResults);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 790, 330));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("or Copy (& Paste)");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 220, 30));

        jLabelMessages.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        jLabelMessages.setForeground(new java.awt.Color(204, 0, 0));
        getContentPane().add(jLabelMessages, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 450, 760, 30));

        jLabelLoadSignifier1.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        jLabelLoadSignifier1.setForeground(new java.awt.Color(153, 51, 0));
        jLabelLoadSignifier1.setText("no tide data loaded");
        getContentPane().add(jLabelLoadSignifier1, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 11, 770, -1));

        jTextAreaElevations.setColumns(20);
        jTextAreaElevations.setRows(5);
        jScrollPane2.setViewportView(jTextAreaElevations);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 180, 330));

        jMenuFile.setText("File");

        jMenuItemFileLoad.setText("Load Tide Data");
        jMenuItemFileLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFileLoadActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemFileLoad);

        jMenuItemFileSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemFileSave.setText("Save Analysis");
        jMenuItemFileSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFileSaveActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemFileSave);
        jMenuFile.add(jSeparator1);

        jMenuItemFileQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemFileQuit.setText("Quit");
        jMenuItemFileQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFileQuitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemFileQuit);

        jMenuBar1.add(jMenuFile);

        jMenuInfo.setText("Info");

        jMenuItemInfoAbout.setText("About");
        jMenuItemInfoAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInfoAboutActionPerformed(evt);
            }
        });
        jMenuInfo.add(jMenuItemInfoAbout);

        jMenuItemInfoHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItemInfoHelp.setText("Help");
        jMenuItemInfoHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInfoHelpActionPerformed(evt);
            }
        });
        jMenuInfo.add(jMenuItemInfoHelp);

        jMenuBar1.add(jMenuInfo);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemFileQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFileQuitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemFileQuitActionPerformed

    private void jMenuItemFileLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFileLoadActionPerformed
        handleFileLoad(evt);
    }//GEN-LAST:event_jMenuItemFileLoadActionPerformed

    private void jMenuItemFileSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFileSaveActionPerformed
        handleSaveAnalysis();
    }//GEN-LAST:event_jMenuItemFileSaveActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        handleSaveAnalysis();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        handleFileLoad(evt);
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jButtonAnalyzeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnalyzeActionPerformed
        handleAnalysis();
    }//GEN-LAST:event_jButtonAnalyzeActionPerformed

    private void jMenuItemInfoHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInfoHelpActionPerformed
        this.helpWindow.setVisible(true);
    }//GEN-LAST:event_jMenuItemInfoHelpActionPerformed

    private void jMenuItemInfoAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInfoAboutActionPerformed
        this.aboutWindow.setVisible(true);
    }//GEN-LAST:event_jMenuItemInfoAboutActionPerformed
    
    private void handleFileLoad(java.awt.event.ActionEvent evt) {
        this.updateMessage("");
        int errorCount = 0;
        boolean messageShown = false;
        try {
            int result = this.fileChooser.showOpenDialog(this);
            if (result != JFileChooser.APPROVE_OPTION) {
                System.out.println("No file selected!");
                return;
            }
            // user selects a file
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            
            // open that file
            // read in the contents, building the arraylist of TideInterval objects in the process
            //    process 1 line at a time, passing to TideInterval string-based constructor w/ pervious line
            // fileLine1 = first line of file
            // while fileLine2 = next available line
            //   tideIntervals.add(TideInterval.buildFromNoaaLines(fileLine1, fileLine2));
            //   fileLine1 = fileLine2;
            Scanner sc = new Scanner(selectedFile);
            String fileLinePrev, fileLineCur;
            int counter = 0;
            this.tideIntervals.clear();
            if (sc.hasNextLine()) {
                fileLinePrev = sc.nextLine();
                while (sc.hasNextLine()) {
                    fileLineCur = sc.nextLine();
                    TideInterval ti = TideInterval.buildFromNoaaLines(fileLinePrev, fileLineCur);
                    if (ti.isValid()) {
                        this.tideIntervals.add(ti);
                    } else {
                        errorCount++;
                        if (errorCount > 10 && ! messageShown) {
                            messageShown = true;
                            this.updateMessage("Tide data problem like: "+ti.getMsg());
                        }
                    }
                    fileLinePrev = fileLineCur;
                    counter++;
//                    if (counter > 4) {
//                        System.exit(1);
//                    }
                }
            }

            this.updateLoadSignifier();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            errorCount++;
            if (errorCount > 10 && ! messageShown) {
                messageShown = true;
                this.updateMessage("File load problem like: "+ex.toString());
            }
        }
    }
    
    private void updateLoadSignifier() {
        if (this.tideIntervals.isEmpty()) {
            this.jLabelLoadSignifier1 .setText("no tide data loaded");
            this.jLabelLoadSignifier1.setForeground(this.loadSignifierColorNONE);
        } else {
            String signifierText = "tide data loaded: ";
            signifierText += Integer.toString(this.tideIntervals.size())+" intervals, ";
            signifierText += "from " + ((TideInterval) tideIntervals.get(0)).getBeginTime().toString() + " to " + ((TideInterval) tideIntervals.get(tideIntervals.size()-1)).getEndTime().toString();
            this.jLabelLoadSignifier1.setText(signifierText);
            this.jLabelLoadSignifier1.setForeground(this.loadSignifierColorLOADED);
        }
    }

    private void handleSaveAnalysis() {
        if (this.tideIntervals.isEmpty() || this.elevationQueries.isEmpty()) {
            return;
        }
        
        // open file dialog & get results
        // open that file as a .tab
        // write to it this.getAnalysisResultsAsTabDelimited()
        // close the file
        int result = this.fileChooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File selectedFile = fileChooser.getSelectedFile();
//        selectedFile.
        
//        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        if (! selectedFile.getAbsolutePath().endsWith(".tab")) {
            selectedFile = new File(selectedFile+".tab");
        }
//        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(selectedFile));
            out.write(getAnalysisResultsAsTabDelimited());
            out.close();
        } catch (IOException ex) {
            System.out.println("error writing to "+selectedFile.getAbsolutePath());
        }
    }
    
    private void markAndCountTideExtremes() {
        if (this.tideIntervals.isEmpty()) {
            return;
        }
        
        this.numTidePeaks = 0;
        this.numTideTroughs = 0;
        this.totalTideSeconds = 0;
        
        // general process is:
        // cycle through all the intervals, and for each compare its average height to those ahead and behind (within a certain span)
        // if this interval is the highest, mark it as a cycle peak
        // if this interval is the lowest, mark it as a cycle trough
        // NOTE: begin and end intervals simply don't check on non-existent intervals
        
        // this is the duration forward and backward to check
        long comparisonSpanSeconds = 3600; // 1 hour
        long intervalSizeSeconds = ((TideInterval)(this.tideIntervals.get(0))).getDuration().getSeconds();
        int comparisonSpanMaxIntervals = Math.max(1, Math.round((float)comparisonSpanSeconds/(float)intervalSizeSeconds));
        
        int lookBackIntervals; // = 0;
        int lookAheadIntervals; // = Math.min(comparisonSpanMaxIntervals, this.tideIntervals.size());
        
        // now do the checking, adjusting the lookBack and lookAhead as we progress through the tideIntervals
        int tiSize = this.tideIntervals.size();
        for (int iCur=0; iCur<tiSize; iCur++) {
            TideInterval ti = (TideInterval)(this.tideIntervals.get(iCur));
                        
            double basis = ti.getAverageHeight();
            boolean isPeak = true;
            boolean isTrough = true;
            
            // update intervals
            lookBackIntervals = Math.min(comparisonSpanMaxIntervals, iCur);
            lookAheadIntervals = Math.min(comparisonSpanMaxIntervals, this.tideIntervals.size()-1-iCur);

            // check earlier
            for (int iPre = 1; (iPre<=lookBackIntervals) && (isPeak || isTrough); iPre++) {
                double check = ((TideInterval)(this.tideIntervals.get(iCur-iPre))).getAverageHeight();
                if (check > basis) {
                    isPeak = false;
                }
                if (check < basis) {
                    isTrough = false;
                }
            }

            // check later
            for (int iPost = 1; (iPost<=lookAheadIntervals) && (isPeak || isTrough); iPost++) {
                double check = ((TideInterval)(this.tideIntervals.get(iCur+iPost))).getAverageHeight();
                if (check > basis) {
                    isPeak = false;
                }
                if (check < basis) {
                    isTrough = false;
                }
            }

            // set state
            ti.setPeak(isPeak);
            ti.setTrough(isTrough);
            
            if (isPeak) { this.numTidePeaks++; }
            if (isTrough) { this.numTideTroughs++; }
            this.totalTideSeconds += ti.getDuration().getSeconds();
        }
    }
    
    private void handleAnalysis() {
        if (this.tideIntervals.isEmpty()) {
            return;
        }
        
        this.markAndCountTideExtremes();
        
        this.updateMessage("");
        int errorCount = 0;
        boolean messageShown = false;
                
        this.elevationQueries.clear();

        TideInterval firstTi = (TideInterval) this.tideIntervals.get(0);
        this.minTideHeight = firstTi.getMinHeight();
        this.maxTideHeight = firstTi.getMaxHeight();
        
        for (Object tiBase : this.tideIntervals) {
            TideInterval ti = (TideInterval) tiBase;
            if (ti.getMaxHeight() > this.maxTideHeight) {
                this.maxTideHeight = ti.getMaxHeight();
            }
            if (ti.getMinHeight() < this.minTideHeight) {
                this.minTideHeight = ti.getMinHeight();
            }
        }
        
        this.midEq = new ElevationQuery((maxTideHeight+minTideHeight)/2);
        this.submergedEq = new ElevationQuery(minTideHeight-1);
        
        this.elevationQueries = new ArrayList(200);
        String[] elevTexts = this.jTextAreaElevations.getText().split("\n");
        for (String elevText : elevTexts) {
            String toParse = elevText.trim();
            if (! toParse.isEmpty()) {
                try {
                    double e = Double.parseDouble(toParse);
                    this.elevationQueries.add(new ElevationQuery(e));
                }
                catch (NumberFormatException exc) {
                    errorCount++;
                    if (errorCount > 4 && ! messageShown) {
                        messageShown = true;
                        this.updateMessage("Elevation parsing problem like: "+exc.toString());
                    }
                }
            }
        }
        
        // run through the tideIntervals, processing each interval for each elevationQuery
        for (Object tiBase : this.tideIntervals) {
            TideInterval ti = (TideInterval) tiBase;
            for (Object eqBase : this.elevationQueries) {
                ElevationQuery eq = (ElevationQuery) eqBase;
                eq.processInterval(ti);
            }
            this.midEq.processInterval(ti);
            this.submergedEq.processInterval(ti);
        }
        
        updateAnalysisResults();
    }

    private void updateMessage(String s) {
        this.jLabelMessages.setText(s);
    } 
    
    private void updateAnalysisResults() {
        // quick visual
        for (Object elevationQuerie : this.elevationQueries) {
            ElevationQuery eq = (ElevationQuery) elevationQuerie;
            String durString = Long.toString(eq.getFloodDuration().toHours())+" hr, "+Long.toString(eq.getFloodDuration().toMinutes() - (eq.getFloodDuration().toHours()*60))+" min";
        }
        
        // copy-and-paste-able
        this.jTextAreaResults.setText(this.getAnalysisResultsAsTabDelimited());
    }
    
    private String getAnalysisResultsAsTabDelimited() {
        // copy-and-paste-able
        String tabDelimitedResults = "ELEV\t% FLOODS\t# FLOODS\t% FL DUR\tFL. DUR. SEC\tFL. DUR. MIN\tFL. DUR. HR\t% EXPOSES\t# EXPOSES\n";
        long eqSecs = this.submergedEq.getFloodDuration().getSeconds();
        double eqMin = (double)eqSecs/60;
        double eqHr = (double)eqSecs/3600;
        String line;

        for (Object elevationQuerie : elevationQueries) {
            ElevationQuery eq = (ElevationQuery) elevationQuerie;
            eqSecs = eq.getFloodDuration().getSeconds();
            eqMin = (double)eqSecs/60;
            eqHr = (double)eqSecs/3600;
            line =  this.decimalFormat2Places.format(eq.getHeight())+"\t"+
                    this.decimalFormat2Places.format((double)eq.getFloodCount()/(double)this.numTidePeaks)+"\t"+
                    Integer.toString(eq.getFloodCount())+"\t"+
                    this.decimalFormat2Places.format((double)eqSecs/(double)this.totalTideSeconds)+"\t"+                    
                    Long.toString(eqSecs)+"\t"+
                    Long.toString((long)(eqMin))+"\t"+
                    decimalFormat2Places.format(eqHr)+"\t"+
                    this.decimalFormat2Places.format((double)eq.getExposureCount()/(double)this.numTideTroughs)+"\t"+
                    Integer.toString(eq.getExposureCount())+"\n";
            tabDelimitedResults += line;
        }
        
        tabDelimitedResults += "\n";
        tabDelimitedResults += "MIN TIDE HEIGHT:\t"+decimalFormat2Places.format(this.minTideHeight)+"\n";
        tabDelimitedResults += "MAX TIDE HEIGHT:\t"+decimalFormat2Places.format(this.maxTideHeight) +"\n";
        tabDelimitedResults += "\n";
        tabDelimitedResults += "TOTAL FLOODS:\t"+this.numTidePeaks+"\n";
        tabDelimitedResults += "TOTAL EXPOSURES:\t"+this.numTideTroughs +"\n";
        tabDelimitedResults += "\n";
        tabDelimitedResults += "TOTAL DURATION (SEC):\t"+this.totalTideSeconds+"\n";
        tabDelimitedResults += "TOTAL DURATION (MIN):\t"+decimalFormat2Places.format((double)this.totalTideSeconds/60)+"\n";
        tabDelimitedResults += "TOTAL DURATION (HR):\t"+decimalFormat2Places.format((double)this.totalTideSeconds/3600)+"\n";

//        tabDelimitedResults += "min tide\t"+this.decimalFormat2Places.format(this.minTideHeight)+"\n";
//        tabDelimitedResults += "median tide\t"+this.decimalFormat2Places.format(this.midEq.getHeight())+"\n";
//        tabDelimitedResults += "max tide\t"+this.decimalFormat2Places.format(this.maxTideHeight)+"\n";
//        tabDelimitedResults += "\n";
//        tabDelimitedResults += "median height floods\t"+ Integer.toString(this.midEq.getFloodCount())+"\n";        
        tabDelimitedResults += "\n";

        return tabDelimitedResults;
    }
    
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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("TideMiner started");
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnalyze;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelLoadSignifier1;
    private javax.swing.JLabel jLabelMessages;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuInfo;
    private javax.swing.JMenuItem jMenuItemFileLoad;
    private javax.swing.JMenuItem jMenuItemFileQuit;
    private javax.swing.JMenuItem jMenuItemFileSave;
    private javax.swing.JMenuItem jMenuItemInfoAbout;
    private javax.swing.JMenuItem jMenuItemInfoHelp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextArea jTextAreaElevations;
    private javax.swing.JTextArea jTextAreaResults;
    // End of variables declaration//GEN-END:variables
}
