package cpu;

import Info.About;
import Info.Instruction;
import Items.Cell;
import Items.Job;
import Items.MyTable;
import Items.Queue;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

/**
 * Main page of the programs
 */
public class Face extends javax.swing.JFrame {

    /**
     * Creates new frame and start the thread
     */    
    public Face() {
        initComponents();
        stopBttn.setEnabled(false);
        restartBttn.setEnabled(false);
        setLocationRelativeTo(null);
        MainQueue.createNew(8);  // create random data main queue
        updateTable(MainQueue.get()); // view initial jobs data in the table 
        thread.start();
    }
    
    /**
     * set assigned jobs by the user to the Main queue and update the table
     * @param queue queue of jobs
     */
    public void setAssignedQueue(Queue queue)
    {
        MainQueue.add(queue);    
        updateTable(MainQueue.get());
    }
    
    private MyTable myTable;  // table on the GUI
    /**
     * update table data
     * @param queue queue of jobs to be shown on the table  
     */
    private void updateTable(Queue queue)
    {
        myTable = new MyTable(queue);
        table.setModel(myTable);  
    }
    
    // <editor-fold defaultstate="collapsed" desc="Visuals" >
    
    /**
     * view CPU, Gantt and readyQueue visuals
     * @param job current job processed in the simulation
     * @param readyQueue  current ready queue in the simulation
     */
    private void viewVisuals(Job job , Queue readyQueue)
    {
        cpuVisual(job, Simulation.Time);
        showGantt(job, Simulation.Time);
        showReadyQueue(readyQueue);
    }
    
