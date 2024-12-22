package object;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class OBJ_Door extends SuperObject {

    private static final Logger LOGGER = Logger.getLogger(OBJ_Door.class.getName());

    public OBJ_Door() {

        name = "Door";
        try {
            objectImage = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResource("/objects/door.png")));
        } catch (IOException e) {
            LOGGER.severe("Failed to load image for OBJ_Door: " + e.getMessage());
        }

        collision = true;
    }
}
