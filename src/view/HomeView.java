package view;

import controller.MenuController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeView extends JFrame {
    private MenuController menuController;
    private SettingView settingView;

    private ImageIcon buttonPlay = new ImageIcon(HomeView.class.getResource("/res/btnPlay.png"));
    private ImageIcon buttonSetting = new ImageIcon(HomeView.class.getResource("/res/btnSetting.png"));
    private ImageIcon buttonExit = new ImageIcon(HomeView.class.getResource("/res/btnExit.png"));
    private ImageIcon buttonPlay2 = new ImageIcon(HomeView.class.getResource("/res/btnPlay_hover.png"));
    private ImageIcon buttonSetting2 = new ImageIcon(HomeView.class.getResource("/res/btnSetting_hover.png"));
    private ImageIcon buttonExit2 = new ImageIcon(HomeView.class.getResource("/res/btnExit_hover.png"));
    
    /**
     * Create the frame.
     */
    public HomeView() {
        settingView = new SettingView();
        menuController = new MenuController(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setExtendedState(JFrame.NORMAL);
        setTitle("Chess Game");
        setBounds(100, 100, 797, 512);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.black);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        //set gradient background
        GradientBackground gradient = new GradientBackground( new Color(156, 89, 254), new Color(111, 83, 253));
        gradient.setBounds(0, 0, 797, 512);
        contentPane.add(gradient);
        gradient.setLayout(null);

        //logoGame
        JLabel logoGame = new JLabel("");
        logoGame.setBounds(140, 10, 499, 149);
        gradient.add(logoGame);
        logoGame.setIcon(new ImageIcon(HomeView.class.getResource("/res/lblLogo.png")));

        //add button play
        JLabel buttonPlay = new JLabel("");
        buttonPlay.addMouseListener(menuController.startGame);
        buttonPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Image imgTmp2 = HomeView.this.buttonPlay2.getImage();
                Image newing2 = imgTmp2.getScaledInstance(400, 170, Image.SCALE_SMOOTH);
                buttonPlay.setIcon(new ImageIcon(newing2));
                buttonPlay.setBounds(216, 188, 378, 115);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Image imgTmp2 = HomeView.this.buttonPlay.getImage();
                Image newing2 = imgTmp2.getScaledInstance(270, 50, Image.SCALE_SMOOTH);
                buttonPlay.setIcon(new ImageIcon(newing2));
                buttonPlay.setBounds(280, 170, 378, 115);
            }
        });
        buttonPlay.setBounds(280, 170, 378, 115);
        Image imgTmp = this.buttonPlay.getImage();
        Image newing = imgTmp.getScaledInstance(270, 50, Image.SCALE_SMOOTH);
        buttonPlay.setIcon(new ImageIcon(newing));
        gradient.add(buttonPlay);

        //add button setting
        JLabel buttonSetting = new JLabel("");
        buttonSetting.addMouseListener(menuController.openSettingView);
        buttonSetting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Image imgTmpSetting = buttonSetting2.getImage();
                Image newing = imgTmpSetting.getScaledInstance(400, 170, Image.SCALE_SMOOTH);
                buttonSetting.setIcon(new ImageIcon(newing));
                buttonSetting.setBounds(215, 268, 378, 115);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                Image imgTmpSetting = HomeView.this.buttonSetting.getImage();
                Image newing = imgTmpSetting.getScaledInstance(270, 50, Image.SCALE_SMOOTH);
                buttonSetting.setIcon(new ImageIcon(newing));
                buttonSetting.setBounds(280, 255, 290, 108);
            }
        });
        buttonSetting.setBounds(280, 260, 290, 108);
        Image imgTmpSetting = this.buttonSetting.getImage();
        Image newingSetting = imgTmpSetting.getScaledInstance(270, 50, Image.SCALE_SMOOTH);
        buttonSetting.setIcon(new ImageIcon(newingSetting));
        gradient.add(buttonSetting);

        //add button exit
        JLabel buttonExit = new JLabel("");
        buttonExit.addMouseListener(menuController.exitGame);
        buttonExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Image imgTmpSetting = buttonExit2.getImage();
                Image newing = imgTmpSetting.getScaledInstance(400, 170, Image.SCALE_SMOOTH);
                buttonExit.setIcon(new ImageIcon(newing));
                buttonExit.setBounds(218, 370, 383, 98);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                Image imgTmpSetting = HomeView.this.buttonExit.getImage();
                Image newing = imgTmpSetting.getScaledInstance(264, 53, Image.SCALE_SMOOTH);
                buttonExit.setIcon(new ImageIcon(newing));
                buttonExit.setBounds(280, 355, 290, 98);
            }
        });


        buttonExit.setBounds(280, 355, 290, 98);
        Image imgTmpExit = this.buttonExit.getImage();
        Image newingExit = imgTmpExit.getScaledInstance(270, 50, Image.SCALE_SMOOTH);
        buttonExit.setIcon(new ImageIcon(newingExit));
        gradient.add(buttonExit);

        settingView.selectTime.addActionListener(menuController.selectTimeSetting);
        settingView.selectColor.addActionListener(menuController.selectColorSetting);
        settingView.selectPieceTheme.addActionListener(menuController.selectThemeSetting);
        settingView.buttonBack.addMouseListener(menuController.backToMenuHome);
    }

    public SettingView getSettingView() {
        return this.settingView;
    }
}
