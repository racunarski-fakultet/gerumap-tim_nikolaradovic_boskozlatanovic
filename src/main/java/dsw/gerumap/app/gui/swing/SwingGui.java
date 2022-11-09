package dsw.gerumap.app.gui.swing;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.errorHandling.messageImplementation.Message;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;

public class SwingGui implements Gui, Subscriber {



    @Override
    public void start() {
        MainFrame.getIntance().setVisible(true);
    }

    @Override
    public void addToSubscriberList() {
        ((Publisher) AppCore.getInstance().getMessageGenerator()).addSubscriber(this);
    }

    @Override
    public void update(Object obj, Enum e) {
        JOptionPane.showMessageDialog(MainFrame.getIntance(),((Message)obj).getMessage(),((Message)obj).getEventType().toString().replace('_',' '),JOptionPane.ERROR_MESSAGE);
    }

}
