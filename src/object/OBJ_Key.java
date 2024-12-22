package object;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class OBJ_Key extends SuperObject {

    private static final Logger LOGGER = Logger.getLogger(OBJ_Key.class.getName());

    public OBJ_Key() {

        name = "Key";
        try {
            objectImage = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResource("/objects/key.png")));
        } catch (IOException e) {
            LOGGER.severe("Failed to load image for OBJ_Key: " + e.getMessage());
        }
    }
}
