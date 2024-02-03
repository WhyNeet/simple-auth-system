package dev.whyneet.authsystem.artwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Artwork {
    private final Boolean[][] board;

    public Artwork(int x, int y) {
        this.board = new Boolean[y][x];

        Arrays.stream(this.board).forEach(line -> Arrays.fill(line, false));
    }

    public void draw(Drawable drawable) {
        drawable.draw(board);
    }

    public Boolean[][] getBoard() {
        return board;
    }

    public String export(String empty, String filled) {
        StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(this.board).forEach(line -> stringBuilder.append(
                Arrays.stream(line)
                        .map(el -> el ? filled : empty)
                        .collect(Collectors.joining())
        ).append("\n"));

        return stringBuilder.toString();
    }

    public String export() {
        return export("_", "#");
    }
}
