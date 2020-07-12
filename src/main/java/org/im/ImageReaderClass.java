package org.im;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ImageReaderClass {

    private static Metadata metadata;
    private static final int YEAR_CONVERSION_CONSTANT = 1900;

    public static void main(String[] args) throws ImageProcessingException, IOException {

        File folder = new File("/home/naveenkumarn/Documents/TestImages");
        File[] images = folder.listFiles();
        for (File image : images) {
//            System.out.println(image.getName());
//            readTimeMetaData(image);
        }

        readAllMetaData(new File("/home/naveenkumarn/Documents/TestImages/PaintTool_sample.jpg"));
    }

    private static void readTimeMetaData(File image) throws ImageProcessingException, IOException {

        metadata = ImageMetadataReader.readMetadata(image);
        Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
        Date date = directory.getDate(ExifIFD0Directory.TAG_DATETIME);
        System.out.println("Date:" + date);
        System.out.println("Year =" + (date.getYear() + YEAR_CONVERSION_CONSTANT));
        System.out.println("Month =" + date.getMonth());
        System.out.println("Date =" + date.getDate());

    }

    private static void readAllMetaData(File image) throws ImageProcessingException, IOException {

        metadata = ImageMetadataReader.readMetadata(image);
        for(Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags())
                System.out.println(tag.toString());
        }
    }
}
