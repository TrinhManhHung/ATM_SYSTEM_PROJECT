package atm;
//Chứa các nút nhấn, nhãn dán đã được thiết kế màu, kích thước sẵn 
//tạo sự đồng điệu cho project
/**
 *
 * @author HUNG
 */

import java.awt.*;
import javax.swing.*;

public class GUIConstants {
    
    public static Color backgroundColor = Color.decode("#F2EDE4"); //màu kem nhạt
    public static Color color2 = Color.decode("#2C3359"); //màu xanh dương đậm
    public static Color mainColor = Color.decode("#75B8BF"); //màu xanh nước biển
    public static Color foregroundColor = Color.decode("#E8F8F9"); //màu gần trắng
    
    public static JButton jButton(String content) {
        JButton btn = new JButton(content);
        btn.setBackground(mainColor);
        btn.setForeground(Color.white);
        btn.setFont(new Font("SansSerif", Font.BOLD, 23));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        return btn;
    }
    
    public static JTextField jTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font ("SansSerif", Font.BOLD, 23));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setForeground(color2);
        return textField;
    }
    
    public static JPasswordField jPasswordField() {
        JPasswordField textField = new JPasswordField();
        textField.setFont(new Font("SansSerif", Font.BOLD, 23));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setForeground(color2);
        return textField;
    }
    
    public static JLabel jLabel (String content, int textSize){
        JLabel label = new JLabel(content);
        label.setFont(new Font("SansSerif", Font.BOLD, textSize));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(color2);
        return label;
    }
}
