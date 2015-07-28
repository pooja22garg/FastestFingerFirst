import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;

import net.miginfocom.swing.MigLayout;

import java.awt.Component;

import javax.swing.border.TitledBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JToggleButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowStateListener;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.io.BufferedInputStream;

public class ServerFront extends Thread{

	protected JFrame frame;
	protected JTable table;
	protected JTable table_1;
	protected JTable table_2;
	protected JTable table_3;
	protected JTable table_4;
	protected DefaultTableModel dtm1 = new DefaultTableModel(0,0);
	protected DefaultTableModel dtm2 = new DefaultTableModel(0,0);
	protected static ServerFront window;
	protected JPanel panel;
	protected JPanel panel_1;
	protected JPanel panel_2;
	protected JPanel panel_3;
	protected JPanel panel_4;
	private String[] sv={"","THNNNNNNNNN","TRNNNNNNNNN","TRRNNNNNNNN","TRRRNNNNNNN","TRRRRRNNNNN","TRRRRNNNNNN","TRRRRRRNNNN","TRRRRRRRNNN","TRRRRRRRRNN","TRRRRRRRRRN"};
	static String winner[] = new String[3];
	static String answ[] = new String[3];
	protected static String ServerProcessId ="0";
	 ServerRecieve sr ;
	Queue sendQueue = new Queue();
	String addresses[] = new String[10];
	JButton assignId = new JButton("ASSIGN IDs");
	private JTextField cs1;
	private JTextField cs2;
	private JTextField cs3;
static MessageConsume m;
	/**
	 * Launch the application.
	 */
	public static Server rec = new Server();
	public static int count=0;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 window= new ServerFront();
					window.frame.setVisible(true);
					
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		rec.start();
		/*Client c = new Client();
		c.start();
		Client c1 = new Client();
		c1.start();
		Client c2 = new Client();
		c2.start();
		*/
		
