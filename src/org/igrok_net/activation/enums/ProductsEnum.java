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
package org.igrok_net.activation.enums;

public enum ProductsEnum {

    None(0),
    IGNValidator(1),
    IGNQuery(2),
    IGNLogin(4),
    ASME_BASE(8),
    ASME_SERVER(ProductsEnum.ASME_BASE.getValue() | ProductsEnum.IGNQuery.getValue()
            | ProductsEnum.IGNValidator.getValue()),
    IGNLoginBundle(
            ProductsEnum.IGNLogin.getValue() | ProductsEnum.IGNQuery.getValue() | ProductsEnum.IGNValidator.getValue()),
    AllFree(ProductsEnum.None.getValue() | ProductsEnum.IGNValidator.getValue() | ProductsEnum.IGNQuery.getValue()
            | ProductsEnum.IGNLogin.getValue() | ProductsEnum.ASME_BASE.getValue());

    private final int bitMask;

    ProductsEnum(int bitMask) {
        this.bitMask = bitMask;
    }

    public int getValue() {
        return bitMask;
    }

    public String getFormattedValue() {
        return String.format("%05d", bitMask);
    }

    public boolean hasFlag(ProductsEnum flag) {
        return (getValue() & flag.getValue()) == flag.getValue();
    }

    // Static method to get enum by integer value
    public static ProductsEnum fromValue(int bitMask) {
        for (ProductsEnum status : ProductsEnum.values()) {
            if (status.getValue() == bitMask) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + bitMask);
    }
}
