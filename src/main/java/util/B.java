package util;

import abi.generic.memory.*;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Created by jamesrichardson on 2/10/16.
 *
 * Byte Utils
 */
public class B {

    private B(){}

    public static byte[] byteToBytes(byte b){
        final byte[] bytes = {b};
        return bytes;
    }

    public static byte[] intToBytes(int in,ByteOrder order){
        return ByteBuffer.allocate(Integer.BYTES).order(order).putInt(in).array();
    }

    public static int bytesToInt(byte [] in,ByteOrder order){

        byte[] tmp;
        if(order == ByteOrder.LITTLE_ENDIAN) {
            tmp = in;
        }else{
            tmp = flipByteOrder(in);
        }
        int ret = 0;
        for(int i=0;i<in.length;i++){
            ret |= (tmp[i] & 0xFF) << (i*8);
        }
        return ret;
    }

    public static byte[] doubleToBytes(double in,ByteOrder order){
        return ByteBuffer.allocate(Double.BYTES).order(order).putDouble(in).array();
    }

    public static double bytesToDouble(byte[] in,ByteOrder order){
        byte[] tmp = new byte[Double.BYTES];
        Arrays.fill(tmp, (byte)0x0);
        if(order == ByteOrder.BIG_ENDIAN) {
            for (int i = tmp.length-1; i >= 0; i--) {
                tmp[i] = in[i];
            }
        }else{
            for (int i = 0; i <tmp.length; i++) {
                tmp[i] = in[i];
            }
        }
        return ByteBuffer.wrap(tmp).getDouble();
    }

    public static byte[] longToBytes(long in,ByteOrder order){
        return ByteBuffer.allocate(Long.BYTES).order(order).putDouble(in).array();
    }

    public static long bytesToLong(byte[] in,ByteOrder order){
        byte[] tmp = new byte[Long.BYTES];
        Arrays.fill(tmp, (byte)0x0);
        if(order == ByteOrder.BIG_ENDIAN) {
            for (int i = tmp.length-1; i >= 0; i--) {
                tmp[i] = in[i];
            }
        }else{
            for (int i = 0; i <tmp.length; i++) {
                tmp[i] = in[i];
            }
        }
        return ByteBuffer.wrap(tmp).getLong();
    }

    public static int longToInt(long in){
        return java.lang.Math.toIntExact(in);
    }

    public static byte[] stringToBytes(String in){
        return DatatypeConverter.parseHexBinary(in.replaceAll("0x",""));
    }

    public static String bytesToString(byte[] in){
        return DatatypeConverter.printHexBinary(in);
    }

    public static boolean equals(final byte[] one,final byte[] two){

        if(one.length != two.length)
            return false;

        for(int i=0;i<one.length;i++){
            if(one[i] != two[i])
                return false;
        }

        return true;
    }

    public static boolean equalsIgnoreLength(final byte[] one,final byte[] two){
        if(one.length <= two.length){
            for(int i=0;i<one.length;i++){
                if(one[i] != two[i])
                    return false;
            }
        }else{
            for(int i=0;i<two.length;i++){
                if(one[i] != two[i])
                    return false;
            }
        }
        return true;
    }

    public static Word getWordAtAddress(final byte[] raw, final Address address, ByteOrder resultOrder){
        final int addr = bytesToInt(address.getContainer(),address.BYTEORDER);

        return new Word(raw[addr],raw[addr+1],resultOrder);
    }

    public static DWord getDWordAtAddress(final byte[] raw, final Address address, ByteOrder resultOrder){
        final int addr = bytesToInt(address.getContainer(),address.BYTEORDER);

        return new DWord(raw[addr],raw[addr+1],raw[addr+2],raw[addr+3],resultOrder);
    }

    public static QWord getQWordAtAddress(final byte[] raw, final Address address, ByteOrder resultOrder){
        final int addr = bytesToInt(address.getContainer(),address.BYTEORDER);

        return new QWord(raw[addr],raw[addr+1],raw[addr+2],raw[addr+3],raw[addr+4],raw[addr+5],raw[addr+6],raw[addr+7],resultOrder);
    }

    public static byte[] mergeBytes(byte[] first, byte[] last){
        byte[] join = new byte[first.length+last.length];
        for(int i=0;i<first.length;i++){
            join[i]=first[i];
        }
        for(int i=first.length;i<join.length;i++){
            join[i]=last[i-first.length];
        }
        return join;
    }

    public static byte[] flipByteOrder(byte[] in){
        byte[] ret = new byte[in.length];
        Arrays.fill(ret,(byte)0x00);

        for(int i=0;i<in.length;i++){
            ret[i]=in[(in.length-1)-i];
        }

        return ret;
    }

//    public static byte[] getWordAtAddress(final byte[] raw,final byte[] address){
//
//        final byte[] ret = new byte[2];
//        final int addr = longToInt(bytesToLong(address,ByteOrder.BIG_ENDIAN));
//
//        ret[0] = raw[addr];
//        ret[1] = raw[addr+1];
//
//        return ret;
//    }
//
//    public static byte[] getDWordAtAddress(final byte[] raw, final byte[] address){
//
//        final byte[] ret = new byte[4];
//        final int addr = longToInt(bytesToLong(address,ByteOrder.BIG_ENDIAN));
//
//        ret[0] = raw[addr];
//        ret[1] = raw[addr+1];
//        ret[2] = raw[addr+2];
//        ret[3] = raw[addr+3];
//
//        return ret;
//    }
//
//
//    public static byte[] getQWordAtAddress(final byte[] raw,final byte[] address){
//
//        final byte[] ret = new byte[8];
//        final int addr = longToInt(bytesToLong(address,ByteOrder.BIG_ENDIAN));
//
//        ret[0] = raw[addr];
//        ret[1] = raw[addr+1];
//        ret[2] = raw[addr+2];
//        ret[3] = raw[addr+3];
//        ret[4] = raw[addr+4];
//        ret[5] = raw[addr+5];
//        ret[6] = raw[addr+6];
//        ret[7] = raw[addr+7];
//
//        return ret;
//    }
}
