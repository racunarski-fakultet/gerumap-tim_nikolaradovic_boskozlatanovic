package dsw.gerumap.app.gui.swing.tabbedPane.view;

import dsw.gerumap.app.gui.swing.tabbedPane.controller.MousePainter;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

@Getter
@Setter
public class TabItemModel {
    private MapNode mapNode;
    private JPanel panel;
    Random r = new Random();

    public TabItemModel(MapNode mapNode) {
        this.mapNode = mapNode;
        this.panel = new JPanel();
        panel.addMouseListener(new MousePainter(this));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setBackground(Color.WHITE);
      //  panel.add(new Label("string" + r.nextInt(100)));
    }
}