    /**
     * clear and reset CPU, Gantt and ReadyQueue visuals
     */
    private void clearVisuals()
    {
        cpuClear();
        clearGantt();
        clearReadyQueue();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="cpu visual" >
    
    private static int idleTime =0; // CPU idle time
    /**
     * view current data of the CPU during the simulation
     * @param job current job to be processed by the CPU
     * @param time current time of the simulation
     */
    private void cpuVisual(Job job , int time){
        if(job == null ) 
        {
            cpuCurrentJob.setText(" Idle " ); 
            idleTime++;
        }
        else 
        { 
            cpuCurrentJob.setText("JOB " + job.jobNumber);
        }
        cpuCurrentTime.setText(time + " -> " + (time +1));
        if(time != 0) 
        {
            cpuUtilize.setText(((time - idleTime) *100 / time) +"%");
        }
        else { cpuUtilize.setText(100 +"%"); }
    }
    
    /**
     * reset CPU visual data
     */
    private void cpuClear(){
        cpuCurrentJob.setText("Idle");
        cpuCurrentTime.setText("0");
        cpuUtilize.setText("0%");
        idleTime = 0;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Charts" >
    /**
     * update and show Gantt chart
     * @param job job to be added to the chart
     * @param time time of the simulation
     */
    private void showGantt(Job job , int time){
       GanttChart.addJob(job, time);
       addToGUI(GanttChart.List);
    }
    
    /**
     * remove all the elements from the Gantt chart
     */
    private void clearGantt(){
        removeFromGUI(GanttChart.List);
        GanttChart.clear();
    }
    
    /**
     * update and show ready queue chart
     * @param list list of jobs to be represented
     */
    private void showReadyQueue(Queue list){
        clearReadyQueue();
        ReadyChart.update(list);
        addToGUI(ReadyChart.List);
    }
    
    /**
     * remove all the elements form the ready queue chart
     */
    private void clearReadyQueue(){
        removeFromGUI(ReadyChart.List);
        ReadyChart.clear();
     }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="paint elements on GUI" >
    
    /**
     * add list of cell elements to the GUI
     * @param list of element from the charts
     */
    private void addToGUI (ArrayList<Cell> list)
    {
        for(int i =0 ; i< list.size() ; i++)
        {
           add(list.get(i));
        }
        repaint();
    }
    
    /**
     * remove list of cell elements from the GUI
     * @param list list of elements to be removed from the GUI 
     */
    private void removeFromGUI ( ArrayList<Cell> list)
    {
        for(int i =0 ; i< list.size() ; i++)
        {
           remove(list.get(i)); 
        } 
        repaint();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Thread - Real Time Simulation" >
    Thread thread = new Thread(new Runnable (){
        @Override
        public void run () {
        while(true)
        {
            if(!Simulation.Finished && !Simulation.Stoped) // stops the simulation
            {
             nextStepBttnActionPerformed(null);  // press next step button
             delay();  // delay time after every step
            }    
        } 
    }});
    
    /**
     * responsible for the delay time between every step in the
     * simulation.
     */
    public void delay ()
    {
        int num = Integer.parseInt(simSpeed.getSelectedItem()+"");  // get the delay factor from GUI
        try {
            Thread.sleep(150 * num); // 150 is here by try and error
        } 
        catch (InterruptedException ex) {  
            Logger.getLogger(Face.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    // </editor-fold>
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBorder(BorderFactory.createTitledBorder("Data"));
        jLabel1 = new javax.swing.JLabel();
        numOfJobs = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        AlgorithmsMenu = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        simSpeed = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        quantum = new javax.swing.JComboBox();
        simulateBttn = new javax.swing.JButton();
        stopBttn = new javax.swing.JButton();
        nextStepBttn = new javax.swing.JButton();
        restartBttn = new javax.swing.JButton();
        anotherSimBttn = new javax.swing.JButton();
        finishBttn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cpuPanel = new javax.swing.JPanel();
        cpuPanel.setBorder(BorderFactory.createTitledBorder("CPU"));
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cpuCurrentJob = new javax.swing.JLabel();
        cpuCurrentTime = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cpuUtilize = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel4.setBorder(BorderFactory.createTitledBorder("Job Pool"));
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        Average = new javax.swing.JPanel();
        cpuPanel.setBorder(BorderFactory.createTitledBorder("CPU"));
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        AverWait = new javax.swing.JLabel();
        AverTurn = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CPU scheduale");
        setMinimumSize(new java.awt.Dimension(1053, 600));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(500, 250));
        jPanel1.setMinimumSize(new java.awt.Dimension(500, 250));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 250));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Num of Jobs");

        numOfJobs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        numOfJobs.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2", "3", "4", "5", "6", "7", "8" }));
        numOfJobs.setSelectedIndex(6);
        numOfJobs.setLightWeightPopupEnabled(false);
        numOfJobs.setMaximumSize(new java.awt.Dimension(30, 20));
        numOfJobs.setMinimumSize(new java.awt.Dimension(30, 20));
        numOfJobs.setPreferredSize(new java.awt.Dimension(30, 20));
        numOfJobs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numOfJobsActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Algorithm");

        AlgorithmsMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        AlgorithmsMenu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FCFS", "SJF", "STRF", "RR", "Priority1", "Priority2" }));
        AlgorithmsMenu.setMaximumSize(new java.awt.Dimension(65, 20));
        AlgorithmsMenu.setMinimumSize(new java.awt.Dimension(65, 20));
        AlgorithmsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlgorithmsMenuActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Sim speed");

        simSpeed.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        simSpeed.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2", "3", "4", "5", "6", "7", "8" }));
        simSpeed.setSelectedIndex(5);
        simSpeed.setMaximumSize(new java.awt.Dimension(65, 20));
        simSpeed.setMinimumSize(new java.awt.Dimension(65, 20));
        simSpeed.setPreferredSize(new java.awt.Dimension(65, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Quantum");

        quantum.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        quantum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2", "3", "4", "5", "6", "7", "8" }));

        simulateBttn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        simulateBttn.setText("Simulate");
        simulateBttn.setMaximumSize(new java.awt.Dimension(85, 25));
        simulateBttn.setMinimumSize(new java.awt.Dimension(85, 25));
        simulateBttn.setPreferredSize(new java.awt.Dimension(85, 25));
        simulateBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulateBttnActionPerformed(evt);
            }
        });

        stopBttn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        stopBttn.setText("Stop");
        stopBttn.setMaximumSize(new java.awt.Dimension(85, 25));
        stopBttn.setMinimumSize(new java.awt.Dimension(85, 25));
        stopBttn.setPreferredSize(new java.awt.Dimension(85, 25));
        stopBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopBttnActionPerformed(evt);
            }
        });

        nextStepBttn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nextStepBttn.setText("Next step");
        nextStepBttn.setMaximumSize(new java.awt.Dimension(95, 25));
        nextStepBttn.setMinimumSize(new java.awt.Dimension(95, 25));
        nextStepBttn.setPreferredSize(new java.awt.Dimension(95, 25));
        nextStepBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextStepBttnActionPerformed(evt);
            }
        });

        restartBttn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        restartBttn.setText("Restart");
        restartBttn.setMaximumSize(new java.awt.Dimension(80, 25));
        restartBttn.setMinimumSize(new java.awt.Dimension(80, 25));
        restartBttn.setPreferredSize(new java.awt.Dimension(80, 25));
        restartBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartBttnActionPerformed(evt);
            }
        });

        anotherSimBttn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        anotherSimBttn.setText("Start another simulation");
        anotherSimBttn.setMaximumSize(new java.awt.Dimension(185, 25));
        anotherSimBttn.setMinimumSize(new java.awt.Dimension(185, 25));
        anotherSimBttn.setPreferredSize(new java.awt.Dimension(185, 25));
        anotherSimBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anotherSimBttnActionPerformed(evt);
            }
        });

        finishBttn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        finishBttn.setText("Finish");
        finishBttn.setMaximumSize(new java.awt.Dimension(80, 25));
        finishBttn.setMinimumSize(new java.awt.Dimension(80, 25));
        finishBttn.setPreferredSize(new java.awt.Dimension(80, 25));
        finishBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishBttnActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Data");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(numOfJobs, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(simulateBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(AlgorithmsMenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stopBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(anotherSimBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(finishBttn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(simSpeed, javax.swing.GroupLayout.Alignment.LEADING, 0, 95, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nextStepBttn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(restartBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(quantum, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numOfJobs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlgorithmsMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(simSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stopBttn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(simulateBttn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextStepBttn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(restartBttn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anotherSimBttn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finishBttn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cpuPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cpuPanel.setMaximumSize(new java.awt.Dimension(337, 111));
        cpuPanel.setMinimumSize(new java.awt.Dimension(337, 111));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Current job");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Current Time");

        cpuCurrentJob.setBackground(new java.awt.Color(255, 255, 255));
        cpuCurrentJob.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        cpuCurrentJob.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cpuCurrentJob.setText("Idle");

        cpuCurrentTime.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        cpuCurrentTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cpuCurrentTime.setText("0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("CPU");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Utilization");

        cpuUtilize.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        cpuUtilize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cpuUtilize.setText("0%");

        javax.swing.GroupLayout cpuPanelLayout = new javax.swing.GroupLayout(cpuPanel);
        cpuPanel.setLayout(cpuPanelLayout);
        cpuPanelLayout.setHorizontalGroup(
            cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cpuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(cpuPanelLayout.createSequentialGroup()
                        .addGroup(cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(cpuCurrentJob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cpuCurrentTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(cpuUtilize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        cpuPanelLayout.setVerticalGroup(
            cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cpuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addGap(20, 20, 20)
                .addGroup(cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(20, 20, 20)
                .addGroup(cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpuCurrentTime)
                    .addComponent(cpuCurrentJob)
                    .addComponent(cpuUtilize))
                .addGap(20, 20, 20))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setMaximumSize(new java.awt.Dimension(523, 257));
        jPanel4.setMinimumSize(new java.awt.Dimension(523, 257));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), null, null, null, null, null, null, null, null, null, null},
                { new Integer(2), null, null, null, null, null, null, null, null, null, null},
                { new Integer(3), null, null, null, null, null, null, null, null, null, null},
                { new Integer(4), null, null, null, null, null, null, null, null, null, null},
                { new Integer(5), null, null, null, null, null, null, null, null, null, null},
                { new Integer(6), null, null, null, null, null, null, null, null, null, null},
                { new Integer(7), null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Arrive", "Burst", "Priority", "Start", "response", "Wait", "Remain", "Finish", "Turnaround", "%"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.getColumnModel().getColumn(6).setResizable(false);
        table.getColumnModel().getColumn(7).setResizable(false);
        table.getColumnModel().getColumn(8).setResizable(false);
        table.getColumnModel().getColumn(9).setResizable(false);
        table.getColumnModel().getColumn(10).setResizable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Job pool");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        Average.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Average.setMaximumSize(new java.awt.Dimension(337, 111));
        Average.setMinimumSize(new java.awt.Dimension(337, 111));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Waiting");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Turnaround");

        AverWait.setBackground(new java.awt.Color(255, 255, 255));
        AverWait.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        AverWait.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AverWait.setText("0");

        AverTurn.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        AverTurn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AverTurn.setText("0");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Average");

        javax.swing.GroupLayout AverageLayout = new javax.swing.GroupLayout(Average);
        Average.setLayout(AverageLayout);
        AverageLayout.setHorizontalGroup(
            AverageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AverageLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(AverageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(AverageLayout.createSequentialGroup()
                        .addGroup(AverageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(AverWait, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(AverageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AverTurn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AverageLayout.setVerticalGroup(
            AverageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AverageLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel13)
                .addGap(20, 20, 20)
                .addGroup(AverageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(AverageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AverTurn)
                    .addComponent(AverWait))
                .addGap(20, 20, 20))
        );

        jLabel14.setBackground(new java.awt.Color(255, 0, 0));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cpu/Untitled-1.png"))); // NOI18N
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cpu/Untitled3.png"))); // NOI18N
        jLabel15.setText("jLabel15");

        jMenu1.setText("File");

        jMenuItem1.setText("Add my data");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem6.setText("Report");
        jMenu1.add(jMenuItem6);

        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem4.setText("Instructions");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("About");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cpuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel14)
                        .addGap(10, 10, 10)
                        .addComponent(Average, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cpuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Average, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>                        

    // <editor-fold defaultstate="collapsed" desc="buttons" >
    
    /**
     * Event triggered when number of jobs menu is changed.
     * it updates the MainQueue and the table data
     * @param evt 
     */
    private void numOfJobsActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        int num = Integer.parseInt(numOfJobs.getSelectedItem()+"");
        MainQueue.createNew(num);
        updateTable(MainQueue.get());
        //setMainQ();
    }                                         

   /**
    * performs a step in the simulation
    * @param evt 
    */
    private void nextStepBttnActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        if(!Simulation.Finished)
        {
            numOfJobs.setEnabled(false);
            quantum.setEnabled(false);
            restartBttn.setEnabled(true);
            AlgorithmsMenu.setEnabled(false);
            Job job = Simulation.workStep();
            viewVisuals(job, Simulation.getReadyQueue());
        }
        if(Simulation.Finished){finishBttnActionPerformed(null);}
        String t1 = myTable.getAverageWaiting() + "";
        String t2 = myTable.getAverageTurn() + "";
        if(t1.length() > 5) { t1 = t1.substring(0, 5);} // set max length to 5
        if(t2.length() > 5) { t2 = t2.substring(0, 5);}
        AverWait.setText(t1);
        AverTurn.setText(t2);
        Simulation.Time++;
    }                                            

    /**
     * stops the simulation.
     * also disables stop button and enables next step
     * button and simulate button.
     * @param evt 
     */
    private void stopBttnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        Simulation.Stoped = true; // stop the simulation
        stopBttn.setEnabled(false);
        nextStepBttn.setEnabled(true);
        simulateBttn.setEnabled(true);
        
    }                                        

    /**
     * restart simulation with same data
     * @param evt 
     */
    private void restartBttnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        stopBttnActionPerformed(null);
        restartBttn.setEnabled(false);
        nextStepBttn.setEnabled(true);
        numOfJobs.setEnabled(true);
        quantum.setEnabled(true);
        AlgorithmsMenu.setEnabled(true);
        simulateBttn.setEnabled(true);
        finishBttn.setEnabled(true);
        MainQueue.reset();
        updateTable(MainQueue.get());
        Simulation.reset();
        AverWait.setText("0");
        AverTurn.setText("0");
        clearVisuals();
    }                                           

    /**
     * start another simulation with another random data
     * @param evt 
     */
    private void anotherSimBttnActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        // adjust buttons
        stopBttnActionPerformed(null);
        restartBttn.setEnabled(false);
        numOfJobs.setEnabled(true);
        AlgorithmsMenu.setEnabled(true);
        quantum.setEnabled(true);
        nextStepBttn.setEnabled(true);
        simulateBttn.setEnabled(true);
        finishBttn.setEnabled(true);
        // reset average wait and turnaround
        AverWait.setText("0");
        AverTurn.setText("0");
        
        numOfJobsActionPerformed(null);
        Simulation.reset();  // reset simulation
        clearVisuals(); // reset CPU, Gantt and readyQueue view
    }                                              

    /**
     * starts the simulation.
     * @param evt 
     */
    private void simulateBttnActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        Simulation.Stoped = false;  // start the simulation
        simulateBttn.setEnabled(false);
        stopBttn.setEnabled(true);
        nextStepBttn.setEnabled(false);
    }                                            

    /**
     * completes the simulation to the end immediately 
     * @param evt 
     */
    private void finishBttnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        while(!Simulation.Finished)
        {
            nextStepBttnActionPerformed(null);  // press next step button till the simluation is finished
        }
        // modify the buttons view
        stopBttnActionPerformed(null);
        stopBttn.setEnabled(false);
        finishBttn.setEnabled(false);
        nextStepBttn.setEnabled(false);
        simulateBttn.setEnabled(false);
    }                                          

    /**
     * view instructions frame
     * @param evt 
     */
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        new Instruction().show(); 
    }                                          

    /**
     * view about frame
     * @param evt 
     */
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        new About().show();   
    }                                          

    /**
     * exit the program
     * @param evt 
     */
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:.
        System.exit(0);
    }                                          

    /**
     * view the frame to allow the user to add jobs data manually 
     * @param evt 
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        AddData ad = new AddData();
        ad.show();
        this.dispose();
    }                                          

    /**
     * Event triggered when Algorithms menu is changed.
     * it changes the algorithm type in the Simulation class
     * @param evt 
     */
    private void AlgorithmsMenuActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        Simulation.AlgorithmType = AlgorithmsMenu.getSelectedItem().toString();
    }                                              

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="intializations" >
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
            java.util.logging.Logger.getLogger(Face.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Face.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Face.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Face.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Face().setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JComboBox AlgorithmsMenu;
    public static javax.swing.JLabel AverTurn;
    public static javax.swing.JLabel AverWait;
    public static javax.swing.JPanel Average;
    private javax.swing.JButton anotherSimBttn;
    public static javax.swing.JLabel cpuCurrentJob;
    public static javax.swing.JLabel cpuCurrentTime;
    public static javax.swing.JPanel cpuPanel;
    public static javax.swing.JLabel cpuUtilize;
    private javax.swing.JButton finishBttn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextStepBttn;
    private javax.swing.JComboBox numOfJobs;
    private javax.swing.JComboBox quantum;
    private javax.swing.JButton restartBttn;
    private javax.swing.JComboBox simSpeed;
    private javax.swing.JButton simulateBttn;
    private javax.swing.JButton stopBttn;
    public static javax.swing.JTable table;
    // End of variables declaration                   
    // </editor-fold>
}
