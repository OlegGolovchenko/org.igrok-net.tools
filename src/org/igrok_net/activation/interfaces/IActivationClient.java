package org.igrok_net.activation.interfaces;

import java.nio.file.Path;

import org.igrok_net.activation.enums.ProductsEnum;

public interface IActivationClient {

    void init(String email);

    void init(String email, String key);

    void register(ProductsEnum product, String key);

    void register(ProductsEnum product, Path licFile);

    boolean isRegistered(ProductsEnum product);
}
