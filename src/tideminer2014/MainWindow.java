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
import java.time.Duration;
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

    private JFileChooser fileChooser;

    private final Color loadSignifierColorNONE = new Color(153, 51, 0);
    private final Color loadSignifierColorLOADED = new Color(0, 102, 51);

    private JTextField[] elevations = new JTextField[12];
    private JLabel[] floodCountLabels = new JLabel[12];
    private JLabel[] floodDurationLabels = new JLabel[12];
        
    private FileNameExtensionFilter delimitedFilesFilter;// = new FileNameExtensionFilter("Delimited files", "tab", "csv");
//    FileFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
// JFileChooser fileChooser = ...;
// fileChooser.addChoosableFileFilter(filter);
    
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
        this.setBounds(100,100, this.getWidth(), this.getHeight());
        
        helpWindow = new HelpWindow();
        helpWindow.setBounds(this.getX()+50, this.getY()+50, helpWindow.getWidth(), helpWindow.getHeight());
        
        aboutWindow = new AboutWindow();
        aboutWindow.setBounds(this.getX()+75, this.getY()+75, aboutWindow.getWidth(), aboutWindow.getHeight());
        
        this.tideIntervals = new ArrayList(200);
        this.elevationQueries = new ArrayList(200);
        
        this.delimitedFilesFilter = new FileNameExtensionFilter("Delimited files", "tab", "csv");
        this.fileChooser = new JFileChooser();
        this.fileChooser.addChoosableFileFilter(this.delimitedFilesFilter);
        this.fileChooser.setFileFilter(delimitedFilesFilter);
        
        this.elevations[0]          = this.jTextField_e01;
        this.floodCountLabels[0]    = this.jLabel_e01_flood_count;
        this.floodDurationLabels[0] = this.jLabel_e01_flood_duration;

        this.elevations[1]          = this.jTextField_e02;
        this.floodCountLabels[1]    = this.jLabel_e02_flood_count;
        this.floodDurationLabels[1] = this.jLabel_e02_flood_duration;

        this.elevations[2]          = this.jTextField_e03;
        this.floodCountLabels[2]    = this.jLabel_e03_flood_count;
        this.floodDurationLabels[2] = this.jLabel_e03_flood_duration;

        this.elevations[3]          = this.jTextField_e04;
        this.floodCountLabels[3]    = this.jLabel_e04_flood_count;
        this.floodDurationLabels[3] = this.jLabel_e04_flood_duration;

        this.elevations[4]          = this.jTextField_e05;
        this.floodCountLabels[4]    = this.jLabel_e05_flood_count;
        this.floodDurationLabels[4] = this.jLabel_e05_flood_duration;

        this.elevations[5]          = this.jTextField_e06;
        this.floodCountLabels[5]    = this.jLabel_e06_flood_count;
        this.floodDurationLabels[5] = this.jLabel_e06_flood_duration;

        this.elevations[6]          = this.jTextField_e07;
        this.floodCountLabels[6]    = this.jLabel_e07_flood_count;
        this.floodDurationLabels[6] = this.jLabel_e07_flood_duration;

        this.elevations[7]          = this.jTextField_e08;
        this.floodCountLabels[7]    = this.jLabel_e08_flood_count;
        this.floodDurationLabels[7] = this.jLabel_e08_flood_duration;

        this.elevations[8]          = this.jTextField_e09;
        this.floodCountLabels[8]    = this.jLabel_e09_flood_count;
        this.floodDurationLabels[8] = this.jLabel_e09_flood_duration;

        this.elevations[9]          = this.jTextField_e10;
        this.floodCountLabels[9]    = this.jLabel_e10_flood_count;
        this.floodDurationLabels[9] = this.jLabel_e10_flood_duration;

        this.elevations[10]          = this.jTextField_e11;
        this.floodCountLabels[10]    = this.jLabel_e11_flood_count;
        this.floodDurationLabels[10] = this.jLabel_e11_flood_duration;

        this.elevations[11]          = this.jTextField_e12;
        this.floodCountLabels[11]    = this.jLabel_e12_flood_count;
        this.floodDurationLabels[11] = this.jLabel_e12_flood_duration;
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
        jTextField_e01 = new javax.swing.JTextField();
        jTextField_e02 = new javax.swing.JTextField();
        jTextField_e03 = new javax.swing.JTextField();
        jTextField_e04 = new javax.swing.JTextField();
        jTextField_e05 = new javax.swing.JTextField();
        jTextField_e06 = new javax.swing.JTextField();
        jTextField_e07 = new javax.swing.JTextField();
        jTextField_e08 = new javax.swing.JTextField();
        jTextField_e09 = new javax.swing.JTextField();
        jTextField_e10 = new javax.swing.JTextField();
        jTextField_e11 = new javax.swing.JTextField();
        jTextField_e12 = new javax.swing.JTextField();
        jButtonAnalyze = new javax.swing.JButton();
        jButtonLoad = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_e01_flood_duration = new javax.swing.JLabel();
        jLabel_e01_flood_count = new javax.swing.JLabel();
        jLabel_e02_flood_count = new javax.swing.JLabel();
        jLabel_e02_flood_duration = new javax.swing.JLabel();
        jLabel_e03_flood_count = new javax.swing.JLabel();
        jLabel_e03_flood_duration = new javax.swing.JLabel();
        jLabel_e04_flood_count = new javax.swing.JLabel();
        jLabel_e04_flood_duration = new javax.swing.JLabel();
        jLabel_e05_flood_count = new javax.swing.JLabel();
        jLabel_e05_flood_duration = new javax.swing.JLabel();
        jLabel_e06_flood_count = new javax.swing.JLabel();
        jLabel_e06_flood_duration = new javax.swing.JLabel();
        jLabel_e07_flood_count = new javax.swing.JLabel();
        jLabel_e07_flood_duration = new javax.swing.JLabel();
        jLabel_e08_flood_count = new javax.swing.JLabel();
        jLabel_e08_flood_duration = new javax.swing.JLabel();
        jLabel_e09_flood_count = new javax.swing.JLabel();
        jLabel_e09_flood_duration = new javax.swing.JLabel();
        jLabel_e10_flood_count = new javax.swing.JLabel();
        jLabel_e10_flood_duration = new javax.swing.JLabel();
        jLabel_e11_flood_count = new javax.swing.JLabel();
        jLabel_e11_flood_duration = new javax.swing.JLabel();
        jLabel_e12_flood_count = new javax.swing.JLabel();
        jLabel_e12_flood_duration = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaResults = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabelLoadSignifier = new javax.swing.JLabel();
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
        setMinimumSize(new java.awt.Dimension(962, 535));
        setPreferredSize(new java.awt.Dimension(962, 535));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("2. Enter Elevations");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 81, 177, -1));

        jTextField_e01.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(jTextField_e01, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 111, -1, -1));

        jTextField_e02.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(jTextField_e02, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 137, -1, -1));

        jTextField_e03.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(jTextField_e03, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 163, -1, -1));

        jTextField_e04.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(jTextField_e04, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 189, -1, -1));

        jTextField_e05.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(jTextField_e05, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 215, -1, -1));

        jTextField_e06.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(jTextField_e06, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 241, -1, -1));

        jTextField_e07.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(jTextField_e07, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 267, -1, -1));

        jTextField_e08.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(jTextField_e08, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 293, -1, -1));

        jTextField_e09.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(jTextField_e09, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 319, -1, -1));

        jTextField_e10.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(jTextField_e10, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 345, -1, -1));

        jTextField_e11.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(jTextField_e11, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 371, -1, -1));

        jTextField_e12.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(jTextField_e12, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 397, -1, -1));

        jButtonAnalyze.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jButtonAnalyze.setText("3. Analyze");
        jButtonAnalyze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnalyzeActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAnalyze, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 435, 177, -1));

        jButtonLoad.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jButtonLoad.setText("1. Load Tide Data");
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("4. Inspect Results");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 51, 183, -1));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setText("# floods");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 87, -1, -1));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setText("time flooded");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 87, -1, -1));

        jLabel_e01_flood_duration.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e01_flood_duration.setText("time flooded");
        getContentPane().add(jLabel_e01_flood_duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 112, -1, -1));

        jLabel_e01_flood_count.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e01_flood_count.setText("# floods");
        getContentPane().add(jLabel_e01_flood_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 112, -1, -1));

        jLabel_e02_flood_count.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e02_flood_count.setText("# floods");
        getContentPane().add(jLabel_e02_flood_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 138, -1, -1));

        jLabel_e02_flood_duration.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e02_flood_duration.setText("time flooded");
        getContentPane().add(jLabel_e02_flood_duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 138, -1, -1));

        jLabel_e03_flood_count.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e03_flood_count.setText("# floods");
        getContentPane().add(jLabel_e03_flood_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 164, -1, -1));

        jLabel_e03_flood_duration.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e03_flood_duration.setText("time flooded");
        getContentPane().add(jLabel_e03_flood_duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 164, -1, -1));

        jLabel_e04_flood_count.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e04_flood_count.setText("# floods");
        getContentPane().add(jLabel_e04_flood_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 190, -1, -1));

        jLabel_e04_flood_duration.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e04_flood_duration.setText("time flooded");
        getContentPane().add(jLabel_e04_flood_duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, -1));

        jLabel_e05_flood_count.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e05_flood_count.setText("# floods");
        getContentPane().add(jLabel_e05_flood_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 216, -1, -1));

        jLabel_e05_flood_duration.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e05_flood_duration.setText("time flooded");
        getContentPane().add(jLabel_e05_flood_duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 216, -1, -1));

        jLabel_e06_flood_count.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e06_flood_count.setText("# floods");
        getContentPane().add(jLabel_e06_flood_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 242, -1, -1));

        jLabel_e06_flood_duration.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e06_flood_duration.setText("time flooded");
        getContentPane().add(jLabel_e06_flood_duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 242, -1, -1));

        jLabel_e07_flood_count.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e07_flood_count.setText("# floods");
        getContentPane().add(jLabel_e07_flood_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 268, -1, -1));

        jLabel_e07_flood_duration.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e07_flood_duration.setText("time flooded");
        getContentPane().add(jLabel_e07_flood_duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 268, -1, -1));

        jLabel_e08_flood_count.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e08_flood_count.setText("# floods");
        getContentPane().add(jLabel_e08_flood_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 294, -1, -1));

        jLabel_e08_flood_duration.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e08_flood_duration.setText("time flooded");
        getContentPane().add(jLabel_e08_flood_duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 294, -1, -1));

        jLabel_e09_flood_count.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e09_flood_count.setText("# floods");
        getContentPane().add(jLabel_e09_flood_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 320, -1, -1));

        jLabel_e09_flood_duration.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e09_flood_duration.setText("time flooded");
        getContentPane().add(jLabel_e09_flood_duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        jLabel_e10_flood_count.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e10_flood_count.setText("# floods");
        getContentPane().add(jLabel_e10_flood_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 346, -1, -1));

        jLabel_e10_flood_duration.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e10_flood_duration.setText("time flooded");
        getContentPane().add(jLabel_e10_flood_duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 346, -1, -1));

        jLabel_e11_flood_count.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e11_flood_count.setText("# floods");
        getContentPane().add(jLabel_e11_flood_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 372, -1, -1));

        jLabel_e11_flood_duration.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e11_flood_duration.setText("time flooded");
        getContentPane().add(jLabel_e11_flood_duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 372, -1, -1));

        jLabel_e12_flood_count.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e12_flood_count.setText("# floods");
        getContentPane().add(jLabel_e12_flood_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 398, -1, -1));

        jLabel_e12_flood_duration.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel_e12_flood_duration.setText("time flooded");
        getContentPane().add(jLabel_e12_flood_duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 398, -1, -1));

        jButtonSave.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jButtonSave.setText("5. Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 435, 183, -1));

        jTextAreaResults.setColumns(20);
        jTextAreaResults.setRows(5);
        jScrollPane1.setViewportView(jTextAreaResults);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(459, 81, 473, 336));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("or 5. Copy (& Paste)");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 51, 474, -1));

        jLabelLoadSignifier.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        jLabelLoadSignifier.setForeground(new java.awt.Color(153, 51, 0));
        jLabelLoadSignifier.setText("no tide data loaded");
        getContentPane().add(jLabelLoadSignifier, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 11, 739, -1));

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
        try {
            int result = this.fileChooser.showOpenDialog(this);
            if (result != JFileChooser.APPROVE_OPTION) {
                return;
            }
            // user selects a file
            File selectedFile = fileChooser.getSelectedFile();
//            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            
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
            if (sc.hasNextLine()) {
                fileLinePrev = sc.nextLine();
                while (sc.hasNextLine()) {
                    fileLineCur = sc.nextLine();
                    TideInterval ti = TideInterval.buildFromNoaaLines(fileLinePrev, fileLineCur);
                    if (ti.isValid()) {
                        this.tideIntervals.add(ti);
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
        }
    }
    
    private void updateLoadSignifier() {
        if (this.tideIntervals.isEmpty()) {
            this.jLabelLoadSignifier.setText("no tide data loaded");
            this.jLabelLoadSignifier.setForeground(this.loadSignifierColorNONE);
        } else {
            String signifierText = "tide data loaded: ";
            signifierText += Integer.toString(this.tideIntervals.size())+" intervals, ";
            signifierText += "from " + ((TideInterval) tideIntervals.get(0)).getBeginTime().toString() + " to " + ((TideInterval) tideIntervals.get(tideIntervals.size()-1)).getEndTime().toString();
            this.jLabelLoadSignifier.setText(signifierText);
            this.jLabelLoadSignifier.setForeground(this.loadSignifierColorLOADED);
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
    
    private void handleAnalysis() {
        if (this.tideIntervals.isEmpty()) {
            return;
        }
        
        this.elevationQueries.clear();

        this.minTideHeight = 10000;
        this.maxTideHeight = -10000;
        for (Object tiBase : this.tideIntervals) {
            TideInterval ti = (TideInterval) tiBase;
            if (this.maxTideHeight < ti.getBeginHeight()) {
                this.maxTideHeight = ti.getBeginHeight();
            }
            if (this.minTideHeight > ti.getBeginHeight()) {
                this.minTideHeight = ti.getBeginHeight();
            }
            if (this.maxTideHeight < ti.getEndHeight()) {
                this.maxTideHeight = ti.getEndHeight();
            }
            if (this.minTideHeight > ti.getEndHeight()) {
                this.minTideHeight = ti.getEndHeight();
            }
        }
        
        this.midEq = new ElevationQuery((maxTideHeight+minTideHeight)/2);
        this.submergedEq = new ElevationQuery(minTideHeight-1);
        this.submergedEq.setIsFlooded(true);
        
        // shift empty elevation entries up and build the elevation queries from jTextField_e01 through _e12
        int idxOfEmpty = -1;
        for (int i=0;i<12;i++) {
            String elevStr = this.elevations[i].getText().trim();
            if ((elevStr.equals("")) && (idxOfEmpty == -1)) {
                idxOfEmpty = i;
            } else {
                if (! elevStr.equals("")) {
                    if (idxOfEmpty >= 0) {
                        this.elevations[i].setText("");
                        this.elevations[idxOfEmpty].setText(elevStr);
                        idxOfEmpty++;
                    }
                    try {
                        this.elevationQueries.add(new ElevationQuery(Double.parseDouble(elevStr)));
                    }
                    catch (java.lang.NumberFormatException exc) {
                        System.out.println("BAD ELEVATION: "+elevStr);
                    }
                }
            }
        }
        
        // set the initial flood state
        TideInterval firstTi = (TideInterval) this.tideIntervals.get(0);
        for (Object eqBase : this.elevationQueries) {
            ElevationQuery eq = (ElevationQuery) eqBase;
            if (eq.getHeight() < firstTi.getBeginHeight()) {
                eq.setIsFlooded(true);
            }
        }
        if (this.midEq.getHeight() < firstTi.getBeginHeight()) {
            this.midEq.setIsFlooded(true);
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

    private void updateAnalysisResults() {
        // quick visual
        for (int i=0; i<this.elevationQueries.size(); i++) {
            ElevationQuery eq = (ElevationQuery) this.elevationQueries.get(i);
            this.floodCountLabels[i].setText(Integer.toString(eq.getFloodCount()));
            String durString = Long.toString(eq.getFloodDuration().toHours())+" hr, "+Long.toString(eq.getFloodDuration().toMinutes() - (eq.getFloodDuration().toHours()*60))+" min";
            this.floodDurationLabels[i].setText(durString);
        }
        for (int j=this.elevationQueries.size(); j<12; j++) {
            this.floodCountLabels[j].setText("# floods");
            this.floodDurationLabels[j].setText("time flooded");
        }
        
        // copy-and-paste-able
        this.jTextAreaResults.setText(this.getAnalysisResultsAsTabDelimited());
    }
    
    private String getAnalysisResultsAsTabDelimited() {
        // copy-and-paste-able
        String tabDelimitedResults = "ELEV\t# EXP/FLD\tDUR. AS SEC\tDUR. AS MIN\tDUR. AS HR\n";
        long eqSecs = this.submergedEq.getFloodDuration().getSeconds();
        double eqMin = (double)eqSecs/60;
        double eqHr = (double)eqSecs/3600;
        String line = "SUBMERGED\t"+
                Integer.toString(this.submergedEq.getFloodCount())+"\t"+
                Long.toString(eqSecs)+"\t"+
                Long.toString((long)(eqMin))+"\t"+
                decimalFormat2Places.format(eqHr)+"\n";
        tabDelimitedResults += line;

        for (int i=0; i<elevationQueries.size(); i++) {
            ElevationQuery eq = (ElevationQuery) elevationQueries.get(i);
            eqSecs = eq.getFloodDuration().getSeconds();
            eqMin = (double)eqSecs/60;
            eqHr = (double)eqSecs/3600;
            line = this.elevations[i].getText()+"\t"+
                    Integer.toString(eq.getFloodCount())+"\t"+
                    Long.toString(eqSecs)+"\t"+
                    Long.toString((long)(eqMin))+"\t"+
                    decimalFormat2Places.format(eqHr)+"\n";
            tabDelimitedResults += line;
        }
        
        tabDelimitedResults += "\n";
        tabDelimitedResults += "min tide\t"+this.decimalFormat2Places.format(this.minTideHeight)+"\n";
        tabDelimitedResults += "median tide\t"+this.decimalFormat2Places.format(this.midEq.getHeight())+"\n";
        tabDelimitedResults += "max tide\t"+this.decimalFormat2Places.format(this.maxTideHeight)+"\n";
        tabDelimitedResults += "\n";
        tabDelimitedResults += "median height floods\t"+ Integer.toString(this.midEq.getFloodCount())+"\n";        
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
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnalyze;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelLoadSignifier;
    private javax.swing.JLabel jLabel_e01_flood_count;
    private javax.swing.JLabel jLabel_e01_flood_duration;
    private javax.swing.JLabel jLabel_e02_flood_count;
    private javax.swing.JLabel jLabel_e02_flood_duration;
    private javax.swing.JLabel jLabel_e03_flood_count;
    private javax.swing.JLabel jLabel_e03_flood_duration;
    private javax.swing.JLabel jLabel_e04_flood_count;
    private javax.swing.JLabel jLabel_e04_flood_duration;
    private javax.swing.JLabel jLabel_e05_flood_count;
    private javax.swing.JLabel jLabel_e05_flood_duration;
    private javax.swing.JLabel jLabel_e06_flood_count;
    private javax.swing.JLabel jLabel_e06_flood_duration;
    private javax.swing.JLabel jLabel_e07_flood_count;
    private javax.swing.JLabel jLabel_e07_flood_duration;
    private javax.swing.JLabel jLabel_e08_flood_count;
    private javax.swing.JLabel jLabel_e08_flood_duration;
    private javax.swing.JLabel jLabel_e09_flood_count;
    private javax.swing.JLabel jLabel_e09_flood_duration;
    private javax.swing.JLabel jLabel_e10_flood_count;
    private javax.swing.JLabel jLabel_e10_flood_duration;
    private javax.swing.JLabel jLabel_e11_flood_count;
    private javax.swing.JLabel jLabel_e11_flood_duration;
    private javax.swing.JLabel jLabel_e12_flood_count;
    private javax.swing.JLabel jLabel_e12_flood_duration;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuInfo;
    private javax.swing.JMenuItem jMenuItemFileLoad;
    private javax.swing.JMenuItem jMenuItemFileQuit;
    private javax.swing.JMenuItem jMenuItemFileSave;
    private javax.swing.JMenuItem jMenuItemInfoAbout;
    private javax.swing.JMenuItem jMenuItemInfoHelp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextArea jTextAreaResults;
    private javax.swing.JTextField jTextField_e01;
    private javax.swing.JTextField jTextField_e02;
    private javax.swing.JTextField jTextField_e03;
    private javax.swing.JTextField jTextField_e04;
    private javax.swing.JTextField jTextField_e05;
    private javax.swing.JTextField jTextField_e06;
    private javax.swing.JTextField jTextField_e07;
    private javax.swing.JTextField jTextField_e08;
    private javax.swing.JTextField jTextField_e09;
    private javax.swing.JTextField jTextField_e10;
    private javax.swing.JTextField jTextField_e11;
    private javax.swing.JTextField jTextField_e12;
    // End of variables declaration//GEN-END:variables
}
