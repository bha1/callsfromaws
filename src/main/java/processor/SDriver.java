package processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.S3Object;

public class SDriver {
	 public static void main(String[] args) throws IOException {
		 long startTime = System.currentTimeMillis();
	        String clientRegion = "us-east-1";
	        String bucketName = "rogueagent-bucket1";
	        String key = "sample.json";
			BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAJCW6LJ5HOGVDMZWQ",
					"07pWUYHLXwPVhLDI46d7bkBQGeCov6Rm7A0x1Rxl");
	        try {
	        	System.out.println("1-"+(System.currentTimeMillis()-startTime));
	            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
	                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
	                    .withRegion(clientRegion)
	                    .build();
	            System.out.println("2-"+(System.currentTimeMillis()-startTime));
	            // Get an object and print its contents.
	            System.out.println("Downloading an object");
	            S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, key));
	            System.out.println("3-"+(System.currentTimeMillis()-startTime));
	            System.out.println("Content-Type: " + s3object.getObjectMetadata().getContentType());
	            System.out.println("Content: ");
	            displayTextInputStream(s3object.getObjectContent());
	            System.out.println("4-"+(System.currentTimeMillis()-startTime));
	            // Get a range of bytes from an object and print the bytes.
	            GetObjectRequest rangeObjectRequest = new GetObjectRequest(bucketName, key)
	                                                        .withRange(0,9);
	            S3Object objectPortion = s3Client.getObject(rangeObjectRequest);
	            System.out.println("Printing bytes retrieved.");
	            displayTextInputStream(objectPortion.getObjectContent());
	            
	            // Get an entire object, overriding the specified response headers, and print the object's content.
	            ResponseHeaderOverrides headerOverrides = new ResponseHeaderOverrides()
	                                                            .withCacheControl("No-cache")
	                                                            .withContentDisposition("attachment; filename=sample.json");
	            GetObjectRequest getObjectRequestHeaderOverride = new GetObjectRequest(bucketName, key)
	                                                            .withResponseHeaders(headerOverrides);
	            S3Object headerOverrideObject = s3Client.getObject(getObjectRequestHeaderOverride);
	            displayTextInputStream(headerOverrideObject.getObjectContent());
	        }
	        catch(AmazonServiceException e) {
	            // The call was transmitted successfully, but Amazon S3 couldn't process 
	            // it, so it returned an error response.
	            e.printStackTrace();
	        }
	        catch(SdkClientException e) {
	            // Amazon S3 couldn't be contacted for a response, or the client
	            // couldn't parse the response from Amazon S3.
	            e.printStackTrace();
	        }
	    }

	    private static void displayTextInputStream(InputStream input) throws IOException {
	        // Read the text input stream one line at a time and display each line.
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            System.out.println(line);
	        }
	        System.out.println();
	    }

}
