package object;

import java.util.Objects;
import java.util.logging.Logger;

public class OBJ_Chest extends SuperObject {

    private static final Logger LOGGER = Logger.getLogger(OBJ_Chest.class.getName());

    public OBJ_Chest() {

        name = "Chest";

        try {
            objectImage = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResource("/objects/chest.png")));
        } catch (Exception e) {
            LOGGER.severe("Failed to load image for OBJ_Door: " + e.getMessage());
        }
    }
}
