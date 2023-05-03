package edu.ucr.cs.cs167.mjone032;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import static org.apache.zookeeper.ZooDefs.OpCode.exists;


public class Main {
    public static void main(String[] args) throws IOException {

        try{
            if(args.length != 2){
                System.err.println("Incorrect number of arguments! Expected two arguments.");
                System.exit(-1);
            }

            Configuration conf = new Configuration();

            //file names
            org.apache.hadoop.fs.Path inputP = new Path(args[0]);
            org.apache.hadoop.fs.Path outP = new Path(args[1]);


            //getting their path
            FileSystem current_input_FS = inputP.getFileSystem(conf);
            FileSystem current_output_FS = outP.getFileSystem(conf);


            //if<directory>.hasfile<fileName>
            if(!current_input_FS.isFile(inputP)){
                System.err.printf("Input file '%s' does not exist!\n", inputP);
                System.exit(-1);
            }

            if(current_output_FS.isFile(outP)){
                System.err.printf("Output file '%s' already exists!\n", outP);
                System.exit(-1);
            }


            //data stream = <directory>.open(fileName)
            FSDataInputStream inbound = current_input_FS.open(inputP);
            FSDataOutputStream outbound = current_output_FS.create(outP);

            byte[] content = new byte[8192];

            long bytesUsed = 0;
            long currBite = 0;
            long starting = System.nanoTime();

            /*
            while((currBite = inbound.read(content))>0){
                bytesUsed += currBite;
                outbound.write(content, 0, (int) currBite);
            }
            */

            IOUtils.copyBytes(inbound,outbound,conf);
            bytesUsed=current_input_FS.getLength(outP);

            long ending = System.nanoTime();
            long elapsed = ending - starting;

            System.out.printf("Copied %d bytes from '%s' to '%s' in %f seconds\n",
                    bytesUsed, inputP, outP, elapsed * 1E-9);



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}