		RegisterClient();
	
		
	}
 public static void RegisterClient() 
 {
	String[] t= new  String[2];
	String addr,s;
	String[] addresses=new String[10];
	 m = new MessageConsume(rec.MsgQueue);
		
		int i=0;
while(i<3){
		try {
				s=m.EmptyQueue();
				t = s.split(",");
				addr = t[0];
				addresses[i] = addr;
				t = addr.split(":");
				addr = t[1];
				updateRegisterationPanel(i,addr);
				i++;

				
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
}

System.out.println("out of loop");
String temp = "\n";

	FileWriter fo = null;
	try {
		fo = new FileWriter("./src/IPList");
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	BufferedWriter bw = new BufferedWriter(fo);
	
	try {
		
		while(i>0){		
		System.out.println(addresses[3-i]);
		
		bw.write(addresses[3-i]);
		
		bw.newLine();
		i--;
		}
		bw.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	
 } 
 public static void updateRegisterationPanel(final int i,final String addresses)
 {
	
	 EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					window.dtm1.addRow(new Object[] {i+1,addresses});
					if(i==2){window.panel_2.setVisible(true);}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	    	 
 }
 
 
	/**
	 * Create the application.
	 */
	public ServerFront() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 764, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		 panel= new JPanel();
		
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel.setBackground(Color.YELLOW);
		
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(25);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		
		
		JLabel lblRegisteredParticipants = new JLabel("Registered Participants");
		lblRegisteredParticipants.setFont(new Font("Georgia", Font.BOLD, 13));
		panel.add(lblRegisteredParticipants);
		
		table_1 = new JTable();
		table_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		 
		
		String header[] = new String[] { "SerialNo","IP Address" };

	//add header in table model
		dtm1.setColumnIdentifiers(header);
		table_1.setModel(dtm1);
		

		table_1.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(120);
		//table_2.getColumnModel().getColumn(2).setPreferredWidth(120);
		panel.add(table_1);
		
		panel_1 = new JPanel();
		panel_1.setVisible(true);
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_1.setBackground(Color.ORANGE);
		//panel_1.setVisible(false);
		
		
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		
		JButton b = new JButton("ggfggdg");
		b.setBackground(Color.magenta);
		panel.add(b);
		
		table_3 = new JTable();
		table_3.setBounds(111, 101, 153, 0);
		String ColIden [] = new String[]{"dfs","fdfd","fd"};
			dtm2.setColumnIdentifiers(ColIden);
		
		table_3.setModel(dtm2);
		//panel_1.add(table_3);
		
	/*	JLabel lblNewLabel_1 = new JLabel("New label");
		panel_1.add(lblNewLabel_1, "cell 1 7");
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel_1.add(lblNewLabel_2, "cell 2 7");
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		panel_1.add(lblNewLabel_3, "cell 3 7");
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		panel_1.add(lblNewLabel_4, "cell 4 7");
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		panel_1.add(lblNewLabel_5, "cell 5 7");
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		panel_1.add(lblNewLabel_6, "cell 1 8");
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		panel_1.add(lblNewLabel_7, "cell 2 8");
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		panel_1.add(lblNewLabel_8, "cell 3 8");
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		panel_1.add(lblNewLabel_9, "cell 4 8");
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		panel_1.add(lblNewLabel_10, "cell 5 8");
		*/
		//panel_1.setVisible(false);
		 panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_2.setBackground(Color.PINK);
		panel_2.setVisible(false);
		frame.getContentPane().add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new MigLayout("", "[][grow][][]", "[][][][][][][][grow][][][]"));
		
		assignId.setFont(new Font("Georgia", Font.BOLD, 13));
		
	
		assignId.addActionListener(new ActionListener() {
			//private Server[] se =new Server[10];
			private Send[] se =new Send[10];
			public void actionPerformed(ActionEvent e) {
				String s=new String();
				String[] id =new String[10];
				String mt[] = {"in","id"};
				String msg;
				String filecontent = new String("");
				int i=0,t;
				Thread[] t1 = new Thread[10]; 
				
			String[] h = new String[2]; 
				
				BufferedReader br;
				try {
					br = new BufferedReader(new FileReader("./src/IPList"));
					Random randomGenerator = new Random();	
				
					
					try {
						while((s=br.readLine())!=null){
					
							do{
							
								t=randomGenerator.nextInt(4);
								
								}while(Arrays.asList(id).contains(Integer.toString(t))==true || t==0);
								id[i]=Integer.toString(t);
							
								addresses[i] = s;
								/*h=s.split(":");
								if(h[0].equals("/172.50.88.50"))
								{id[i]="1";}
								else if(h[0].equals("/172.50.88.49"))
								{id[i]="3";}
								else
									id[i]="2";*/
								i++;
								}
						br.close();
					
						try {
						//	FileOutputStream fo = new FileOutputStream("./src/IPList");
							FileWriter fo = new FileWriter("./src/IPList");
							BufferedWriter bw = new BufferedWriter(fo);
							
								
								try {
									while(i>0)
									{
									
										bw.write(id[3-i]);
										bw.write(",");
										bw.write(addresses[3-i]);
										bw.newLine();
										i--;
									}
									 bw.close();
										
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						updateIDNoPanel(id,addresses);
						i=0;
						br = new BufferedReader(new FileReader("./src/IPList"));
						String s1;
						while((s1= br.readLine())!=null){
						
							filecontent = filecontent+s1+">";
							
						}
						
						br.close();
							/* preparing message to initialise client whose id is generated ****/
							while(i<3){
								msg = addresses[i] + "}"+ServerProcessId +"'" + "id;"+id[i];   //id is send in string format
								se[i] =new Send(sendQueue.MsgQueue);
								sendQueue.MsgQueue.add(msg);
								msg = addresses[i] + "}"+ServerProcessId +"'" + "in;"+sv[Integer.parseInt(id[i])];
								
								sendQueue.MsgQueue.add(msg); 
								msg = addresses[i] + "}"+ServerProcessId +"'" + "fi;" + filecontent;
								sendQueue.MsgQueue.add(msg); 
								//System.out.println("server sending vector "+ msg[0] + "to id" + id[i]);
								//rec.handleSend(mt, addresses[i], msg);
							
								// t1[i] = new Thread(new Send(rec.server,mt, addresses[i], msg));
								//t1[i].start();
								try {
									
								Thread.sleep(500);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							//END
						i++;		
							}
						
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				 
				
				assignId.setEnabled(false);
				
			}
		});
		panel_2.add(assignId, "cell 1 1,alignx center,aligny center");
		
		table_2 = new JTable();  // Table to display id allocated to each client

		String h[] = new String[] { "SerialNo","ID","IP Address" };

	//add header in table model
		dtm2.setColumnIdentifiers(h);
		table_2.setModel(dtm2);
		table_2.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(120);
		//panel_2.add(table_2);
		
		panel_2.add(table_2, "cell 1 3,growx,aligny center");

		panel_3 = new JPanel();
		panel_3.setBackground(Color.red);
		panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		frame.getContentPane().add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new MigLayout("", "[][][]", "[][][][][][][][][]"));
		panel_3.setVisible(false);
		
		JButton btnNewButton_2 = new JButton("     PLAY GAME    ");
		btnNewButton_2.setFont(new Font("Georgia", Font.BOLD, 13));
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=0;
				String msg;
				while(i<3)
				{
					msg = addresses[i] + "}"+ServerProcessId +"'" + "op;" + "india,america,france,germany";
					System.out.println(msg);
					sendQueue.MsgQueue.add(msg);
					i++;
				}
				 sr =new ServerRecieve(rec.MsgQueue,rec.server);
				 criticalSection();
				 panel_1.setVisible(true);
				 
			}
		}
		
				);
		btnNewButton_2.setEnabled(false);
		JButton btnNewButton_1 = new JButton("DISPLAY GAME");
		btnNewButton_1.setFont(new Font("Georgia", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnNewButton_2.setEnabled(true);	
			}}		);
		
		panel_3.add(btnNewButton_1, "cell 1 4");
		panel_3.add(btnNewButton_2, "cell 1 7,alignx center");
		 panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_4.setVisible(false);
		frame.getContentPane().add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new MigLayout("", "[grow][][][][][][grow][grow][][][][][][][][][][]", "[grow][][][][][][]"));
		
		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table_4.getColumnModel().getColumn(0).setMaxWidth(214);
		table_4.getColumnModel().getColumn(1).setMaxWidth(214);
		table_4.getColumnModel().getColumn(2).setMaxWidth(214);
		panel_4.add(table_4, "cell 7 0 2 7,growx,aligny center");
		
		JLabel lblNewLabel_19 = new JLabel("New label");
		panel_4.add(lblNewLabel_19, "cell 1 3");
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_4.add(lblNewLabel, "cell 2 3");
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		panel_4.add(lblNewLabel_11, "cell 3 3");
		
		JLabel lblNewLabel_12 = new JLabel("New label");
		panel_4.add(lblNewLabel_12, "cell 4 3");
		
		JLabel lblNewLabel_13 = new JLabel("New label");
		panel_4.add(lblNewLabel_13, "cell 5 3");
		
		JLabel lblNewLabel_14 = new JLabel("New label");
		panel_4.add(lblNewLabel_14, "cell 1 4");
		
		JLabel lblNewLabel_15 = new JLabel("New label");
		panel_4.add(lblNewLabel_15, "cell 2 4");
		
		JLabel lblNewLabel_16 = new JLabel("New label");
		panel_4.add(lblNewLabel_16, "cell 3 4");
		
		JLabel lblNewLabel_17 = new JLabel("New label");
		panel_4.add(lblNewLabel_17, "cell 4 4");
		
		JLabel lblNewLabel_18 = new JLabel("New label");
		panel_4.add(lblNewLabel_18, "cell 5 4");
		JButton resetButton = new JButton("     RESET    ");
		btnNewButton_2.setFont(new Font("Georgia", Font.BOLD, 13));
		panel_4.add(resetButton,"cell 6 8");	
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				initialize();
				
				
			}});
		cs1 = new JTextField("1");
		cs2 = new JTextField("2");
		cs3 = new JTextField("3");
		panel_1.add(cs1);
		panel_1.add(cs2);
		panel_1.add(cs3);
	}
	
	
	public void updateIDNoPanel(String id[],String addresses[]){
		int i=0;
		while(i<3){
		
			dtm2.addRow(new Object[] {i,id[i],addresses[i]});
			i++;
		}
		panel_3.setVisible(true);
	}
	
	
	public void criticalSection(){
		
		String s,id;
		int i=0;
		String temp[] = new String[2];
		
		System.out.println("inside answer");
		while(i<3){
		try {
			s= m.EmptyQueue();
			temp =s.split("'");
			id =temp[0];
			
			
			
			temp =temp[1].split(";");
			System.out.println(id);
			if(id.equals("1"))
			{
				
				System.out.println("1 inside cs");
//				
				
			}else if(id.equals("2"))
			{
				System.out.println("2 inside cs");
			}else{
				
				System.out.println("3 inside cs");
				
			}
				
			answ[i]=id+":"+temp[1];
			i++;
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    	}
		}
				
		
	}
}
