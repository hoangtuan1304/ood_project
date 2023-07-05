package view;

import controller.MenuController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SettingView extends JFrame {
    private final Font FONT_COMBOBOX = new Font("Arial", Font.BOLD, 18);
    private final Color COLOR_COMBOBOX = new Color(222, 226, 230);
    private ImageIcon title = new ImageIcon(SettingView.class.getResource("/res/lblSetting.png"));
    private java.util.List<ColorItem> colorItems = new ArrayList<>();
    private java.util.List<Integer> timeItems = new ArrayList<>();
    private List<ThemeItem> themeItems = new ArrayList<>();
    public JComboBox<ColorItem> selectColor;
    public JComboBox<Integer> selectTime;
    public JComboBox<ThemeItem> selectPieceTheme;
    public JLabel buttonBack;

    private MenuController menuController;
    public SettingView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setExtendedState(JFrame.NORMAL);
        setTitle("Setting Game");
        setBounds(100, 100, 797, 512);
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        //set gradient background
        GradientBackground gradient = new GradientBackground(new Color(156, 89, 254), new Color(111, 83, 253));
        gradient.setBounds(0, 0, 797, 512);
        contentPane.add(gradient);
        gradient.setLayout(null);

        //add Title
        JLabel lbText = new JLabel("SETTING");
        lbText.setForeground(SystemColor.window);
        lbText.setFont(new Font("Arial", Font.BOLD, 24));
        lbText.setBounds(363, 50, 114, 37);
        gradient.add(lbText);


        JLabel Title = new JLabel("");
        Title.setBounds(322, 32, 185, 75);
        Image imgTmpTitle = this.title.getImage();
        Image newing = imgTmpTitle.getScaledInstance(180, 50, Image.SCALE_SMOOTH);
        Title.setIcon(new ImageIcon(newing));
        Title.setIcon(new ImageIcon(newing));
        gradient.add(Title);

        //Select time text
        JLabel lbLevels = new JLabel("");
        lbLevels.setIcon(new ImageIcon(SettingView.class.getResource("/res/lblChangeTime.png")));
        lbLevels.setBounds(60, 151, 114, 37);
        gradient.add(lbLevels);

        JLabel lbHaiCham = new JLabel("");
        lbHaiCham.setIcon(new ImageIcon(SettingView.class.getResource("/res/dot.png")));
        lbHaiCham.setBounds(335, 151, 23, 25);
        gradient.add(lbHaiCham);

        //select Color text
        JLabel lbColor = new JLabel("");
        lbColor.setIcon(new ImageIcon(SettingView.class.getResource("/res/lblChangeColor.png")));
        lbColor.setBounds(60, 224, 233, 37);
        gradient.add(lbColor);

        JLabel lbHaiCham_1 = new JLabel("");
        lbHaiCham_1.setIcon(new ImageIcon(SettingView.class.getResource("/res/dot.png")));
        lbHaiCham_1.setBounds(335, 224, 23, 25);
        gradient.add(lbHaiCham_1);

        //select Pieces text
        JLabel lbPieces = new JLabel("");
        lbPieces.setIcon(new ImageIcon(SettingView.class.getResource("/res/lblChangeTheme.png")));
        lbPieces.setBounds(60, 295, 233, 37);
        gradient.add(lbPieces);

        JLabel lbHaiCham3 = new JLabel("");
        lbHaiCham3.setIcon(new ImageIcon(SettingView.class.getResource("/res/dot.png")));
        lbHaiCham3.setBounds(335, 300, 40, 24);
        gradient.add(lbHaiCham3);

        selectTime = new JComboBox();
        selectTime.setFont(FONT_COMBOBOX);
        selectTime.setBackground(COLOR_COMBOBOX);
        selectTime.setEditable(false);
        selectTime.setBounds(450, 149, 201, 41);
        gradient.add(selectTime);

        addTimeItem(10);
        addTimeItem(5);
        addTimeItem(3);
        addTimeItem(2);
        addTimeItem(1);
        for (Integer i : timeItems) {
            selectTime.addItem(i);
        }
        selectTime.setSelectedItem(10);
        gradient.add(selectTime);

        // Theme
        selectColor = new JComboBox();
        selectColor.setFont(FONT_COMBOBOX);
        selectColor.setBackground(COLOR_COMBOBOX);
        selectColor.setEditable(false);
        selectColor.setBounds(450, 214, 201, 41);
        addColorItem(new ColorItem("Default", new Color(183, 192, 216), new Color(232, 237, 249)));
        addColorItem(new ColorItem("Tournament", new Color(51, 106, 77), new Color(236, 237, 232)));
        addColorItem(new ColorItem("Dash", new Color(183, 135, 73), new Color(108, 61, 41)));
        addColorItem(new ColorItem("Checkers", new Color(199, 76, 81), new Color(48, 48, 48)));
        selectColor.setRenderer(new ColorItem.DualColorItemRenderer());
        for (ColorItem item : colorItems) {
            selectColor.addItem(item);
        }
        selectColor.setSelectedItem("Chọn màu");
//        selectTime.addActionListener(homeView.getMenuController().selectColorSetting);
        gradient.add(selectColor);


        // Piece
        selectPieceTheme = new JComboBox();
        selectColor.setBackground(COLOR_COMBOBOX);
        selectPieceTheme.setFont(FONT_COMBOBOX);
        selectPieceTheme.setEditable(false);
        selectPieceTheme.setBounds(450, 290, 201, 41);
        gradient.add(selectPieceTheme);
        addThemeItem(new ThemeItem("Default", "pieceTheme1.png"));
        addThemeItem(new ThemeItem("Minimalism", "pieceTheme2.png"));
        addThemeItem(new ThemeItem("Luca", "pieceTheme3.png"));
        addThemeItem(new ThemeItem("Classic", "pieceTheme4.png"));
        for (ThemeItem item : themeItems) {
            selectPieceTheme.addItem(item);
        }
        selectPieceTheme.setRenderer(new ThemeItem.ThemeIcon());
        selectPieceTheme.setSelectedItem("Chọn quân cờ");


        JLabel backText = new JLabel("");
        backText.setIcon(new ImageIcon(SettingView.class.getResource("/res/iconBack.png")));
        backText.setBounds(130, 35, 81, 31);
        gradient.add(backText);

        buttonBack = new JLabel("");
        buttonBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonBack.setIcon(new ImageIcon(SettingView.class.getResource("/res/btnBack_hover.png")));
                buttonBack.setBounds(33, 8, 108, 87);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonBack.setIcon(new ImageIcon(SettingView.class.getResource("/res/btnBack.png")));
                buttonBack.setBounds(60, 20, 50, 63);
            }

        });
        buttonBack.setIcon(new ImageIcon(SettingView.class.getResource("/res/btnBack.png")));
        buttonBack.setBounds(60, 20, 50, 63);
        gradient.add(buttonBack);
    }

    public void addColorItem(ColorItem item) {
        colorItems.add(item);
    }

    public void removeColorItem(ColorItem item) {
        colorItems.remove(item);
    }

    public void addTimeItem(int item) {
        timeItems.add(item);
    }

    public void removeTimeItem(int item) {
        timeItems.remove(item);
    }

    public void addThemeItem(ThemeItem themeItem) {
        themeItems.add(themeItem);
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }
}
