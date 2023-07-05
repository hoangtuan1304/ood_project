package view;

import controller.PieceListener;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ExitDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	public ExitDialog() {
		setUndecorated(true);
		setBounds(100, 100, 350, 300);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		contentPanel.setLayout(null);


		JLabel iconX = new JLabel("");
		iconX.setBounds(130, 10, 60, 69);
		contentPanel.add(iconX);
		iconX.setIcon(new ImageIcon(ExitDialog.class.getResource("/res/btnClose.png")));

		JTextPane txtpnAreYouSure = new JTextPane();
		txtpnAreYouSure.setEditable(false);
		txtpnAreYouSure.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 19));
		txtpnAreYouSure.setText("Are you sure you want to exit the game?");
		StyledDocument doc = txtpnAreYouSure.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		txtpnAreYouSure.setBounds(29, 114, 257, 69);
		txtpnAreYouSure.setForeground(new Color(111, 83, 253));
		contentPanel.add(txtpnAreYouSure);


		//add button Yes
		JLabel buttonYes = new JLabel("");
		buttonYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonYes.setIcon(new ImageIcon(ExitDialog.class.getResource("/res/btnYes_hover.png")));
				buttonYes.setBounds(10, 200, 153, 97);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonYes.setIcon(new ImageIcon(ExitDialog.class.getResource("/res/btnYes.png")));
				buttonYes.setBounds(50, 203, 85, 52);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				PieceListener.playSound("soundClick.wav");
				System.exit(31);
			}
		});
		buttonYes.setIcon(new ImageIcon(ExitDialog.class.getResource("/res/btnYes.png")));
		buttonYes.setBounds(50, 203, 85, 52);
		contentPanel.add(buttonYes);

		//add button no
		JLabel buttonNo = new JLabel("");
		buttonNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonNo.setIcon(new ImageIcon(ExitDialog.class.getResource("/res/btnNo_hover.png")));
				buttonNo.setBounds(150, 200, 153, 97);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonNo.setIcon(new ImageIcon(ExitDialog.class.getResource("/res/btnNo.png")));
				buttonNo.setBounds(190, 200, 85, 57);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				PieceListener.playSound("soundClick.wav");
			}
		});
		buttonNo.setIcon(new ImageIcon(ExitDialog.class.getResource("/res/btnNo.png")));
		buttonNo.setBounds(190, 200, 85, 57);
		contentPanel.add(buttonNo);


		JLabel lbbg = new JLabel("");
		lbbg.setIcon(new ImageIcon(ExitDialog.class.getResource("/res/exitWrapper.png")));
		lbbg.setBounds(8, 31, 307, 269);
		contentPanel.add(lbbg);
	}
}
