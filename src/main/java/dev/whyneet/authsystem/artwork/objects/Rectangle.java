package dev.whyneet.authsystem.artwork.objects;

import dev.whyneet.authsystem.artwork.Drawable;

public class Rectangle implements Drawable {
    private final int x;
    private final int y;
    private final int sizeX;
    private final int sizeY;

    public Rectangle(int x, int y, int sizeX, int sizeY) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public void draw(Boolean[][] board) {
        if (y >= board.length || y + sizeY >= board.length || x >= board[0].length || x + sizeX >= board[0].length) throw new IllegalArgumentException(String.format("board is too small to draw this Rectangle: (%s, %s) < (%s, &s)", board[0].length, board.length, x + sizeX , y + sizeY));

        for (int dy = 0; dy < sizeY; dy++) {
            for (int dx = 0; dx < sizeX; dx++) {
                board[dy + y][dx + x] = true;
            }
        }
    }
}
