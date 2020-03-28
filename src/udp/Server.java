package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.LinkedList;
import java.util.List;

public class Server {

    private List<Client> clients;

    public Server() throws IOException {

        //Initialisieren der Bestandteile
        clients = new LinkedList<>();
        DatagramSocket soc = new DatagramSocket(1337);
        byte[] buffer;

        //Runtime des Servers
        while (true) {
            buffer = new byte[32];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            soc.receive(packet);

            //Liste nach Übereinstimmungen durchsuchen
            for (int i = 0; i < clients.size(); i++) {

                //Liste ist leer
                if (clients.isEmpty()) {
                    Client client = new Client(packet.getAddress(), packet.getPort());
                    clients.add(client);
                    break;

                    //Liste enthält bereits Client
                } else if (clients.get(i).getAddress() == packet.getAddress() && clients.get(i).getPort() == packet.getPort()) {
                    break;

                    //letzter Eintrag der Liste
                } else if ((i+1) == clients.size()) {
                    if (clients.get(i).getAddress() == packet.getAddress() && clients.get(i).getPort() == packet.getPort()) {
                        break;
                    } else {
                        Client client = new Client(packet.getAddress(), packet.getPort());
                        clients.add(client);
                    }
                }

            }

            if (clients.isEmpty() == false) {
                for (int j = 0; j < clients.size(); j++) {
                    DatagramPacket forwarding = new DatagramPacket(packet.getData(), packet.getLength(), clients.get(j).getAddress(), clients.get(j).getPort());
                    soc.send(forwarding);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}
