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
