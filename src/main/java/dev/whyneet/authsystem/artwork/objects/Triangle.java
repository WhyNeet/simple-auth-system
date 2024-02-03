package dev.whyneet.authsystem.artwork.objects;

import dev.whyneet.authsystem.artwork.Drawable;

public class Triangle implements Drawable {
    private final int anchorX;
    private final int anchorY;
    private final int size;

    public Triangle(int anchorX, int anchorY, int size) {
        this.anchorX = anchorX;
        this.anchorY = anchorY;
        this.size = size;
    }

    @Override
    public void draw(Boolean[][] board) {
        if (anchorX + size / 2 >= board[0].length || anchorY + size >= board.length) throw new IllegalArgumentException(String.format("board is too small to draw this Rectangle: (%s, %s) < (%s, &s)", board[0].length, board.length, anchorX + size / 2, anchorY + size));

        for (int dy = 0; dy < size; dy++) {
            for (int dx = 0; dx < dy; dx++) {
                board[anchorY + dy][anchorX - dx] = true;
                board[anchorY + dy][anchorX + dx] = true;
            }
        }
    }
}
