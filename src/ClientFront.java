import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.BorderLayout;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;


public class ClientFront {

	
	private JFrame frmKbcFastestFinger;
	private JTextField ServerIP_txt;
	private JTextField Part_Id_text;
	private JTextField IP_ADD_text;
	private JTextField Ques_txt;
	private JTextField ASA_txt;
	private JTextField ASB_txt;
	private JTextField ASC_txt;
	private JTextField ASD_txt;
	private JTextField RIS_txt1;
	private JTextField RIS_txt2;
	private JTextField RIS_txt3;
	private JTextField RIS_txt4;
	private JTextField RIS_txt5;
	private JTextField RIS_txt6;
	private JTextField RIS_txt7;
	private JTextField RIS_txt8;
	private JTextField RIS_txt9;
	private JTextField RIS_txt10;
	private JTextField RIR_txt1;
	private JTextField RIR_txt10;
	private JTextField RIR_txt2;
	private JTextField RIR_txt3;
	private JTextField RIR_txt4;
	private JTextField RIR_txt5;
	private JTextField RIR_txt6;
	private JTextField RIR_txt7;
	private JTextField RIR_txt8;
	private JTextField RIR_txt9;
	private JTextField RS_txt1;
	private JTextField RS_txt10;
	private JTextField RS_txt2;
	private JTextField RS_txt3;
	private JTextField RS_txt4;
	private JTextField RS_txt5;
	private JTextField RS_txt6;
	private JTextField RS_txt7;
	private JTextField RS_txt8;
	private JTextField RS_txt9;
	private JTextField RR_txt1;
	private JTextField RR_txt2;
	private JTextField RR_txt3;
	private JTextField RR_txt4;
	private JTextField RR_txt5;
	private JTextField RR_txt6;
	private JTextField RR_txt7;
	private JTextField RR_txt8;
	private JTextField RR_txt9;
	private JTextField RR_txt10;
	private JTextField RQ_txt1;
	private JTextField RQ_txt2;
	private JTextField RQ_txt3;
	private JTextField RQ_txt4;
	private JTextField RQ_txt5;
	private JTextField RQ_txt6;
	private JTextField RQ_txt7;
	private JTextField RQ_txt8;
	private JTextField RQ_txt9;
	private JTextField RQ_txt10;
	private JButton CSACCSS;
	private JButton CSREL;
	private JLabel lblA;
	private JLabel lblC;
	private JLabel lblB;
	private JLabel lblD;
	private JLabel opta_lbl = new JLabel("");
	private JLabel optb_lbl = new JLabel("");
	private JLabel optc_lbl = new JLabel("");
	private JLabel optd_lbl = new JLabel("");
	
