package sample;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPmessage {

    //fields
    private String time;
    private String message;
    private String ip;
    private int length;
    private int port;

    //constructor for UDPmessage class
    public UDPmessage(String message, String ip, int port)
    {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date time = new Date();
        this.time =formatter.format(time);
        this.message = message;
        this.ip = ip;
        this.length = message.length();
        this.port = port;

    }

    //methods

    public UDPmessage(byte[] message, int msgLength, InetAddress ip, int port)
    {
        //call other constructor
        this(new String(message, 0, msgLength), ip.getHostAddress(), port );
    }

    //getters
    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getIp() {
        return ip;
    }

    public String getIpAndString()
    {
        return ip + ":" + port;
    }

    public int getLength() {
        return length;
    }


    //toString method

    @Override
    public String toString() {
        return "UdpMessage{" +
                "time='" + time + '\'' +
                ", message='" + message + '\'' +
                ", ip='" + ip + '\'' +
                ", length=" + length +
                ", port=" + port +
                '}';
    }


}
