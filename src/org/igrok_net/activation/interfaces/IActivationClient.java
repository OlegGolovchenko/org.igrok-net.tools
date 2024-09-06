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
