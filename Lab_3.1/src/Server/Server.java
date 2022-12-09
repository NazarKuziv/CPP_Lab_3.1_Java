package Server;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame{

    ServerSocket ss;
    Socket s;
    ObjectOutputStream out;
    ObjectInputStream in;

    private JPanel server_panel;
    private JTextArea Result;

    public Server() throws IOException {
            initComponents();
            ss = new ServerSocket(9999);
            s = ss.accept();
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());

    }

    private void initComponents() {
        setContentPane(server_panel);
        setTitle("Server");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setSize(600,300);
        setVisible(true);

    }
    private void SendMessage(String msg) {

        try {
            out.writeObject(msg);

        } catch (Exception e) {
        }
    }

    private void ReciveMessage() {
        String msg;
        while (true) {
            try {
                msg = (String) in.readObject();
                Result.append("Client: " + msg+"\n");

                StringBuilder sb = new StringBuilder(msg);
                int index = sb.length() / 2;;
                if(sb.length()%2 == 0)
                {
                    sb.deleteCharAt(index-1);
                    sb.deleteCharAt(index-1);
                }
                else
                {
                    sb.deleteCharAt(index);
                }


                msg = sb.toString();
                Result.append("Result: " + msg+"\n");
                SendMessage("Server: " + msg);

            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server ser = new Server();
        ser.ReciveMessage();

    }
}
