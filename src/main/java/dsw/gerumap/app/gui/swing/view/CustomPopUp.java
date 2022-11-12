package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
@Getter
@Setter
public class CustomPopUp {

    JTextField name = new JTextField();
    JTextField author = new JTextField();
    JComponent[] inputs;


    public int makePopUp(MapNode mapNode){

        if (mapNode instanceof ProjectExplorer){
            inputs = new JComponent[] {
                    new JLabel("Ime"),
                    name,
                    new JLabel("Autor"),
                    author,

            };
        }
        else {
            inputs = new JComponent[] {
                    new JLabel("Ime"),
                    name,
            };
        }


        return JOptionPane.showConfirmDialog(MainFrame.getIntance(), inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
    }


}
