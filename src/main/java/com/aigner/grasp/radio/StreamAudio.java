package com.aigner.grasp.radio;

import de.sfuhrm.radiobrowser4j.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StreamAudio {

    private static StreamAudio instance;
    AdvancedPlayer player;
    private Thread playThread;
    private String content;
    RadioHistory radioHistory;
    private int counter = 0;
    private ArrayList<String> radioList = new ArrayList<String>();


    public StreamAudio() {
//        playStream("http://stream4.nadaje.com:15476/radiobialystok");
        radioHistory = new RadioHistory();
        radioList.add("http://streamer.psyradio.org:8010/;listen.mp3");
        radioList.add("https://stream.rockantenne.de/alternative/stream/mp3");
        radioList.add("http://s25.myradiostream.com:10092/stream");
        radioList.add("http://stream.funradio.sk:8000/fun128.mp3");
        radioList.add("https://cast1.torontocast.com:2130/stream");
        radioList.add("https://rogers-hls.leanstream.co/rogers/nor1019.stream/icy?environment=tunein&args=tunein_01");

    }


    public static StreamAudio getInstance() {
        if(instance == null) {
            instance = new StreamAudio();
        }
        return instance;
    }

    public static void main(String[] args) {
        try {
//            playStream("http://stream4.nadaje.com:15476/radiobialystok");
            // Entdeckungsagent für die Endpunkte
            Optional<String> endpoint = new EndpointDiscovery("DemoAgent/1.0").discover();

            // RadioBrowser-Instanz erstellen
            RadioBrowser radioBrowser = new RadioBrowser(ConnectionParams.builder().apiUrl(endpoint.get()).userAgent("Demo agent/1.0").timeout(5000).build());

            // Radiostationen auflisten
            radioBrowser.listStations(ListParameter.create().order(FieldName.NAME))
//            radioBrowser.listStations(ListParameter.create().order(FieldName.name))
                    .limit(64)
                    .forEach(s -> {
                        System.out.printf("%s: %s%n", s.getName(), s.getUrl());
                        // Hier kannst du den Stream abspielen, z.B. mit einer Audio-Bibliothek
                        // playStream(s.getUrl()); // Beispiel-Funktion zum Abspielen
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void playStream(String streamUrl) {
        try {
            InputStream inputStream = new URL(streamUrl).openStream();
            AdvancedPlayer player = new AdvancedPlayer(inputStream);
            System.out.println("Playing: " + streamUrl);
            player.play();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void playStream() {
        String streamUrl = "http://stream4.nadaje.com:15476/radiobialystok";
        content = streamUrl;
        radioHistory.addMemento(createMemento());
        stopStream(); // Stop any running stream before starting a new one

        playThread = new Thread(() -> {
            try {
                InputStream inputStream = new URL(streamUrl).openStream();
                player = new AdvancedPlayer(inputStream);
                System.out.println("StreamAudio playStream: " + streamUrl);
                player.play();
            } catch (JavaLayerException | IOException e) {
                e.printStackTrace();
            }
        });
        playThread.start();
    }

    public void stopStream() {
        if (player != null) {
            player.close();
        }
        if (playThread != null && playThread.isAlive()) {
            playThread.interrupt();
        }
    }

    public void turnOff() {
        stopStream();
    }

    public void nextRadioStation() {
        if (radioList.size() >= counter + 1) {

            String radio = radioList.get(counter++);
            content = radio;
            radioHistory.addMemento(createMemento());

            stopStream();
            playThread = new Thread(() -> {
                try {
                    InputStream inputStream = new URL(radio).openStream();
                    player = new AdvancedPlayer(inputStream);
                    System.out.println("StreamAudio nextRadioStation: " + radio);
                    player.play();
                } catch (IOException | JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            });
            playThread.start();
        }
    }

    public void lastRadioStation() {
        System.out.println("StreamAudio lastRadioStation(): ");
        System.out.println("StreamAudio lastRadioStation() radioHistory.radioMementoList.size(): " + radioHistory.radioMementoList.size());

        int lastIndex = radioHistory.radioMementoList.size() - 1;


        if(lastIndex >= 0) {
            restoreFromMemento(radioHistory.getMemento(lastIndex));
            stopStream(); // Stop any running stream before starting a new one
            playThread = new Thread(() -> {
                try {
                    InputStream inputStream = new URL(content).openStream();
                    player = new AdvancedPlayer(inputStream);
                    System.out.println("StreamAudio lastRadioStation(): " + content);
                    player.play();
                } catch (JavaLayerException | IOException e) {
                    e.printStackTrace();
                }
            });
        }

        radioHistory.radioMementoList.removeLast();
        playThread.start();

    }

    public RadioMemento createMemento() {
        return new RadioMemento(this.content);
    }

    public void restoreFromMemento(RadioMemento radioMemento) {
        this.content = radioMemento.getSavedContent();
    }

}
