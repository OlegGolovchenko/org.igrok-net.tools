/*
 *  IgRok-NET tools
 *  Copyright (C) 2023  Oleg Golovchenko
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.igrok_net.tools.email;

import java.nio.file.Path;
import org.igrok_net.activation.ActivationClient;
import org.igrok_net.activation.enums.ProductsEnum;
import org.igrok_net.activation.interfaces.IActivationClient;

/**
 * Email validator class
 */
public class EmailValidator {

    private static IActivationClient client;
    private static String activationEmail;
    private static boolean activated;

    static {
        client = null;
        activated = false;
        activationEmail = "";
    }

    public static void activate(String email, String key, Path fileLocation) {
        activationEmail = email;
        if (client == null) {
            client = new ActivationClient();
            client.init(email, key);
        }
        if (!(key.isEmpty() || key == null)) {
            client.register(ProductsEnum.IGNValidator, key);
        } else {
            if (fileLocation != null) {
                client.register(ProductsEnum.IGNValidator, fileLocation);
            }
        }
        activated = client.isRegistered(ProductsEnum.IGNValidator);
    }

    /**
     * Email validation method.
     * Validates format of email according to RFC 5321, 5322.
     * 
     * @param email email address to check
     * @return true if correctly formatted, false otherwise
     * @throws IllegalStateException if validator was not activated
     */
    public static boolean ValidateEmail(String email) throws IllegalStateException {
        if (client == null || (client != null && !activated)) {
            throw new IllegalStateException(
                    "Please call activate or use -a <email> <key> arguments before using product");
        }
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("can not validate empty email");
        if (email.length() > 254)
            return false;
        if (!email.contains("@"))
            return false;
        String[] emailSplit = email.split("@");
        if (emailSplit.length < 2)
            return false;
        String domain = emailSplit[emailSplit.length - 1];
        if (!domain.contains("."))
            return false;
        if (domain.startsWith("-"))
            return false;
        if (!domain.matches("^[A-z0-9-.]+$"))
            return false;
        String[] domainParts = domain.split(".");
        if (domainParts.length == 4) {
            if (domain.matches("^[0-9.]+$")) {
                if (domain.contains("[") || domain.contains("]")) {
                    for (String item : domainParts) {
                        if (item.contains("[") || item.contains("]")) {
                            if (item.length() > 5)
                                return false;
                        } else {
                            if (item.length() > 3)
                                return false;
                        }
                    }
                } else {
                    for (String item : domainParts) {
                        if (item.length() > 3)
                            return false;
                    }
                }
            }
        }

        StringBuilder addressBuilder = new StringBuilder();
        String address = "";

        if (emailSplit.length > 2) {
            for (int i = 0; i < emailSplit.length - 1; i++) {
                addressBuilder.append(emailSplit[i]);
                addressBuilder.append("@");
            }
            address = addressBuilder.toString();
        } else {
            address = emailSplit[0];
        }

        if (address == null || address.isEmpty())
            return false;

        if (!address.matches("^[a-z0-9.!#“”$%&'*+/=?^_`{|}~-]+$"))
            return false;

        if (address.contains("@"))
            return false;

        if (address.startsWith(".") || address.endsWith("."))
            return false;

        if (address.contains(".."))
            return false;

        return true;
    }
}
