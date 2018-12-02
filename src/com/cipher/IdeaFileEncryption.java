/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cipher;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;


public class IdeaFileEncryption {
private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
private static final int     blockSize = 8;




public  void cryptFile (String inputFileName, String outputFileName, String charKey, boolean encrypt) throws IOException {
   FileChannel inChannel = null;
   FileChannel outChannel = null;
   try {
      Idea idea = new Idea(charKey, encrypt);
      BlockStreamCrypter bsc = new BlockStreamCrypter(idea, encrypt);
      inChannel = FileChannel.open(Paths.get(inputFileName), StandardOpenOption.READ);
      long inFileSize = inChannel.size();
      long inDataLen;
      long outDataLen;
      if (encrypt) {
         inDataLen = inFileSize;
         outDataLen = (inDataLen + blockSize - 1) / blockSize * blockSize; }
       else {
         if (inFileSize == 0) {
            throw new IOException("Input file is empty."); }
         if (inFileSize % blockSize != 0) {
            throw new IOException("Input file size is not a multiple of " + blockSize + "."); }
         inDataLen = inFileSize - blockSize;
         outDataLen = inDataLen; }
      outChannel = FileChannel.open(Paths.get(outputFileName), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
      pumpData(inChannel, inDataLen, outChannel, outDataLen, bsc);
      if (encrypt) {
         writeDataLength(outChannel, inDataLen, bsc); }
       else {
         long outFileSize = readDataLength(inChannel, bsc);
         if (outFileSize < 0 || outFileSize > inDataLen || outFileSize < inDataLen - blockSize + 1) {
            throw new IOException("Input file is not a valid cryptogram."); }
         if (outFileSize != outDataLen) {
            outChannel.truncate(outFileSize); }}
      outChannel.close(); }
    finally {
      if (inChannel != null) {
         inChannel.close(); }
      if (outChannel != null) {
         outChannel.close(); }}}

private static class BlockStreamCrypter {
   Idea            idea;
   boolean         encrypt;
   byte[]          prev;                         // data of the previous ciphertext block
   byte[]          newPrev;
   BlockStreamCrypter (Idea idea, boolean encrypt) {
      this.idea = idea;
      this.encrypt = encrypt;
      prev = new byte[blockSize];
      newPrev = new byte[blockSize]; }
   void crypt (byte[] data, int pos) {
            idea.crypt(data, pos);
}}

private static void pumpData(FileChannel inChannel, long inDataLen, FileChannel outChannel, long outDataLen, BlockStreamCrypter bsc) throws IOException {
   final int bufSize = 0x200000;
   assert bufSize % blockSize == 0;
   ByteBuffer buf = ByteBuffer.allocate(bufSize);
   long filePos = 0;
   while (filePos < inDataLen) {
      int reqLen = (int)Math.min(inDataLen - filePos, bufSize);
      buf.position(0);
      buf.limit(reqLen);
      int trLen = inChannel.read(buf);
      if (trLen != reqLen) {
         throw new IOException("Incomplete data chunk read from file."); }
      int chunkLen = (trLen + blockSize - 1) / blockSize * blockSize;
      Arrays.fill(buf.array(), trLen, chunkLen, (byte)0);
      for (int pos = 0; pos < chunkLen; pos += blockSize) {
         bsc.crypt(buf.array(), pos); }
      reqLen = (int)Math.min(outDataLen - filePos, chunkLen);
      buf.position(0);
      buf.limit(reqLen);
      trLen = outChannel.write(buf);
      String cipher = bytesToHex(buf);
      ComCipher.write(cipher);
      if (trLen != reqLen) {
         throw new IOException("Incomplete data chunk written to file."); }
      filePos += chunkLen; }}


public static String bytesToHex(ByteBuffer byt) {
    byte[] bytes = byt.array();
   // byt.get(bytes, 0, bytes.length);
    char[] hexChars = new char[bytes.length *2];
    for ( int j = 0; j < bytes.length; j++ ) {
        int v = bytes[j] & 0xFF;
        hexChars[j * 2] = hexArray[v >>> 4];
        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
     char[] temp = Arrays.copyOf(hexChars, 10000);
    return new String(temp);
}
private static long readDataLength (FileChannel channel, BlockStreamCrypter bsc) throws IOException {
   ByteBuffer buf = ByteBuffer.allocate(blockSize);
   int trLen = channel.read(buf);
   if (trLen != blockSize) {
      throw new IOException("Unable to read data length suffix."); }
   byte[] a = buf.array();
   bsc.crypt(a, 0);
   return unpackDataLength(a); }

private static void writeDataLength (FileChannel channel, long dataLength, BlockStreamCrypter bsc) throws IOException {
   byte[] a = packDataLength(dataLength);  
   bsc.crypt(a, 0);
   ByteBuffer buf = ByteBuffer.wrap(a);
   int trLen = channel.write(buf);
   if (trLen != blockSize) {
      throw new IOException("Error while writing data length suffix."); }}

// Packs an integer into an 8-byte block. Used to encode the file size.
// To support larger files, we allow 13 more bits than the original IDEA V1.1 implementation.
// But files larger than 4GB are no longer backward compatible with the old IDEA V1.1 file structure.
private static byte[] packDataLength (long i) {
   if (i > 0x1FFFFFFFFFFFL) {                    // 45 bits
      throw new IllegalArgumentException("File too long."); }
   byte[] b = new byte[blockSize];
   b[7] = (byte)(i <<  3);
   b[6] = (byte)(i >>  5);
   b[5] = (byte)(i >> 13);
   b[4] = (byte)(i >> 21);
   b[3] = (byte)(i >> 29);
   b[2] = (byte)(i >> 37);
   return b; }

// Extracts an integer from an 8-byte block. Used to decode the file size.
// Returns -1 if the encoded value is invalid. This means that the input file is not a valid cryptogram.
private static long unpackDataLength (byte[] b) {
   if (b[0] != 0 || b[1] != 0 || (b[7] & 7) != 0) {
      return -1; }
   return
      (long)(b[7] & 0xFF) >>  3 |
      (long)(b[6] & 0xFF) <<  5 |
      (long)(b[5] & 0xFF) << 13 |
      (long)(b[4] & 0xFF) << 21 |
      (long)(b[3] & 0xFF) << 29 |
      (long)(b[2] & 0xFF) << 37; }

private static void xor (byte[] a, int pos, byte[] b) {
   for (int p = 0; p < blockSize; p++) {
      a[pos + p] ^= b[p]; }}

}
