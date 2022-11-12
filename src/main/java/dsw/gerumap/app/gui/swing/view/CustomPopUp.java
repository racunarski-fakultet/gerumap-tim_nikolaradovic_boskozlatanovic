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
                    new JLabel("Project"),
                    name,
                    new JLabel("Author"),
                    author,

            };
        }
        else {
            inputs = new JComponent[] {
                    new JLabel("Name"),
                    name,
            };
        }


        return JOptionPane.showConfirmDialog(MainFrame.getIntance(), inputs, "ProjectName and Author", JOptionPane.PLAIN_MESSAGE);
    }


}
