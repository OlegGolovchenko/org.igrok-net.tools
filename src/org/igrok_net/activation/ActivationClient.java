package org.igrok_net.activation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.igrok_net.activation.enums.ProductsEnum;
import org.igrok_net.activation.interfaces.IActivationClient;
import org.igrok_net.hasher.PublicApi;
import org.igrok_net.hasher.interfaces.IKeygen;

public class ActivationClient implements IActivationClient {

    private IKeygen keygen;
    private ProductsEnum activeProducts;
    private String email;

    public ActivationClient() {
        super();
    }

    private void decodeProducts(String key) {
        String[] keyParts = key.split("-");
        if (keyParts.length != 5) {
            this.activeProducts = ProductsEnum.None;
            return;
        }
        try {
            int prodPos = Integer.parseInt(keyParts[0].substring(2, 3));
            this.activeProducts = ProductsEnum.fromValue(Integer.parseInt(keyParts[prodPos]));
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            this.activeProducts = ProductsEnum.None;
        }
    }

    @Override
    public void init(String email) {
        this.keygen = new PublicApi().getKeygen(email);
        this.email = email;
    }

    @Override
    public void init(String email, String key) {
        init(email);
        decodeProducts(key);
    }

    @Override
    public void register(ProductsEnum product, String key) {
        if (!this.isRegistered(product)) {
            if (this.keygen.validateKey(key, product.getFormattedValue())) {
                decodeProducts(key);
            }
        }
    }

    @Override
    public void register(ProductsEnum product, Path licFile) {
        try {
            String key = Files.readAllLines(licFile).get(0);
            register(product, key);
        } catch (IOException e) {

        }
    }

    @Override
    public boolean isRegistered(ProductsEnum product) {
        return this.activeProducts.hasFlag(product);
    }

}
