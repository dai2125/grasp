package com.aigner.grasp.jSerialComm;

import com.fazecast.jSerialComm.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;

public class DummySerialSender {

    private static DummySerialSender instance;
    private SerialPort serialPort;
    private OutputStream outputStream;

    private final Logger LOGGER = LoggerFactory.getLogger(DummySerialReceiver.class);

    public DummySerialSender() {

        serialPort = SerialPort.getCommPort("COM1");
        serialPort.setComPortParameters(9600, 8, 1, 0);  // Baudrate: 9600, 8 Datenbits, 1 Stoppbit, keine Parität
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
    }

    public static DummySerialSender getInstance() {
        if(instance == null) {
            instance = new DummySerialSender();
        }
        return instance;
    }

    public boolean openPort() {
        if (serialPort.openPort()) {
            System.out.println("Port geöffnet: " + serialPort.getSystemPortName());
            try {
                outputStream = serialPort.getOutputStream();
                return true;
            } catch (Exception exception) {
                exception.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Fehler beim öffnen des Ports, Sender");
            return false;
        }
    }

    public void sendData(byte[] data) {
        try {
            if (outputStream != null) {
                outputStream.write(data);
                outputStream.flush();
            }
        } catch (Exception exception) {
            LOGGER.error("An error occurred while sending data from serial sender", exception);
        }
    }

    public void closePort() {
        try {
            if (serialPort != null && serialPort.isOpen()) {
                serialPort.closePort();
                System.out.println("Port geschlossen");
            }
        } catch (Exception exception) {
            LOGGER.error("An error occurred while closing port from serial sender", exception);
        }
    }

    public byte[] createMessage(byte information) {
        byte[] header = new byte[]{(byte) 0b11111111};
        byte[] info = new byte[]{information};
        byte[] footer = new byte[]{(byte) 0b11111111};

        byte[] message = new byte[header.length + info.length + footer.length];

        System.arraycopy(header, 0, message, 0, header.length);
        System.arraycopy(info, 0, message, header.length, info.length);
        System.arraycopy(footer, 0, message, header.length + info.length, footer.length);

//        System.out.println("Byte[]: " + message[0]);
//        System.out.println("Byte[]: " + message[1]);
//        System.out.println("Byte[]: " + message[message.length - 1]);
        return message;
    }

    public byte[] createMessage(byte[] device, byte[] command) {
        byte[] header = new byte[]{(byte) 0b11111111};
        byte[] info = device;
        byte[] footer = command;

        byte[] message = new byte[header.length + device.length + command.length];

        System.arraycopy(header, 0, message, 0, header.length);
        System.arraycopy(info, 0, message, header.length, device.length);
        System.arraycopy(footer, 0, message, header.length + device.length, command.length);

        return message;
    }

    public byte[] createMessage(byte[] device, byte[] command, byte[] item) {
        byte[] header = new byte[]{(byte) 0b11111111};

        byte[] message = new byte[header.length + device.length + command.length + item.length];

        System.arraycopy(header, 0, message, 0, header.length);
        System.arraycopy(device, 0, message, header.length, device.length);
        System.arraycopy(command, 0, message, header.length + device.length, command.length);
        System.arraycopy(item, 0, message, header.length + device.length + command.length, item.length);

        System.out.println("DummySerialSender createMessage: message.length " + message.length);

        return message;
    }
}
