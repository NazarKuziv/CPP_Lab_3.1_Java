package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends JFrame {

    Socket s;
    ObjectOutputStream out;
    ObjectInputStream in;
    private JPanel client_panel;
    private JTextArea textArea;
    private JButton Send_button;
    private JLabel Result_lb;

    public Client() throws IOException {

        initComponents();
        s = new Socket("localhost", 9999);
        out = new ObjectOutputStream(s.getOutputStream());
        in = new ObjectInputStream(s.getInputStream());

    }

    private void initComponents() {
        setContentPane(client_panel);
        setTitle("Client");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setSize(600,300);
        setVisible(true);

        Send_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendMessage(textArea.getText());

            }
        });
    }
    private void SendMessage(String msg) {

        try {
            out.writeObject(msg);
        } catch (Exception e) {
        }
    }

    private void ReciveMessage() {

        while (true) {
            try {
                Result_lb.setText((String) in.readObject());

            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Client c = new Client();
        c.ReciveMessage();

    }
}