	static ClientFront window;
	static String serverPortNo="1181";
	static String serverIp = "127.0.0.1";
	 static Queue sendQueue = new Queue();
	 static Queue recieveQueue = new Queue();
	static Socket[] so = new Socket[10];
	static  Deque<String> stack = new ArrayDeque<String>();
	 private  static int count=0;
	 static ClientRecieve mr;
	static ClientMessageHandle mh;
	static SharedDataStructures ds =new SharedDataStructures();
	static Queue reqQueue= new Queue();
	static  boolean flag =false;
	static boolean sockflag=true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ClientFront();
					window.frmKbcFastestFinger.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		System.out.println("exiting main");
	}

	/**
	 * Create the application.
	 */
	public static synchronized int getCount(){
		
		return(count);
	
	}
	public static synchronized boolean checkSocketStatus(){
		if(sockflag){
		
			sockflag = false;
			return(true);
		}
		else
			return(false);
	}
	public static synchronized void resetSocketStatus(int i){
		count = count+i;
		sockflag = true;
		
	}
	
	
	public ClientFront() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKbcFastestFinger = new JFrame();
		frmKbcFastestFinger.getContentPane().setBackground(new Color(47, 79, 79));
		frmKbcFastestFinger.setTitle("         KBC- \r\nFastest Finger First");
		frmKbcFastestFinger.setBounds(100, 100, 673, 499);
		frmKbcFastestFinger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKbcFastestFinger.getContentPane().setLayout(null);
		
		JPanel Register_panel = new JPanel();
		Register_panel.setForeground(new Color(128, 0, 0));
		Register_panel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 228, 181), null, new Color(255, 250, 205), null));
		Register_panel.setBackground(new Color(128, 128, 128));
		Register_panel.setToolTipText("Registration Panel");
		Register_panel.setBounds(32, 11, 596, 83);
		frmKbcFastestFinger.getContentPane().add(Register_panel);
		Register_panel.setLayout(null);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(104, 14, 1, 1);
		Register_panel.add(layeredPane_1);
		
		JLabel lblServerIpAddress = new JLabel("Server IP Address");
		lblServerIpAddress.setBounds(180, 29, 87, 14);
		Register_panel.add(lblServerIpAddress);
		
		ServerIP_txt = new JTextField();
		ServerIP_txt.setBounds(277, 26, 86, 20);
		Register_panel.add(ServerIP_txt);
		ServerIP_txt.setToolTipText("IP Address of Server");
		ServerIP_txt.setColumns(10);
		
		JLabel lblRegistrationDetails = new JLabel("Registration Details");
		lblRegistrationDetails.setFont(new Font("Arial", Font.BOLD, 14));
		lblRegistrationDetails.setBounds(211, 1, 138, 23);
		Register_panel.add(lblRegistrationDetails);
		
		JButton Register = new JButton("Register");
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String ipaddr,addr,msg;
				
				ipaddr = ServerIP_txt.getText();
				serverIp = ipaddr;
				addr = ipaddr+":"+serverPortNo;
				msg= "R";
				
				//System.out.println("from client Front , msg sent"+ msg + "to" + addr);
		
				/**** msg format which is pushed in sendQueue = msg } ipaddr:portno 	********/
				
				sendQueue.MsgQueue.add(msg+"}"+addr);
				ClientSend cs = new ClientSend(sendQueue.MsgQueue);
				
				//System.out.println("inside client front thread: "+cs);
				
				while(!flag){System.out.println("loop");}
					
				mr = new ClientRecieve(recieveQueue,stack,ipaddr);
				mh = new ClientMessageHandle(recieveQueue,ds);
				
				
			}
		});
		Register.setForeground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		Register.setBackground(UIManager.getColor("Button.highlight"));
		Register.setBounds(229, 54, 89, 23);
		Register_panel.add(Register);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(284, 199, -102, -24);
		frmKbcFastestFinger.getContentPane().add(layeredPane);
		
		JPanel Game_panel = new JPanel();
		Game_panel.setBorder(UIManager.getBorder("Button.border"));
		Game_panel.setBackground(new Color(192, 192, 192));
		Game_panel.setToolTipText("Game Details ");
		Game_panel.setBounds(32, 101, 596, 153);
		frmKbcFastestFinger.getContentPane().add(Game_panel);
		Game_panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Participiant ID");
		lblNewLabel.setBounds(103, 31, 80, 14);
		Game_panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("IP Address");
		lblNewLabel_1.setBounds(307, 31, 65, 14);
		Game_panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Question");
		lblNewLabel_2.setBounds(103, 62, 46, 14);
		Game_panel.add(lblNewLabel_2);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setBounds(103, 86, 46, 14);
		Game_panel.add(lblOptions);
		
		JLabel lblAnswerSequence = new JLabel("Answer Sequence");
		lblAnswerSequence.setBounds(103, 135, 86, 14);
		Game_panel.add(lblAnswerSequence);
		
		JLabel lblNewLabel_3 = new JLabel("Game Details");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(227, 0, 96, 14);
		Game_panel.add(lblNewLabel_3);
		
		Part_Id_text = new JTextField();
		Part_Id_text.setBounds(193, 28, 86, 20);
		Game_panel.add(Part_Id_text);
		Part_Id_text.setColumns(10);
		
		IP_ADD_text = new JTextField();
		IP_ADD_text.setBounds(371, 28, 86, 20);
		Game_panel.add(IP_ADD_text);
		IP_ADD_text.setColumns(10);
		
		Ques_txt = new JTextField();
		Ques_txt.setBounds(197, 59, 260, 20);
		Game_panel.add(Ques_txt);
		Ques_txt.setColumns(10);
		
		ASA_txt = new JTextField();
		ASA_txt.setBounds(197, 132, 31, 20);
		Game_panel.add(ASA_txt);
		ASA_txt.setColumns(10);
		
		ASB_txt = new JTextField();
		ASB_txt.setColumns(10);
		ASB_txt.setBounds(240, 132, 31, 20);
		Game_panel.add(ASB_txt);
		
		ASC_txt = new JTextField();
		ASC_txt.setColumns(10);
		ASC_txt.setBounds(281, 132, 31, 20);
		Game_panel.add(ASC_txt);
		
		ASD_txt = new JTextField();
		ASD_txt.setColumns(10);
		ASD_txt.setBounds(322, 132, 31, 20);
		Game_panel.add(ASD_txt);
		
		lblA = new JLabel("A");
		lblA.setBounds(197, 86, 15, 14);
		Game_panel.add(lblA);
		
		lblC = new JLabel("C");
		lblC.setBounds(197, 111, 23, 14);
		Game_panel.add(lblC);
		
		lblB = new JLabel("B");
		lblB.setBounds(342, 86, 23, 14);
		Game_panel.add(lblB);
		
		lblD = new JLabel("D");
		lblD.setBounds(342, 111, 23, 14);
		Game_panel.add(lblD);
		
	
		opta_lbl.setBounds(227, 86, 86, 17);
		Game_panel.add(opta_lbl);
		
		optc_lbl = new JLabel("");
		optc_lbl.setBounds(227, 110, 86, 17);
		Game_panel.add(optc_lbl);
		
		 optb_lbl = new JLabel("");
		optb_lbl.setBounds(371, 85, 86, 17);
		Game_panel.add(optb_lbl);
		
	    optd_lbl = new JLabel("");
	  	optd_lbl.setBounds(371, 111, 86, 17);
		Game_panel.add(optd_lbl);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setLayout(null);
		panel.setToolTipText("Critical Section Details");
		panel.setBounds(32, 265, 596, 197);
		frmKbcFastestFinger.getContentPane().add(panel);
		
		JLabel lblRequestReceived = new JLabel("Request Queue");
		lblRequestReceived.setBounds(9, 38, 80, 14);
		panel.add(lblRequestReceived);
		
		JLabel lblRequestReceived_1 = new JLabel("Reply Received");
		lblRequestReceived_1.setBounds(10, 63, 86, 14);
		panel.add(lblRequestReceived_1);
		
		JLabel lblReplySent = new JLabel("Reply Sent");
		lblReplySent.setBounds(9, 90, 80, 14);
		panel.add(lblReplySent);
		
		JLabel lblRelease = new JLabel("Release Info\r\n Received");
		lblRelease.setHorizontalAlignment(SwingConstants.LEFT);
		lblRelease.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRelease.setBounds(9, 116, 108, 14);
		panel.add(lblRelease);
		
		JLabel lblCriticalSectionDetails = new JLabel("Critical Section Details");
		lblCriticalSectionDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCriticalSectionDetails.setBounds(189, -1, 170, 14);
		panel.add(lblCriticalSectionDetails);
		
		RIS_txt1 = new JTextField();
		RIS_txt1.setColumns(10);
		RIS_txt1.setBounds(159, 140, 31, 20);
		panel.add(RIS_txt1);
		
		RIS_txt2 = new JTextField();
		RIS_txt2.setColumns(10);
		RIS_txt2.setBounds(191, 140, 31, 20);
		panel.add(RIS_txt2);
		
		RIS_txt3 = new JTextField();
		RIS_txt3.setColumns(10);
		RIS_txt3.setBounds(223, 140, 31, 20);
		panel.add(RIS_txt3);
		
		RIS_txt4 = new JTextField();
		RIS_txt4.setColumns(10);
		RIS_txt4.setBounds(255, 140, 31, 20);
		panel.add(RIS_txt4);
		
		JLabel lblReleaseInfoSent = new JLabel("Release Info Sent");
		lblReleaseInfoSent.setBounds(8, 143, 96, 14);
		panel.add(lblReleaseInfoSent);
		
		RIS_txt5 = new JTextField();
		RIS_txt5.setColumns(10);
		RIS_txt5.setBounds(287, 140, 31, 20);
		panel.add(RIS_txt5);
		
		RIS_txt6 = new JTextField();
		RIS_txt6.setColumns(10);
		RIS_txt6.setBounds(319, 140, 31, 20);
		panel.add(RIS_txt6);
		
		RIS_txt7 = new JTextField();
		RIS_txt7.setColumns(10);
		RIS_txt7.setBounds(351, 140, 31, 20);
		panel.add(RIS_txt7);
		
		RIS_txt8 = new JTextField();
		RIS_txt8.setColumns(10);
		RIS_txt8.setBounds(383, 140, 31, 20);
		panel.add(RIS_txt8);
		
		RIS_txt9 = new JTextField();
		RIS_txt9.setColumns(10);
		RIS_txt9.setBounds(416, 140, 31, 20);
		panel.add(RIS_txt9);
		
		RIS_txt10 = new JTextField();
		RIS_txt10.setColumns(10);
		RIS_txt10.setBounds(448, 140, 31, 20);
		panel.add(RIS_txt10);
		
		RIR_txt1 = new JTextField();
		RIR_txt1.setColumns(10);
		RIR_txt1.setBounds(159, 111, 31, 20);
		panel.add(RIR_txt1);
		
		RIR_txt10 = new JTextField();
		RIR_txt10.setColumns(10);
		RIR_txt10.setBounds(449, 111, 31, 20);
		panel.add(RIR_txt10);
		
		RIR_txt2 = new JTextField();
		RIR_txt2.setColumns(10);
		RIR_txt2.setBounds(191, 111, 31, 20);
		panel.add(RIR_txt2);
		
		RIR_txt3 = new JTextField();
		RIR_txt3.setColumns(10);
		RIR_txt3.setBounds(224, 111, 31, 20);
		panel.add(RIR_txt3);
		
		RIR_txt4 = new JTextField();
		RIR_txt4.setColumns(10);
		RIR_txt4.setBounds(256, 111, 31, 20);
		panel.add(RIR_txt4);
		
		RIR_txt5 = new JTextField();
		RIR_txt5.setColumns(10);
		RIR_txt5.setBounds(288, 111, 31, 20);
		panel.add(RIR_txt5);
		
		RIR_txt6 = new JTextField();
		RIR_txt6.setColumns(10);
		RIR_txt6.setBounds(320, 111, 31, 20);
		panel.add(RIR_txt6);
		
		RIR_txt7 = new JTextField();
		RIR_txt7.setColumns(10);
		RIR_txt7.setBounds(352, 111, 31, 20);
		panel.add(RIR_txt7);
		
		RIR_txt8 = new JTextField();
		RIR_txt8.setColumns(10);
		RIR_txt8.setBounds(384, 111, 31, 20);
		panel.add(RIR_txt8);
		
		RIR_txt9 = new JTextField();
		RIR_txt9.setColumns(10);
		RIR_txt9.setBounds(417, 111, 31, 20);
		panel.add(RIR_txt9);
		
		RS_txt1 = new JTextField();
		RS_txt1.setColumns(10);
		RS_txt1.setBounds(160, 84, 31, 20);
		panel.add(RS_txt1);
		
		RS_txt10 = new JTextField();
		RS_txt10.setColumns(10);
		RS_txt10.setBounds(449, 84, 31, 20);
		panel.add(RS_txt10);
		
		RS_txt2 = new JTextField();
		RS_txt2.setColumns(10);
		RS_txt2.setBounds(192, 84, 31, 20);
		panel.add(RS_txt2);
		
		RS_txt3 = new JTextField();
		RS_txt3.setColumns(10);
		RS_txt3.setBounds(224, 84, 31, 20);
		panel.add(RS_txt3);
		
		RS_txt4 = new JTextField();
		RS_txt4.setColumns(10);
		RS_txt4.setBounds(256, 84, 31, 20);
		panel.add(RS_txt4);
		
		RS_txt5 = new JTextField();
		RS_txt5.setColumns(10);
		RS_txt5.setBounds(288, 84, 31, 20);
		panel.add(RS_txt5);
		
		RS_txt6 = new JTextField();
		RS_txt6.setColumns(10);
		RS_txt6.setBounds(320, 84, 31, 20);
		panel.add(RS_txt6);
		
		RS_txt7 = new JTextField();
		RS_txt7.setColumns(10);
		RS_txt7.setBounds(352, 84, 31, 20);
		panel.add(RS_txt7);
		
		RS_txt8 = new JTextField();
		RS_txt8.setColumns(10);
		RS_txt8.setBounds(384, 84, 31, 20);
		panel.add(RS_txt8);
		
		RS_txt9 = new JTextField();
		RS_txt9.setColumns(10);
		RS_txt9.setBounds(417, 84, 31, 20);
		panel.add(RS_txt9);
		
		RR_txt1 = new JTextField();
		RR_txt1.setColumns(10);
		RR_txt1.setBounds(160, 57, 31, 20);
		panel.add(RR_txt1);
		
		RR_txt2 = new JTextField();
		RR_txt2.setColumns(10);
		RR_txt2.setBounds(192, 57, 31, 20);
		panel.add(RR_txt2);
		
		RR_txt3 = new JTextField();
		RR_txt3.setColumns(10);
		RR_txt3.setBounds(224, 57, 31, 20);
		panel.add(RR_txt3);
		
		RR_txt4 = new JTextField();
		RR_txt4.setColumns(10);
		RR_txt4.setBounds(256, 57, 31, 20);
		panel.add(RR_txt4);
		
		RR_txt5 = new JTextField();
		RR_txt5.setColumns(10);
		RR_txt5.setBounds(288, 57, 31, 20);
		panel.add(RR_txt5);
		
		RR_txt6 = new JTextField();
		RR_txt6.setColumns(10);
		RR_txt6.setBounds(320, 57, 31, 20);
		panel.add(RR_txt6);
		
		RR_txt7 = new JTextField();
		RR_txt7.setColumns(10);
		RR_txt7.setBounds(352, 57, 31, 20);
		panel.add(RR_txt7);
		
		RR_txt8 = new JTextField();
		RR_txt8.setColumns(10);
		RR_txt8.setBounds(384, 57, 31, 20);
		panel.add(RR_txt8);
		
		RR_txt9 = new JTextField();
		RR_txt9.setColumns(10);
		RR_txt9.setBounds(417, 57, 31, 20);
		panel.add(RR_txt9);
		
		RR_txt10 = new JTextField();
		RR_txt10.setColumns(10);
		RR_txt10.setBounds(449, 57, 31, 20);
		panel.add(RR_txt10);
		
		RQ_txt1 = new JTextField();
		RQ_txt1.setColumns(10);
		RQ_txt1.setBounds(160, 30, 31, 20);
		panel.add(RQ_txt1);
		
		RQ_txt2 = new JTextField();
		RQ_txt2.setColumns(10);
		RQ_txt2.setBounds(192, 30, 31, 20);
		panel.add(RQ_txt2);
		
		RQ_txt3 = new JTextField();
		RQ_txt3.setColumns(10);
		RQ_txt3.setBounds(224, 30, 31, 20);
		panel.add(RQ_txt3);
		
		RQ_txt4 = new JTextField();
		RQ_txt4.setColumns(10);
		RQ_txt4.setBounds(256, 30, 31, 20);
		panel.add(RQ_txt4);
		
		RQ_txt5 = new JTextField();
		RQ_txt5.setColumns(10);
		RQ_txt5.setBounds(288, 30, 31, 20);
		panel.add(RQ_txt5);
		
		RQ_txt6 = new JTextField();
		RQ_txt6.setColumns(10);
		RQ_txt6.setBounds(320, 30, 31, 20);
		panel.add(RQ_txt6);
		
		RQ_txt7 = new JTextField();
		RQ_txt7.setColumns(10);
		RQ_txt7.setBounds(352, 30, 31, 20);
		panel.add(RQ_txt7);
		
		RQ_txt8 = new JTextField();
		RQ_txt8.setColumns(10);
		RQ_txt8.setBounds(384, 30, 31, 20);
		panel.add(RQ_txt8);
		
		RQ_txt9 = new JTextField();
		RQ_txt9.setColumns(10);
		RQ_txt9.setBounds(417, 30, 31, 20);
		panel.add(RQ_txt9);
		
		RQ_txt10 = new JTextField();
		RQ_txt10.setColumns(10);
		RQ_txt10.setBounds(449, 30, 31, 20);
		panel.add(RQ_txt10);
		
		CSACCSS = new JButton("Critical Section Accessed");
		CSACCSS.setBounds(157, 170, 150, 23);
		panel.add(CSACCSS);
		
		CSREL = new JButton("Critical Section Released");
		CSREL.setHorizontalAlignment(SwingConstants.LEFT);
		CSREL.setBounds(340, 171, 150, 23);
		panel.add(CSREL);
	}

	public static void updateOptions(String s){
		
		String[] s1 = new String[4];
		s1=s.split(",");
		window.opta_lbl.setText(s1[0]);
		window.optb_lbl.setText(s1[1]);
		window.optc_lbl.setText(s1[2]);
		window.optd_lbl.setText(s1[3]);
		
		
		
	} 
	
public static void updateRequestQueue(String i){
		
	switch(i)
	{
	
	case "1":
	{
		window.RQ_txt1.setText(i);
		break;
	}
	case "2":
	{
		window.RQ_txt2.setText(i);
		break;
	}
	case "3":
	{
		window.RQ_txt3.setText(i);
		break;
	}	


	}		
	
	}

public static void updateID(String id){
	
	try {
		window.IP_ADD_text.setText(Inet4Address.getLocalHost().getHostAddress());
		window.Part_Id_text.setText(id);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

	
}