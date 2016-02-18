package abi.pe;

import abi.generic.ABIType;

/**
 * Created by jamesrichardson on 2/10/16.
 */
public class PE64 extends PE {

    private static final ABIType ABI_TYPE = ABIType.PE_64;

    public PE64(byte[] binary) {
        super(binary);
    }

    @Override
    public ABIType getArch() {
        return ABI_TYPE;
    }

    @Override
    public byte[] getRaw() {
        return raw;
    }

}