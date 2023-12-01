package me.qwq.utils;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class SwingUtils {
    public static void centerFrame(JFrame jFrame){
        jFrame.setLocationRelativeTo(null);
    }

    private static String getStackTraceText(Throwable ex){
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString().replace("\t", " ");
    }

    public static void showErrorPopup(Throwable ex){
        JTextArea textArea = new JTextArea(getStackTraceText(ex));
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));

        JScrollPane errorScrollPane = new JScrollPane(textArea);
        errorScrollPane.setPreferredSize(new Dimension(600, 400));
        JOptionPane.showMessageDialog(null, errorScrollPane, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
