package com.demon.js_pdf.code;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;

public abstract class CharacterEncoder {
    protected PrintStream pStream;

    protected abstract int bytesPerAtom();

    protected abstract int bytesPerLine();

    protected void encodeBufferPrefix(OutputStream paramOutputStream)
            throws IOException {
        this.pStream = new PrintStream(paramOutputStream);
    }

    protected void encodeBufferSuffix(OutputStream paramOutputStream)
            throws IOException {
    }

    protected void encodeLinePrefix(OutputStream paramOutputStream, int paramInt)
            throws IOException {
    }

    protected void encodeLineSuffix(OutputStream paramOutputStream)
            throws IOException {
        this.pStream.println();
    }

    protected abstract void encodeAtom(OutputStream paramOutputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
            throws IOException;

    protected int readFully(InputStream paramInputStream, byte[] paramArrayOfByte)
            throws IOException {
        for (int i = 0; i < paramArrayOfByte.length; i++) {
            int j = paramInputStream.read();
            if (j == -1) {
                return i;
            }
            paramArrayOfByte[i] = ((byte) j);
        }
        return paramArrayOfByte.length;
    }

    public void encode(InputStream paramInputStream, OutputStream paramOutputStream)
            throws IOException {
        byte[] arrayOfByte = new byte[bytesPerLine()];

        encodeBufferPrefix(paramOutputStream);
        for (; ; ) {
            int j = readFully(paramInputStream, arrayOfByte);
            if (j == 0) {
                break;
            }
            encodeLinePrefix(paramOutputStream, j);
            for (int i = 0; i < j; i += bytesPerAtom()) {
                if (i + bytesPerAtom() <= j) {
                    encodeAtom(paramOutputStream, arrayOfByte, i, bytesPerAtom());
                } else {
                    encodeAtom(paramOutputStream, arrayOfByte, i, j - i);
                }
            }
            if (j < bytesPerLine()) {
                break;
            }
            encodeLineSuffix(paramOutputStream);
        }
        encodeBufferSuffix(paramOutputStream);
    }

    public void encode(byte[] paramArrayOfByte, OutputStream paramOutputStream)
            throws IOException {
        ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
        encode(localByteArrayInputStream, paramOutputStream);
    }

    public String encode(byte[] paramArrayOfByte) {
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
        String str = null;
        try {
            encode(localByteArrayInputStream, localByteArrayOutputStream);

            str = localByteArrayOutputStream.toString("8859_1");
        } catch (Exception localException) {
            throw new Error("CharacterEncoder.encode internal error");
        }
        return str;
    }

    private byte[] getBytes(ByteBuffer paramByteBuffer) {
        byte[] localObject = null;
        if (paramByteBuffer.hasArray()) {
            byte[] arrayOfByte = paramByteBuffer.array();
            if ((arrayOfByte.length == paramByteBuffer.capacity()) && (arrayOfByte.length == paramByteBuffer.remaining())) {
                localObject = arrayOfByte;
                paramByteBuffer.position(paramByteBuffer.limit());
            }
        }
        if (localObject == null) {
            localObject = new byte[paramByteBuffer.remaining()];


            paramByteBuffer.get((byte[]) localObject);
        }
        return localObject;
    }

    public void encode(ByteBuffer paramByteBuffer, OutputStream paramOutputStream)
            throws IOException {
        byte[] arrayOfByte = getBytes(paramByteBuffer);
        encode(arrayOfByte, paramOutputStream);
    }

    public String encode(ByteBuffer paramByteBuffer) {
        byte[] arrayOfByte = getBytes(paramByteBuffer);
        return encode(arrayOfByte);
    }

    public void encodeBuffer(InputStream paramInputStream, OutputStream paramOutputStream)
            throws IOException {
        byte[] arrayOfByte = new byte[bytesPerLine()];

        encodeBufferPrefix(paramOutputStream);
        for (; ; ) {
            int j = readFully(paramInputStream, arrayOfByte);
            if (j != 0) {
                encodeLinePrefix(paramOutputStream, j);
                for (int i = 0; i < j; i += bytesPerAtom()) {
                    if (i + bytesPerAtom() <= j) {
                        encodeAtom(paramOutputStream, arrayOfByte, i, bytesPerAtom());
                    } else {
                        encodeAtom(paramOutputStream, arrayOfByte, i, j - i);
                    }
                }
                encodeLineSuffix(paramOutputStream);
                if (j < bytesPerLine()) {
                    break;
                }
            }
        }
        encodeBufferSuffix(paramOutputStream);
    }

    public void encodeBuffer(byte[] paramArrayOfByte, OutputStream paramOutputStream)
            throws IOException {
        ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
        encodeBuffer(localByteArrayInputStream, paramOutputStream);
    }

    public String encodeBuffer(byte[] paramArrayOfByte) {
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
        try {
            encodeBuffer(localByteArrayInputStream, localByteArrayOutputStream);
        } catch (Exception localException) {
            throw new Error("CharacterEncoder.encodeBuffer internal error");
        }
        return localByteArrayOutputStream.toString();
    }

    public void encodeBuffer(ByteBuffer paramByteBuffer, OutputStream paramOutputStream)
            throws IOException {
        byte[] arrayOfByte = getBytes(paramByteBuffer);
        encodeBuffer(arrayOfByte, paramOutputStream);
    }

    public String encodeBuffer(ByteBuffer paramByteBuffer) {
        byte[] arrayOfByte = getBytes(paramByteBuffer);
        return encodeBuffer(arrayOfByte);
    }
}
