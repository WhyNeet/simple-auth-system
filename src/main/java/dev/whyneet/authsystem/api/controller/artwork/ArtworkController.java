package dev.whyneet.authsystem.api.controller.artwork;

import dev.whyneet.authsystem.api.exception.ApiError;
import dev.whyneet.authsystem.api.security.JwtService;
import dev.whyneet.authsystem.artwork.Artwork;
import dev.whyneet.authsystem.artwork.Drawable;
import dev.whyneet.authsystem.artwork.objects.Rectangle;
import dev.whyneet.authsystem.artwork.objects.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

enum ArtworkShape {
    Rectangle,
    Triangle
}

@RestController
@RequestMapping("/artwork")
public class ArtworkController {
    @Autowired
    private JwtService jwtService;

    @GetMapping(value = {"/generate/{shape}", "/generate/{shape}/{empty}/{filled}"})
    public ResponseEntity<Object> generateArtwork(@CookieValue("access_token") String accessToken, @PathVariable("shape") String shape, @PathVariable(value = "empty", required = false) String empty, @PathVariable(value = "filled", required = false) String filled) {
        try {
            jwtService.extractAllClaims(accessToken);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiError("invalid access_token provided.", e.toString()));
        }

        ArtworkShape artworkShape = ArtworkShape.valueOf(shape);

        Artwork artwork = new Artwork(10, 10);

        Drawable drawable = null;

        switch (artworkShape) {
            case ArtworkShape.Rectangle -> drawable = new Rectangle(3, 3, 4, 4);
            case ArtworkShape.Triangle -> drawable = new Triangle(3, 3, 4);
        }

        artwork.draw(drawable);

        final String result = empty != null && filled != null ? artwork.export(empty, filled) :  artwork.export();

        return ResponseEntity.ok(result);
    }
}