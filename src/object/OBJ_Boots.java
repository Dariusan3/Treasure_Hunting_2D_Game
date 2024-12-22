package object;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class OBJ_Boots extends SuperObject {

    private static final Logger LOGGER = Logger.getLogger(OBJ_Boots.class.getName());

    public OBJ_Boots() {

        name = "Boots";
        try {
            objectImage = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResource("/objects/boots.png")));
        } catch (IOException e) {
            LOGGER.severe("Failed to load image for OBJ_Boots: " + e.getMessage());
        }
    }
}
