package dsw.gerumap.app.gui.swing.view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Getter
public class CustomDrawingPopUp {

    JTextField stroke = new JTextField();

    JButton selectColor = new JButton("Izaberite boju");

    Color c;

    JComponent[] inputs;

    public CustomDrawingPopUp() {
        selectColor.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                c = JColorChooser.showDialog(MainFrame.getIntance(), "Select Color", Color.BLUE);
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
        });
        inputs = new JComponent[] {
                new JLabel("Unesite debljinu linije"),
                stroke, selectColor};
    }

    public int makePopUp(){
        return JOptionPane.showConfirmDialog(MainFrame.getIntance(), inputs, "ProjectName and Author", JOptionPane.PLAIN_MESSAGE);
    }

}
