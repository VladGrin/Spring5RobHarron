package org.example.app.context.collections.util.xml;

public interface ArtworkSender {

    void sendArtwork(String artworkPath, Recipient recipient);

    String getFriendlyName();

    String getShortName();
}
