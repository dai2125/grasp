package com.aigner.grasp.jSerialComm;

import com.aigner.grasp.chainOfResponsibility.*;
import com.fazecast.jSerialComm.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
//import java.util.logging.Logger;

public class DummySerialReceiver implements Runnable {

    private SerialPort serialPort;
    private boolean running;
    private ValidationManager validationManager;

    private final Logger LOGGER = LoggerFactory.getLogger(DummySerialReceiver.class);


    public DummySerialReceiver() {
        // COM2 zum Empfangen verwenden
        serialPort = SerialPort.getCommPort("COM2");
        serialPort.setComPortParameters(9600, 8, 1, 0);  // Baudrate: 9600, 8 Datenbits, 1 Stoppbit, keine Parität
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 10000000, 0);
        validationManager = new ValidationManager();
    }

    // Methode zum Öffnen des Ports
    public boolean openPort() {
        if (serialPort.openPort()) {
            System.out.println("Port geöffnet: " + serialPort.getSystemPortName());
            return true;
        } else {
            System.out.println("Fehler beim Öffnen des Ports., Receiver");
            return false;
        }
    }

    // Methode zum Schließen des Ports
    public void closePort() {
        if (serialPort.isOpen()) {
            serialPort.closePort();
            System.out.println("Port geschlossen.");
        }
    }

    // Startet den Empfänger in einem neuen Thread
    public void start() {
        running = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try (InputStream in = serialPort.getInputStream()) {
            byte[] buffer = new byte[1024];
            int len;

            while (running && (len = in.read(buffer)) > 0) {
//                for (int i = 0; i < len; i++) {
//                    System.out.println("len: " + len);
                byte[] actualData = new byte[len];
                System.arraycopy(buffer, 0, actualData, 0, len);

                System.out.println("DummySerialReceiver run: actualData.length " + actualData.length);
                for(int i = 0; i < actualData.length; i++) {
                    System.out.print(actualData[i] + " ");
                }
                System.out.println();

                validationManager.validate(actualData);

                    // Debug-Ausgabe (optional)
//                    System.out.print(String.format("%8s", Integer.toBinaryString(buffer[i] & 0xFF)).replace(' ', '0') + " ");
//                }
                System.out.println();
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while reading from serial port", e);
        } finally {
            closePort();
        }
    }

    // Stoppt den Empfang
    public void stop() {
        running = false;
    }
}

//public class DummySerialReceiver {
//
//    public static void main(String[] args) {
//        // COM4 zum Empfangen verwenden
//        SerialPort serialPort = SerialPort.getCommPort("COM2");  // COM4 zum Empfangen
//        serialPort.setComPortParameters(9600, 8, 1, 0);  // Baudrate: 9600, 8 Datenbits, 1 Stoppbit, keine Parität
//        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
//
//        ValidationManager validationManager = new ValidationManager();
//
//        if (serialPort.openPort()) {
//            System.out.println("Port geöffnet: " + serialPort.getSystemPortName());
//
//            try (InputStream in = serialPort.getInputStream()) {
//                byte[] buffer = new byte[9];
//                int len;
//                System.out.println("Message received ");
//                while ((len = in.read(buffer)) > 0) {
//                    for(int i = 0; i < len; i++) {
//
//                        validationManager.validate(buffer);
//
////                        System.out.print(String.format("%8s", Integer.toBinaryString(buffer[i] & 0xFF)).replace(' ', '0') + " ");
//                    }
////                    System.out.println();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                serialPort.closePort();
//                System.out.println("Port geschlossen.");
//            }
//        } else {
//            System.out.println("Fehler beim Öffnen des Ports.");
//        }
//    }
//}
