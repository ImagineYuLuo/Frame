package me.qwq;

import me.qwq.panel.MovePanel;
import me.qwq.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame implements ActionListener, MouseListener {

    private JPanel panelCenter = null;
    private JPanel panelBottom = null;
    public static JPanel totalContentPane = null;

    MovePanel movePanel = new MovePanel();

    private static final int w = 32;
    private static final int h = 192;

    private static final int TOTAL_WIDTH = MovePanel.screenWidth + w;
    private static final int TOTAL_HEIGHT = MovePanel.screenHeight + h;

    public MainFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("YuLuo");
        setResizable(false);
        setSize(TOTAL_WIDTH, TOTAL_HEIGHT);
        setContentPane(getPanelContentPane());
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            MainFrame frame = new MainFrame();
            SwingUtils.centerFrame(frame);
            frame.setVisible(true);
        }catch (Throwable ex){
            SwingUtils.showErrorPopup(ex);
        }
    }

    private JPanel getPanelContentPane(){
        if(totalContentPane == null){
            try{
                totalContentPane = new JPanel();
                totalContentPane.setName("PanelContentPane");
                totalContentPane.setPreferredSize(new Dimension(TOTAL_WIDTH, TOTAL_HEIGHT));
                totalContentPane.setLayout(new BorderLayout(5, 5));
                totalContentPane.add(getPanelCenter(), "Center");
                totalContentPane.add(getPanelBottom(), "South");
            }catch(Throwable ex){
                SwingUtils.showErrorPopup(ex);
            }
        }
        return totalContentPane;
    }

    //Center
    private JPanel getPanelCenter(){
        if(panelCenter == null){
            try{
                panelCenter = new JPanel();
                panelCenter.setName("PanelCenter");
                panelCenter.setBackground(Color.WHITE);
                panelCenter.add(movePanel);
                movePanel.startGameThread();
            }catch(Throwable ex){
                SwingUtils.showErrorPopup(ex);
            }
        }
        return panelCenter;
    }

    //Bottom
    private JPanel getPanelBottom(){
        if(panelBottom == null){
            try{
                panelBottom = new JPanel();
                panelBottom.setName("PanelBottom");
                panelBottom.setBackground(Color.DARK_GRAY);
                panelBottom.setPreferredSize(new Dimension(TOTAL_WIDTH-w, w*4-2));
                panelBottom.setLayout(null);
                //panelBottom.add(getLabelSetting(), getLabelSetting().getName());
                panelBottom.add(MovePanel.getDisplayFps(), MovePanel.getDisplayFps().getName());
            }catch(Throwable ex){
                SwingUtils.showErrorPopup(ex);
            }
        }
        return panelBottom;
    }

//    private JLabel getLabelSetting(){
//        if(labelSetting == null){
//            try{
//                labelSetting = new JLabel();
//                labelSetting.setName("Setting");
//                labelSetting.setBounds(5, 5, 150, 20);
//                labelSetting.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
//                labelSetting.setHorizontalAlignment(SwingConstants.CENTER);
//                labelSetting.setForeground(Color.WHITE);
//                labelSetting.setText("Setting[ESC]");
//            }catch (Throwable ex){
//                SwingUtils.showErrorPopup(ex);
//            }
//        }
//        return labelSetting;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}