package org.example.entity;

import java.io.Serializable;

public record SingerSummary(String firstName, String lastName, String latestAlbum) implements Serializable {
}
