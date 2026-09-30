package clownish.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ClownMenuScreen extends Screen {

    public static boolean freeCam = false;
    public static boolean chestEsp = false;
    public static boolean chestTracers = false;

    public ClownMenuScreen() {
        super(Text.literal("ClownClient"));
    }

    @Override
    protected void init() {

        int centerX = this.width / 2;
        int startY = this.height / 2 - 50;

        this.addDrawableChild(
            ButtonWidget.builder(
                Text.literal("Free Cam: " + (freeCam ? "ON" : "OFF")),
                button -> {
                    freeCam = !freeCam;
                    button.setMessage(
                        Text.literal("Free Cam: " + (freeCam ? "ON" : "OFF"))
                    );
                }
            ).dimensions(centerX - 75, startY, 150, 20).build()
        );

        this.addDrawableChild(
            ButtonWidget.builder(
                Text.literal("Chest ESP: " + (chestEsp ? "ON" : "OFF")),
                button -> {
                    chestEsp = !chestEsp;
                    button.setMessage(
                        Text.literal("Chest ESP: " + (chestEsp ? "ON" : "OFF"))
                    );
                }
            ).dimensions(centerX - 75, startY + 25, 150, 20).build()
        );

        this.addDrawableChild(
            ButtonWidget.builder(
                Text.literal("Chest Tracers: " + (chestTracers ? "ON" : "OFF")),
                button -> {
                    chestTracers = !chestTracers;
                    button.setMessage(
                        Text.literal(
                            "Chest Tracers: " +
                            (chestTracers ? "ON" : "OFF")
                        )
                    );
                }
            ).dimensions(centerX - 75, startY + 50, 150, 20).build()
        );
    }

    @Override
    public void render(
        DrawContext context,
        int mouseX,
        int mouseY,
        float delta
    ) {
        this.renderBackground(context, mouseX, mouseY, delta);

        int centerX = this.width / 2;

        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("ClownClient"),
            centerX,
            this.height / 2 - 85,
            0xFFFFFF
        );

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
