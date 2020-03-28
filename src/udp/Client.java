package udp;

import java.net.InetAddress;

public class Client {

    private InetAddress address;
    private int port;

    public Client(InetAddress inetAddress, int port){
        this.address = inetAddress;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
