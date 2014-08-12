package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import contrllor.LoginService;

public class LoginFrame extends JFrame implements ActionListener{

	BufferedImage img= null;
	JTextField loginTextField;
	JPasswordField passwordField;
	JButton btButton;
	// 메인
	public static void main(String args[]) {
		new LoginFrame();
	}

	// 생성자
	public LoginFrame() {
		setTitle("로그인 테스트");
		setSize(1600, 900);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//레이아웃 설정
		setLayout(null); //개발자가 각각 하나씩 설정하겠다.
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1600, 900);
		layeredPane.setLayout(null);
		
		// 이미지 불러오기
		try{
			img = ImageIO.read(new File("image/login.png"));
		}catch(IOException e){
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}
		
		// 패널1 (이미지 불러올 패널)
		MyPanel panel = new MyPanel();
		panel.setBounds(0,0,1600,900);
		
		// 로그인필드
		loginTextField = new JTextField(15);
		loginTextField.setBounds(731, 399, 280, 30);
		loginTextField.setOpaque(false); //투명처리
		loginTextField.setForeground(Color.GREEN); //전경색
		loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());// 경계선 삭제
		layeredPane.add(loginTextField);
	
		
		// 비밀번호필드
		passwordField = new JPasswordField(15);
		passwordField.setBounds(731, 529, 280, 30);
		passwordField.setOpaque(false); //투명처리
		passwordField.setForeground(Color.GREEN); //전경색
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());// 경계선 삭제
		layeredPane.add(passwordField);
		
		// 로그인 버튼 추가
		btButton =new JButton(new ImageIcon("image/btLogin_hud.png"));
		btButton.setBounds(755,689,104,48);
		// 로그인버튼 투명처리
		btButton.setBorderPainted(false);
		btButton.setFocusPainted(false);
		btButton.setContentAreaFilled(false);
		btButton.addActionListener(this);
		layeredPane.add(btButton);
		
		
		// 마지막 추가사항들
		layeredPane.add(panel);
		add(layeredPane);
		
		setVisible(true);
	}

	class MyPanel extends JPanel{
		public void paint(Graphics g){
			g.drawImage(img, 0,0,null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id=loginTextField.getText();
		char[] pass = passwordField.getPassword();//메모리 낭비를 위해
		String passwordString = new String(pass);
		if(id.equals("")|| passwordString.equals("")){
			//메세지를 날린다.
			JOptionPane.showMessageDialog(null,"빈칸이 있네요.");
		}else{
			//로그인 참,거짓 여부를 판단.
			boolean existLogin = LoginService.loginTest(id,passwordString);
			if(existLogin){
				//로그인 성공일 경우
				JOptionPane.showMessageDialog(null,"로그인 성공");
			}else{
				//로그인 실패일 경우
				JOptionPane.showMessageDialog(null,"로그인 실패");
			}
		}
	}
	
	
	
	
	
}
