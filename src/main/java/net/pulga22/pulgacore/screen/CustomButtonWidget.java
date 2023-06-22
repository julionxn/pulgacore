package net.pulga22.pulgacore.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class CustomButtonWidget extends PressableWidget {

    protected final Identifier texture;
    protected final PressAction onPress;
    protected final NarrationSupplier narrationSupplier;
    protected final SliceContext sliceContext;

    protected CustomButtonWidget(Identifier texture, int x, int y, int width, int height, Text message, PressAction onPress, NarrationSupplier narrationSupplier, SliceContext sliceContext) {
        super(x, y, width, height, message);
        this.onPress = onPress;
        this.narrationSupplier = narrationSupplier;
        this.texture = texture;
        this.sliceContext = sliceContext;
    }

    @Override
    public void renderButton(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        drawContext.drawNineSlicedTexture(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight(), sliceContext.left(), sliceContext.top(), sliceContext.right(), sliceContext.bot(), 200, 20, 0, this.getTextureY());
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        int i = this.active ? 0xFFFFFF : 0xA0A0A0;
        this.drawMessage(drawContext, minecraftClient.textRenderer, i | MathHelper.ceil(255.0f) << 24);
    }

    public void drawMessage(DrawContext drawContext, TextRenderer textRenderer, int color) {
        this.drawScrollableText(drawContext, textRenderer, 2, color);
    }

    private int getTextureY() {
        int i = 1;
        if (!this.active) {
            i = 0;
        } else if (this.isHovered()) {
            i = 2;
        }
        return i * 20;
    }

    public static Builder builder(Identifier identifier, Text message, PressAction onPress) {
        return new Builder(identifier, message, onPress);
    }

    @Environment(value= EnvType.CLIENT)
    public static class Builder {
        private final Identifier texture;
        private final Text message;
        private final PressAction onPress;
        @Nullable
        private Tooltip tooltip;
        private int x;
        private int y;
        private SliceContext sliceContext = new SliceContext(1, 1, 1, 1);
        private int width = 150;
        private int height = 20;
        private NarrationSupplier narrationSupplier = textSupplier -> (MutableText)textSupplier.get();

        public Builder(Identifier texture, Text message, PressAction onPress) {
            this.texture = texture;
            this.message = message;
            this.onPress = onPress;
        }

        public Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder dimensions(int x, int y, int width, int height) {
            return this.position(x, y).size(width, height);
        }

        public Builder tooltip(@Nullable Tooltip tooltip) {
            this.tooltip = tooltip;
            return this;
        }

        public Builder narrationSupplier(NarrationSupplier narrationSupplier) {
            this.narrationSupplier = narrationSupplier;
            return this;
        }

        /**
         * @param sliceContext A new SliceContext indicating the pixels that is going to be grabbed of the nineSlicedTexture
         * @return The builder
         */
        public Builder sliceContext(SliceContext sliceContext){
            this.sliceContext = sliceContext;
            return this;
        }

        public CustomButtonWidget build() {
            CustomButtonWidget buttonWidget = new CustomButtonWidget(this.texture, this.x, this.y, this.width, this.height, this.message, this.onPress, this.narrationSupplier, this.sliceContext);
            buttonWidget.setTooltip(this.tooltip);
            return buttonWidget;
        }
    }

    @Override
    public void onPress() {
        this.onPress.onPress(this);
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        this.appendDefaultNarrations(builder);
    }

    @Environment(value=EnvType.CLIENT)
    public static interface PressAction {
        public void onPress(CustomButtonWidget var1);
    }

    @Environment(value=EnvType.CLIENT)
    public static interface NarrationSupplier {
        public MutableText createNarrationMessage(Supplier<MutableText> var1);
    }

    public record SliceContext(int left, int top, int right, int bot) { }
}
