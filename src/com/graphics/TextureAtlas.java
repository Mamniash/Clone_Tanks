package com.graphics;

import com.utils.ResourceLoader;

import java.awt.image.BufferedImage;

public class TextureAtlas {

    BufferedImage image;

    public TextureAtlas(String textureName) {
        image = ResourceLoader.loadImage(textureName);
    }

    public BufferedImage cut(int x, int y, int w, int h) {
        return image.getSubimage(x, y, w, h);
    }
}
